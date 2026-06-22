package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Camion;
import com.mycompany.transporteshirata.Logica.ReporteSensor;
import com.mycompany.transporteshirata.Logica.SensorGps;
import com.mycompany.transporteshirata.Logica.SensorTemperatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SensorDao {

    public boolean registrarLecturaGps(SensorGps lectura) {
        String sql = "INSERT INTO SensorGpsLectura "
                + "(idCamion, fechaHora, latitud, longitud, velocidadKmh, ruta, tiempoRecorridoMinutos, estadoSenal) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lectura.getCamion().getIdCamion());
            ps.setTimestamp(2, Timestamp.valueOf(lectura.getFechaHora()));
            ps.setDouble(3, lectura.getLatitud());
            ps.setDouble(4, lectura.getLongitud());
            ps.setDouble(5, lectura.getVelocidadKmh());
            ps.setString(6, lectura.getRuta());
            ps.setInt(7, lectura.getTiempoRecorridoMinutos());
            ps.setString(8, lectura.getEstadoSenal());
            ps.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "No pudimos guardar la lectura GPS. Revise la conexion y los datos ingresados.");
            return false;
        }
    }

    public boolean registrarLecturaTemperatura(SensorTemperatura lectura) {
        String sql = "INSERT INTO SensorTemperaturaLectura "
                + "(idCamion, fechaHora, temperaturaCelsius, limiteCritico, estado, observacion) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lectura.getCamion().getIdCamion());
            ps.setTimestamp(2, Timestamp.valueOf(lectura.getFechaHora()));
            ps.setDouble(3, lectura.getTemperaturaCelsius());
            ps.setDouble(4, lectura.getLimiteCritico());
            ps.setString(5, lectura.getEstado());
            ps.setString(6, lectura.getObservacion());
            ps.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "No pudimos guardar la lectura de temperatura. Revise la conexion y los datos ingresados.");
            return false;
        }
    }

    public List<SensorGps> listarLecturasGps() {
        List<SensorGps> lista = new ArrayList<>();
        String sql = "SELECT g.*, c.patente, c.marca, c.modelo "
                + "FROM SensorGpsLectura g INNER JOIN Camion c ON g.idCamion = c.idCamion "
                + "ORDER BY g.fechaHora DESC LIMIT 200";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearGps(rs));
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "No pudimos cargar las lecturas GPS.");
        }
        return lista;
    }

    public List<SensorTemperatura> listarLecturasTemperatura() {
        List<SensorTemperatura> lista = new ArrayList<>();
        String sql = "SELECT t.*, c.patente, c.marca, c.modelo "
                + "FROM SensorTemperaturaLectura t INNER JOIN Camion c ON t.idCamion = c.idCamion "
                + "ORDER BY t.fechaHora DESC LIMIT 200";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearTemperatura(rs));
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "No pudimos cargar las lecturas de temperatura.");
        }
        return lista;
    }

    public List<ReporteSensor> generarReporteSensores() {
        List<ReporteSensor> reporte = new ArrayList<>();
        agregarReporteRutas(reporte);
        agregarReporteRendimiento(reporte);
        agregarReporteTemperatura(reporte);
        if (reporte.isEmpty()) {
            reporte.add(new ReporteSensor("RF-12", "Sin datos", "0",
                    "Aun no existen lecturas para generar analisis."));
        }
        return reporte;
    }

    public int contarLecturasGps() {
        return contar("SELECT COUNT(*) FROM SensorGpsLectura");
    }

    public int contarLecturasTemperatura() {
        return contar("SELECT COUNT(*) FROM SensorTemperaturaLectura");
    }

    public int contarAlertasTemperatura() {
        return contar("SELECT COUNT(*) FROM SensorTemperaturaLectura WHERE estado = 'Critico'");
    }

    private void agregarReporteRutas(List<ReporteSensor> reporte) {
        String sql = "SELECT ruta, COUNT(*) total, AVG(velocidadKmh) velocidad, AVG(tiempoRecorridoMinutos) tiempo "
                + "FROM SensorGpsLectura GROUP BY ruta ORDER BY total DESC LIMIT 5";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String detalle = "Velocidad promedio "
                        + String.format("%.1f", rs.getDouble("velocidad"))
                        + " km/h, tiempo promedio "
                        + String.format("%.0f", rs.getDouble("tiempo")) + " min.";
                reporte.add(new ReporteSensor("RF-12", "Ruta frecuente",
                        rs.getString("ruta") + " (" + rs.getInt("total") + " lecturas)", detalle));
            }
        } catch (SQLException | NullPointerException ex) {
            reporte.add(new ReporteSensor("RF-12", "Error reporte GPS", "No disponible",
                    "No pudimos analizar las rutas frecuentes."));
        }
    }

    private void agregarReporteRendimiento(List<ReporteSensor> reporte) {
        String sql = "SELECT c.patente, COUNT(g.idLecturaGps) lecturas, "
                + "COALESCE(AVG(g.velocidadKmh), 0) velocidad, "
                + "COALESCE(SUM(g.tiempoRecorridoMinutos), 0) minutos "
                + "FROM Camion c LEFT JOIN SensorGpsLectura g ON c.idCamion = g.idCamion "
                + "GROUP BY c.idCamion, c.patente HAVING lecturas > 0 ORDER BY lecturas DESC";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String detalle = rs.getInt("lecturas") + " lecturas, "
                        + String.format("%.1f", rs.getDouble("velocidad")) + " km/h promedio.";
                reporte.add(new ReporteSensor("RF-12", "Rendimiento camion",
                        rs.getString("patente"), detalle));
            }
        } catch (SQLException | NullPointerException ex) {
            reporte.add(new ReporteSensor("RF-12", "Error rendimiento", "No disponible",
                    "No pudimos analizar el rendimiento de camiones."));
        }
    }

    private void agregarReporteTemperatura(List<ReporteSensor> reporte) {
        String sql = "SELECT c.patente, COUNT(t.idLecturaTemperatura) lecturas, "
                + "COALESCE(AVG(t.temperaturaCelsius), 0) promedio, "
                + "COALESCE(MAX(t.temperaturaCelsius), 0) maxima, "
                + "SUM(CASE WHEN t.estado = 'Critico' THEN 1 ELSE 0 END) alertas "
                + "FROM Camion c LEFT JOIN SensorTemperaturaLectura t ON c.idCamion = t.idCamion "
                + "GROUP BY c.idCamion, c.patente HAVING lecturas > 0 ORDER BY alertas DESC, maxima DESC";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String detalle = "Promedio " + String.format("%.1f", rs.getDouble("promedio"))
                        + " C, maxima " + String.format("%.1f", rs.getDouble("maxima"))
                        + " C, alertas " + rs.getInt("alertas") + ".";
                reporte.add(new ReporteSensor("RF-13", "Control temperatura",
                        rs.getString("patente"), detalle));
            }
        } catch (SQLException | NullPointerException ex) {
            reporte.add(new ReporteSensor("RF-13", "Error temperatura", "No disponible",
                    "No pudimos analizar las temperaturas de carga."));
        }
    }

    private int contar(String sql) {
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException | NullPointerException ex) {
            return 0;
        }
        return 0;
    }

    private SensorGps mapearGps(ResultSet rs) throws SQLException {
        Camion camion = new Camion();
        camion.setIdCamion(rs.getInt("idCamion"));
        camion.setPatente(rs.getString("patente"));
        camion.setMarca(rs.getString("marca"));
        camion.setModelo(rs.getString("modelo"));

        SensorGps lectura = new SensorGps();
        lectura.setIdLecturaGps(rs.getInt("idLecturaGps"));
        lectura.setCamion(camion);
        lectura.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
        lectura.setLatitud(rs.getDouble("latitud"));
        lectura.setLongitud(rs.getDouble("longitud"));
        lectura.setVelocidadKmh(rs.getDouble("velocidadKmh"));
        lectura.setRuta(rs.getString("ruta"));
        lectura.setTiempoRecorridoMinutos(rs.getInt("tiempoRecorridoMinutos"));
        lectura.setEstadoSenal(rs.getString("estadoSenal"));
        return lectura;
    }

    private SensorTemperatura mapearTemperatura(ResultSet rs) throws SQLException {
        Camion camion = new Camion();
        camion.setIdCamion(rs.getInt("idCamion"));
        camion.setPatente(rs.getString("patente"));
        camion.setMarca(rs.getString("marca"));
        camion.setModelo(rs.getString("modelo"));

        SensorTemperatura lectura = new SensorTemperatura();
        lectura.setIdLecturaTemperatura(rs.getInt("idLecturaTemperatura"));
        lectura.setCamion(camion);
        lectura.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
        lectura.setTemperaturaCelsius(rs.getDouble("temperaturaCelsius"));
        lectura.setLimiteCritico(rs.getDouble("limiteCritico"));
        lectura.setEstado(rs.getString("estado"));
        lectura.setObservacion(rs.getString("observacion"));
        return lectura;
    }
}
