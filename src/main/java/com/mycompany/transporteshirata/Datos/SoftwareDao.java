package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Software;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SoftwareDao {

    public List<Software> listarSoftware() {
        List<Software> lista = new ArrayList<>();
        String sql = "SELECT * FROM Software ORDER BY nombre";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return lista;
            }
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Software s = new Software();
                    s.setIdSoftware(rs.getInt("idSoftware"));
                    s.setNombre(rs.getString("nombre"));
                    s.setVersionActual(rs.getString("versionActual"));
                    s.setProveedor(rs.getString("proveedor"));
                    s.setTipo(rs.getString("tipo"));
                    s.setEstado(rs.getString("estado"));
                    lista.add(s);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar software: " + e.toString());
        }
        return lista;
    }

    public boolean existeSoftware(String nombre, int idIgnorar) {
        String sql = "SELECT idSoftware FROM Software WHERE nombre = ? AND idSoftware <> ?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return true;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setInt(2, idIgnorar);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar software: " + e.toString());
            return true;
        }
    }

    public boolean registrarSoftware(Software s) {
        String sql = "INSERT INTO Software (nombre, versionActual, proveedor, tipo, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, s.getNombre());
                ps.setString(2, s.getVersionActual());
                ps.setString(3, s.getProveedor());
                ps.setString(4, s.getTipo());
                ps.setString(5, s.getEstado());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar software: " + e.toString());
            return false;
        }
    }

    public boolean modificarSoftware(Software s) {
        String sql = "UPDATE Software SET nombre=?, versionActual=?, proveedor=?, tipo=?, estado=? WHERE idSoftware=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, s.getNombre());
                ps.setString(2, s.getVersionActual());
                ps.setString(3, s.getProveedor());
                ps.setString(4, s.getTipo());
                ps.setString(5, s.getEstado());
                ps.setInt(6, s.getIdSoftware());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar software: " + e.toString());
            return false;
        }
    }

    public boolean eliminarSoftware(int idSoftware) {
        String sql = "DELETE FROM Software WHERE idSoftware=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idSoftware);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar software: " + e.toString());
            return false;
        }
    }

    public boolean actualizarVersionActual(int idSoftware, String versionNueva) {
        String sql = "UPDATE Software SET versionActual=? WHERE idSoftware=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, versionNueva);
                ps.setInt(2, idSoftware);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar version del software: " + e.toString());
            return false;
        }
    }
}
