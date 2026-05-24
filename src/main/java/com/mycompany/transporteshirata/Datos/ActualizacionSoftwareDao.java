package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.ActualizacionSoftware;
import com.mycompany.transporteshirata.Logica.Equipo;
import com.mycompany.transporteshirata.Logica.Software;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ActualizacionSoftwareDao {

    public boolean registrarActualizacion(ActualizacionSoftware a) {
        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);
            try {
                insertarActualizacion(con, a);
                if ("realizada".equals(a.getEstado())) {
                    actualizarSoftwareEquipo(con, a);
                    actualizarVersionActual(con, a.getSoftware().getIdSoftware(), a.getVersionNueva());
                }
                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Error al registrar actualizacion de software: " + e.getMessage());
                return false;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar actualizacion de software: " + e.toString());
            return false;
        }
    }

    public String obtenerVersionInstalada(int idEquipo, int idSoftware) {
        String sql = "SELECT versionInstalada FROM SoftwareEquipo WHERE idEquipo=? AND idSoftware=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return "";
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idEquipo);
                ps.setInt(2, idSoftware);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("versionInstalada");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar version instalada: " + e.toString());
        }
        return "";
    }

    public List<ActualizacionSoftware> listarActualizaciones() {
        List<ActualizacionSoftware> lista = new ArrayList<>();
        String sql = "SELECT a.*, e.codigo, e.tipo AS tipoEquipo, s.nombre, s.versionActual, s.proveedor, s.tipo AS tipoSoftware, s.estado AS estadoSoftware "
                + "FROM ActualizacionSoftware a "
                + "INNER JOIN equipos e ON a.idEquipo = e.idEquipo "
                + "INNER JOIN Software s ON a.idSoftware = s.idSoftware "
                + "ORDER BY a.fechaActualizacion DESC, a.idActualizacion DESC";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return lista;
            }
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ActualizacionSoftware a = new ActualizacionSoftware();
                    a.setIdActualizacion(rs.getInt("idActualizacion"));
                    a.setFechaActualizacion(rs.getDate("fechaActualizacion").toLocalDate());
                    a.setVersionAnterior(rs.getString("versionAnterior"));
                    a.setVersionNueva(rs.getString("versionNueva"));
                    a.setTecnicoResponsable(rs.getString("tecnicoResponsable"));
                    a.setEstado(rs.getString("estado"));
                    a.setObservaciones(rs.getString("observaciones"));

                    Equipo e = new Equipo();
                    e.setIdEquipo(rs.getInt("idEquipo"));
                    e.setCodigo(rs.getString("codigo"));
                    e.setTipo(rs.getString("tipoEquipo"));
                    a.setEquipo(e);

                    Software s = new Software();
                    s.setIdSoftware(rs.getInt("idSoftware"));
                    s.setNombre(rs.getString("nombre"));
                    s.setVersionActual(rs.getString("versionActual"));
                    s.setProveedor(rs.getString("proveedor"));
                    s.setTipo(rs.getString("tipoSoftware"));
                    s.setEstado(rs.getString("estadoSoftware"));
                    a.setSoftware(s);

                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar actualizaciones: " + e.toString());
        }
        return lista;
    }

    private void insertarActualizacion(Connection con, ActualizacionSoftware a) throws SQLException {
        String sql = "INSERT INTO ActualizacionSoftware "
                + "(fechaActualizacion, versionAnterior, versionNueva, tecnicoResponsable, estado, observaciones, idEquipo, idSoftware) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(a.getFechaActualizacion()));
            ps.setString(2, a.getVersionAnterior());
            ps.setString(3, a.getVersionNueva());
            ps.setString(4, a.getTecnicoResponsable());
            ps.setString(5, a.getEstado());
            ps.setString(6, a.getObservaciones());
            ps.setInt(7, a.getEquipo().getIdEquipo());
            ps.setInt(8, a.getSoftware().getIdSoftware());
            ps.executeUpdate();
        }
    }

    private void actualizarSoftwareEquipo(Connection con, ActualizacionSoftware a) throws SQLException {
        String sql = "INSERT INTO SoftwareEquipo "
                + "(idEquipo, idSoftware, versionInstalada, fechaInstalacion, estado) "
                + "VALUES (?, ?, ?, ?, 'actualizado') "
                + "ON DUPLICATE KEY UPDATE versionInstalada=VALUES(versionInstalada), "
                + "fechaInstalacion=VALUES(fechaInstalacion), estado='actualizado'";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getEquipo().getIdEquipo());
            ps.setInt(2, a.getSoftware().getIdSoftware());
            ps.setString(3, a.getVersionNueva());
            ps.setDate(4, java.sql.Date.valueOf(a.getFechaActualizacion()));
            ps.executeUpdate();
        }
    }

    private void actualizarVersionActual(Connection con, int idSoftware, String versionNueva) throws SQLException {
        String sql = "UPDATE Software SET versionActual=? WHERE idSoftware=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, versionNueva);
            ps.setInt(2, idSoftware);
            ps.executeUpdate();
        }
    }
}
