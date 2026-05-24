package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Equipo;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import com.mycompany.transporteshirata.Logica.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MantenimientoEquipoDao {

    public List<MantenimientoEquipo> listarMantenimientosEquipo() {
        List<MantenimientoEquipo> lista = new ArrayList<>();
        String sql = "SELECT me.*, e.tipo AS tipoEquipo, e.codigo, e.marca, e.ubicacion, e.estado AS estadoEquipo "
                + "FROM MantenimientoEquipo me INNER JOIN equipos e ON me.idEquipo = e.idEquipo";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return lista;
            }
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MantenimientoEquipo m = new MantenimientoEquipo();
                    m.setIdMantenimientoEquipo(rs.getInt("idMantenimientoEquipo"));
                    m.setFechaMantenimiento(rs.getDate("fechaMantenimiento").toLocalDate());
                    m.setTipoMantenimiento(rs.getString("tipoMantenimiento"));
                    m.setDescripcion(rs.getString("descripcion"));
                    m.setTecnicoResponsable(rs.getString("tecnicoResponsable"));
                    m.setObservaciones(rs.getString("observaciones"));
                    m.setEstado(rs.getString("estado"));

                    Equipo e = new Equipo();
                    e.setIdEquipo(rs.getInt("idEquipo"));
                    e.setTipo(rs.getString("tipoEquipo"));
                    e.setCodigo(rs.getString("codigo"));
                    e.setMarca(rs.getString("marca"));
                    e.setUbicacion(rs.getString("ubicacion"));
                    e.setEstado(rs.getString("estadoEquipo"));
                    m.setEquipo(e);

                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar historial de equipos: " + e.toString());
        }
        return lista;
    }

    public boolean registrarMantenimientoEquipo(MantenimientoEquipo m) {
        return registrarMantenimientoEquipoYObtenerId(m) > 0;
    }

    public int registrarMantenimientoEquipoYObtenerId(MantenimientoEquipo m) {
        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return 0;
            }
            return insertarMantenimiento(con, m);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar mantenimiento de equipo: " + e.toString());
            return 0;
        }
    }

    public boolean registrarMantenimientoEquipoConPieza(MantenimientoEquipo m, Pieza pieza, int cantidad) {
        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);
            try {
                int idMantenimiento = insertarMantenimiento(con, m);
                if (idMantenimiento <= 0) {
                    con.rollback();
                    return false;
                }

                int nuevoStock = calcularNuevoStock(con, pieza.getIdPieza(), cantidad);
                insertarMovimientoInventario(con, pieza.getIdPieza(), idMantenimiento, cantidad, m.getTecnicoResponsable(), m.getFechaMantenimiento());
                actualizarStock(con, pieza.getIdPieza(), nuevoStock);

                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Error al registrar mantenimiento con pieza: " + e.getMessage());
                return false;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar mantenimiento con pieza: " + e.toString());
            return false;
        }
    }

    private int insertarMantenimiento(Connection con, MantenimientoEquipo m) throws SQLException {
        String sql = "INSERT INTO MantenimientoEquipo "
                + "(fechaMantenimiento, tipoMantenimiento, descripcion, tecnicoResponsable, observaciones, estado, idEquipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, java.sql.Date.valueOf(m.getFechaMantenimiento()));
            ps.setString(2, m.getTipoMantenimiento());
            ps.setString(3, m.getDescripcion());
            ps.setString(4, m.getTecnicoResponsable());
            ps.setString(5, m.getObservaciones());
            ps.setString(6, m.getEstado());
            ps.setInt(7, m.getEquipo().getIdEquipo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    private int calcularNuevoStock(Connection con, int idPieza, int cantidad) throws SQLException {
        String sql = "SELECT stockActual FROM Pieza WHERE idPieza=? FOR UPDATE";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPieza);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("La pieza seleccionada no existe.");
                }
                int stockActual = rs.getInt("stockActual");
                if (stockActual < cantidad) {
                    throw new SQLException("No hay stock suficiente para usar esta pieza.");
                }
                return stockActual - cantidad;
            }
        }
    }

    private void insertarMovimientoInventario(Connection con, int idPieza, int idMantenimiento, int cantidad,
            String tecnico, LocalDate fecha) throws SQLException {
        String sql = "INSERT INTO MovimientoInventario "
                + "(fecha, tipoMovimiento, cantidad, motivo, tecnicoResponsable, idPieza, idMantenimientoEquipo) "
                + "VALUES (?, 'salida', ?, 'Uso en mantenimiento de equipo', ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            ps.setInt(2, cantidad);
            ps.setString(3, tecnico);
            ps.setInt(4, idPieza);
            ps.setInt(5, idMantenimiento);
            ps.executeUpdate();
        }
    }

    private void actualizarStock(Connection con, int idPieza, int nuevoStock) throws SQLException {
        String sql = "UPDATE Pieza SET stockActual=?, "
                + "estado=CASE "
                + "WHEN ? <= 0 THEN 'sin stock' "
                + "WHEN ? <= stockMinimo THEN 'stock bajo' "
                + "ELSE 'disponible' END "
                + "WHERE idPieza=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setInt(2, nuevoStock);
            ps.setInt(3, nuevoStock);
            ps.setInt(4, idPieza);
            ps.executeUpdate();
        }
    }
}
