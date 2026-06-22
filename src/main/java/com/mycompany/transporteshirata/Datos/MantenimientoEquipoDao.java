package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.EquipoOficina;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            }
        }
    }

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