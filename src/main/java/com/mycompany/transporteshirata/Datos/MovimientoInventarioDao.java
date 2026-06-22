package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import com.mycompany.transporteshirata.Logica.MovimientoInventario;
import com.mycompany.transporteshirata.Logica.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MovimientoInventarioDao {

    public boolean registrarMovimiento(MovimientoInventario m) {
        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);
            try {
                int nuevoStock = calcularNuevoStock(con, m);
                insertarMovimiento(con, m);
                actualizarStock(con, m.getPieza().getIdPieza(), nuevoStock);
                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(null, e.getMessage());
                return false;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar movimiento de inventario: " + e.toString());
            return false;
        }
    }

    public List<MovimientoInventario> listarMovimientos() {
        List<MovimientoInventario> lista = new ArrayList<>();
        String sql = "SELECT mi.*, p.codigo, p.nombre, p.categoria, p.marca, p.stockActual, p.stockMinimo, p.ubicacion, p.estado "
                + "FROM MovimientoInventario mi INNER JOIN Pieza p ON mi.idPieza = p.idPieza "
                + "ORDER BY mi.fecha DESC, mi.idMovimiento DESC";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return lista;
            }
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MovimientoInventario m = new MovimientoInventario();
                    m.setIdMovimiento(rs.getInt("idMovimiento"));
                    m.setFecha(rs.getDate("fecha").toLocalDate());
                    m.setTipoMovimiento(rs.getString("tipoMovimiento"));
                    m.setCantidad(rs.getInt("cantidad"));
                    m.setMotivo(rs.getString("motivo"));
                    m.setTecnicoResponsable(rs.getString("tecnicoResponsable"));

                    Pieza p = new Pieza();
                    p.setIdPieza(rs.getInt("idPieza"));
                    p.setCodigo(rs.getString("codigo"));
                    p.setNombre(rs.getString("nombre"));
                    p.setCategoria(rs.getString("categoria"));
                    p.setMarca(rs.getString("marca"));
                    p.setStockActual(rs.getInt("stockActual"));
                    p.setStockMinimo(rs.getInt("stockMinimo"));
                    p.setUbicacion(rs.getString("ubicacion"));
                    p.setEstado(rs.getString("estado"));
                    m.setPieza(p);

                    int idMantenimiento = rs.getInt("idMantenimientoEquipo");
                    if (!rs.wasNull()) {
                        MantenimientoEquipo mantenimiento = new MantenimientoEquipo();
                        mantenimiento.setIdMantenimientoEquipo(idMantenimiento);
                        m.setMantenimientoEquipo(mantenimiento);
                    }

                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar movimientos de inventario: " + e.toString());
        }
        return lista;
    }

    private int calcularNuevoStock(Connection con, MovimientoInventario m) throws SQLException {
        String sql = "SELECT stockActual FROM Pieza WHERE idPieza=? FOR UPDATE";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, m.getPieza().getIdPieza());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("La pieza seleccionada no existe.");
                }

                int stockActual = rs.getInt("stockActual");
                if ("entrada".equals(m.getTipoMovimiento())) {
                    return stockActual + m.getCantidad();
                }
                if ("salida".equals(m.getTipoMovimiento())) {
                    if (stockActual < m.getCantidad()) {
                        throw new SQLException("No hay stock suficiente para usar esta pieza.");
                    }
                    return stockActual - m.getCantidad();
                }
                return m.getCantidad();
            }
        }
    }

    private void insertarMovimiento(Connection con, MovimientoInventario m) throws SQLException {
        String sql = "INSERT INTO MovimientoInventario "
                + "(fecha, tipoMovimiento, cantidad, motivo, tecnicoResponsable, idPieza, idMantenimientoEquipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(m.getFecha()));
            ps.setString(2, m.getTipoMovimiento());
            ps.setInt(3, m.getCantidad());
            ps.setString(4, m.getMotivo());
            ps.setString(5, m.getTecnicoResponsable());
            ps.setInt(6, m.getPieza().getIdPieza());

            if (m.getMantenimientoEquipo() != null && m.getMantenimientoEquipo().getIdMantenimientoEquipo() > 0) {
                ps.setInt(7, m.getMantenimientoEquipo().getIdMantenimientoEquipo());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }
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
