package com.mycompany.transporteshirata.Datos;

<<<<<<< HEAD
import com.mycompany.transporteshirata.Logica.EquipoOficina;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import java.sql.*;
=======
import com.mycompany.transporteshirata.Logica.Equipo;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import com.mycompany.transporteshirata.Logica.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

<<<<<<< HEAD
/**
 * Clase de Acceso a Datos (DAO) para el mantenimiento e historial de equipos de oficina.
 * Implementa una arquitectura transaccional para garantizar la atomicidad al descontar insumos.
 * Cumple con los requerimientos funcivos RF-06 y RF-08.
 * @author Diego Oyarzo
 * @version 2.0
 */
public class MantenimientoEquipoDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Ejecuta una transacción SQL para registrar una mantención física de oficina,
     * asociar la pieza utilizada en la tabla asociativa decrepitar las existencias físicas en el almacén.
     */
    public boolean registrarMantenimiento(MantenimientoEquipo m, int idPieza, int cantidad) {
        if (m.getEquipo() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un equipo.");
            return false;
        }

        String sqlMant = "INSERT INTO MantenimientoEquipo (idEquipo, fecha, tipo, descripcion, tecnico, observaciones) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPieza = "INSERT INTO MantenimientoPieza (idMantenimientoEquipo, idPieza, cantidad) VALUES (?, ?, ?)";
        String sqlStock = "UPDATE PiezaRepuesto SET stock = stock - ?, estado = CASE WHEN stock - ? <= 0 THEN 'Sin stock' ELSE estado END WHERE idPieza = ?";

        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            ps = con.prepareStatement(sqlMant, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getEquipo().getIdEquipo());
            ps.setDate(2, Date.valueOf(m.getFecha()));
            ps.setString(3, m.getTipo());
            ps.setString(4, m.getDescripcion());
            ps.setString(5, m.getTecnico());
            ps.setString(6, m.getObservaciones());
            ps.executeUpdate();

            ResultSet rsKeys = ps.getGeneratedKeys();
            int idMantGenerado = -1;
            if (rsKeys.next()) {
                idMantGenerado = rsKeys.getInt(1);
            }

            if (idPieza > 0 && cantidad > 0) {
                PreparedStatement psPieza = con.prepareStatement(sqlPieza);
                psPieza.setInt(1, idMantGenerado);
                psPieza.setInt(2, idPieza);
                psPieza.setInt(3, cantidad);
                psPieza.executeUpdate();

                PreparedStatement psStock = con.prepareStatement(sqlStock);
                psStock.setInt(1, cantidad);
                psStock.setInt(2, cantidad);
                psStock.setInt(3, idPieza);
                psStock.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException e) {
                // Manejo del error del rollback
            }
            JOptionPane.showMessageDialog(null, "Error al registrar mantenimiento: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException e) {
                // Restablecimiento del auto-commit
=======
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
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            }
        }
    }

<<<<<<< HEAD
    public List<MantenimientoEquipo> listarMantenimientos() {
        List<MantenimientoEquipo> lista = new ArrayList<>();
        String sql = "SELECT me.*, eq.nombre, eq.tipo, eq.marca, eq.modelo "
                + "FROM MantenimientoEquipo me "
                + "INNER JOIN EquipoOficina eq ON me.idEquipo = eq.idEquipo "
                + "ORDER BY me.fecha DESC";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar mantenimientos: " + ex.getMessage());
        }
        return lista;
    }

    public List<MantenimientoEquipo> listarPorEquipo(int idEquipo) {
        List<MantenimientoEquipo> lista = new ArrayList<>();
        String sql = "SELECT me.*, eq.nombre, eq.tipo, eq.marca, eq.modelo "
                + "FROM MantenimientoEquipo me "
                + "INNER JOIN EquipoOficina eq ON me.idEquipo = eq.idEquipo "
                + "WHERE me.idEquipo = ? "
                + "ORDER BY me.fecha DESC";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEquipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener historial: " + ex.getMessage());
        }
        return lista;
    }

    public boolean actualizarMantenimiento(MantenimientoEquipo m) {
        String sql = "UPDATE MantenimientoEquipo SET fecha=?, tipo=?, descripcion=?, tecnico=?, observaciones=? WHERE idMantenimientoEquipo=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(m.getFecha()));
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getDescripcion());
            ps.setString(4, m.getTecnico());
            ps.setString(5, m.getObservaciones());
            ps.setInt(6, m.getIdMantenimientoEquipo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar mantenimiento: " + ex.getMessage());
            return false;
        }
    }

    private MantenimientoEquipo mapearFila(ResultSet rs) throws SQLException {
        MantenimientoEquipo m = new MantenimientoEquipo();
        m.setIdMantenimientoEquipo(rs.getInt("idMantenimientoEquipo"));
        m.setFecha(rs.getDate("fecha").toLocalDate());
        m.setTipo(rs.getString("tipo"));
        m.setDescripcion(rs.getString("descripcion"));
        m.setTecnico(rs.getString("tecnico"));
        m.setObservaciones(rs.getString("observaciones"));

        EquipoOficina eq = new EquipoOficina();
        eq.setIdEquipo(rs.getInt("idEquipo"));
        eq.setNombre(rs.getString("nombre"));
        eq.setTipo(rs.getString("tipo"));
        eq.setMarca(rs.getString("marca"));
        eq.setModelo(rs.getString("modelo"));
        m.setEquipo(eq);

        return m;
    }
}
=======
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
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
