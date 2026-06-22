package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.EquipoOficina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase de Acceso a Datos (DAO) para la gestión del inventario de equipos de oficina.
 * Controla el registro, listado y actualizaciones de estado de los activos informáticos.
 * Cumple con el requerimiento funcional RF-06.
 */
public class EquipoOficinaDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrarEquipo(EquipoOficina e) {
        if (e.getNombre() == null || e.getNombre().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del equipo no puede estar vacío.");
            return false;
        }
        String sql = "INSERT INTO EquipoOficina (nombre, tipo, marca, modelo, numeroSerie, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getTipo());
            ps.setString(3, e.getMarca());
            ps.setString(4, e.getModelo());
            ps.setString(5, e.getNumeroSerie());
            ps.setString(6, e.getEstado());
            ps.execute();
            return true;
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "El número de serie ya existe en el sistema.");
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar equipo: " + ex.getMessage());
            return false;
        }
    }

    public List<EquipoOficina> listarEquipos() {
        List<EquipoOficina> lista = new ArrayList<>();
        String sql = "SELECT * FROM EquipoOficina ORDER BY nombre";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EquipoOficina eq = new EquipoOficina();
                eq.setIdEquipo(rs.getInt("idEquipo"));
                eq.setNombre(rs.getString("nombre"));
                eq.setTipo(rs.getString("tipo"));
                eq.setMarca(rs.getString("marca"));
                eq.setModelo(rs.getString("modelo"));
                eq.setNumeroSerie(rs.getString("numeroSerie"));
                eq.setEstado(rs.getString("estado"));
                lista.add(eq);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar equipos: " + ex.getMessage());
        }
        return lista;
    }

    public List<EquipoOficina> buscarEquipos(String termino) {
        List<EquipoOficina> lista = new ArrayList<>();
        String sql = "SELECT * FROM EquipoOficina WHERE nombre LIKE ? OR tipo LIKE ? ORDER BY nombre";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + termino + "%");
            ps.setString(2, "%" + termino + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                EquipoOficina eq = new EquipoOficina();
                eq.setIdEquipo(rs.getInt("idEquipo"));
                eq.setNombre(rs.getString("nombre"));
                eq.setTipo(rs.getString("tipo"));
                eq.setMarca(rs.getString("marca"));
                eq.setModelo(rs.getString("modelo"));
                eq.setNumeroSerie(rs.getString("numeroSerie"));
                eq.setEstado(rs.getString("estado"));
                lista.add(eq);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar equipos: " + ex.getMessage());
        }
        return lista;
    }

    public boolean actualizarEstado(int idEquipo, String nuevoEstado) {
        String sql = "UPDATE EquipoOficina SET estado = ? WHERE idEquipo = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idEquipo);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estado: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Modifica de manera integral los atributos de un equipo de oficina existente.
     * @param e Objeto EquipoOficina con los datos actualizados.
     * @return true si la actualización fue exitosa; false en caso contrario.
     */
    public boolean modificarEquipo(EquipoOficina e) {
        String sql = "UPDATE EquipoOficina SET nombre=?, tipo=?, marca=?, modelo=?, numeroSerie=?, estado=? WHERE idEquipo=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getTipo());
            ps.setString(3, e.getMarca());
            ps.setString(4, e.getModelo());
            ps.setString(5, e.getNumeroSerie());
            ps.setString(6, e.getEstado());
            ps.setInt(7, e.getIdEquipo());
            ps.execute();
            return true;
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "El número de serie ingresado ya existe en otro equipo del sistema.");
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar equipo: " + ex.getMessage());
            return false;
        }
    }
    /**
     * Elimina un equipo de oficina del sistema utilizando su identificador.
     * @param id Identificador único del equipo a eliminar.
     * @return true si el registro fue borrado; false si tiene mantenimientos asociados.
     */
    public boolean eliminarEquipo(int id) {
        String sql = "DELETE FROM EquipoOficina WHERE idEquipo=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar el equipo. Es probable que tenga mantenimientos asociados en el historial.");
            return false;
        }
    }
}

