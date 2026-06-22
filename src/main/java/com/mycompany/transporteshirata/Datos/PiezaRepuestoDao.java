package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.PiezaRepuesto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase de Acceso a Datos (DAO) para el control del inventario físico de repuestos de oficina.
 * Controla la lógica de altas, bajas de existencias y actualizaciones críticas de stock (RF-09).
 * * @author Diego Oyarzo
 * @version 2.0
 */
public class PiezaRepuestoDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Registra un nuevo artículo de repuesto en los depósitos del inventario.
     * * @param p Instancia de {@link PiezaRepuesto} con los campos iniciales requeridos.
     * @return {@code true} si la inserción fue correcta; {@code false} si viola las restricciones comerciales del negocio.
     */
    public boolean registrarPieza(PiezaRepuesto p) {
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de la pieza no puede estar vacío.");
            return false;
        }
        if (p.getStock() < 0) {
            JOptionPane.showMessageDialog(null, "El stock no puede ser negativo.");
            return false;
        }

        String sql = "INSERT INTO PiezaRepuesto (nombre, descripcion, stock, estado) VALUES (?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getEstado());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar pieza: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Lista de manera global el inventario disponible en las bodegas del sistema.
     * * @return Lista clasificada alfabéticamente por el nombre del repuesto.
     */
    public List<PiezaRepuesto> listarPiezas() {
        List<PiezaRepuesto> lista = new ArrayList<>();
        String sql = "SELECT * FROM PiezaRepuesto ORDER BY nombre";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PiezaRepuesto p = new PiezaRepuesto();
                p.setIdPieza(rs.getInt("idPieza"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setStock(rs.getInt("stock"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar piezas: " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Ejecuta una búsqueda de componentes utilizando un filtro parcial de texto sobre el nombre.
     * * @param termino Palabra clave a buscar.
     * @return Colección de objetos {@link PiezaRepuesto} que coinciden con la búsqueda.
     */
    public List<PiezaRepuesto> buscarPiezas(String termino) {
        List<PiezaRepuesto> lista = new ArrayList<>();
        String sql = "SELECT * FROM PiezaRepuesto WHERE nombre LIKE ? ORDER BY nombre";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + termino + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                PiezaRepuesto p = new PiezaRepuesto();
                p.setIdPieza(rs.getInt("idPieza"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setStock(rs.getInt("stock"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar piezas: " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Ejecuta una comprobación de stock y descuenta las existencias físicas de un insumo.
     * Actualiza el estado a 'Sin stock' si el inventario llega a cero.
     * * @param idPieza Código identificador de la pieza.
     * @param cantidad Cantidad física a remover.
     * @return {@code true} si se efectuó la remoción de stock; {@code false} ante inconsistencias numéricas.
     */
    public boolean descontarStock(int idPieza, int cantidad) {
        String sqlVerificar = "SELECT stock FROM PiezaRepuesto WHERE idPieza = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sqlVerificar);
            ps.setInt(1, idPieza);
            rs = ps.executeQuery();
            if (rs.next()) {
                int stockActual = rs.getInt("stock");
                if (stockActual < cantidad) {
                    JOptionPane.showMessageDialog(null, "Stock insuficiente. Stock disponible: " + stockActual);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pieza no encontrada.");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar stock: " + ex.getMessage());
            return false;
        }

        String sqlDescontar = "UPDATE PiezaRepuesto SET stock = stock - ?, "
                            + "estado = CASE WHEN stock - ? <= 0 THEN 'Sin stock' ELSE estado END "
                            + "WHERE idPieza = ?";
        try {
            ps = con.prepareStatement(sqlDescontar);
            ps.setInt(1, cantidad);
            ps.setInt(2, cantidad);
            ps.setInt(3, idPieza);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al descontar stock: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Modifica los datos descriptivos, cantidades en existencia y etiquetas de estado de un repuesto.
     * * @param p Objeto {@link PiezaRepuesto} estructurado con las nuevas propiedades.
     * @return {@code true} si la actualización se materializó; {@code false} si falla la restricción lógica.
     */
    public boolean actualizarPieza(PiezaRepuesto p) {
        if (p.getStock() < 0) {
            JOptionPane.showMessageDialog(null, "El stock no puede ser negativo.");
            return false;
        }
        String sql = "UPDATE PiezaRepuesto SET nombre=?, descripcion=?, stock=?, estado=? WHERE idPieza=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getEstado());
            ps.setInt(5, p.getIdPieza());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar pieza: " + ex.getMessage());
            return false;
        }
    }
}