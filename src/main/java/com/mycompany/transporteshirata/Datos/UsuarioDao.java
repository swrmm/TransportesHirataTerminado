/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class UsuarioDao {

    private Connection conexion;

    public UsuarioDao() {
        this.conexion = Conexion.getConexion();
    }

    public Usuario validarUsuario(String rut, String clave) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE rut = ? AND clave = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, rut);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setRut(rs.getString("rut"));
                usuario.setClave(rs.getString("clave"));
                usuario.setCargo(rs.getString("cargo"));
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<Usuario> listarPersonal() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario WHERE cargo = 'personal'";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setRut(rs.getString("rut"));
                usuario.setClave(rs.getString("clave"));
                usuario.setCargo(rs.getString("cargo"));
                lista.add(usuario);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar personal: " + e.toString());
        }

        return lista;
    }

    public boolean existeRut(String rut, int idIgnorar) {
        String sql = "SELECT idUsuario FROM Usuario WHERE rut = ? AND idUsuario <> ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, rut);
            ps.setInt(2, idIgnorar);
            ResultSet rs = ps.executeQuery();
            boolean existe = rs.next();
            rs.close();
            ps.close();
            return existe;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar RUT de usuario: " + e.toString());
            return true;
        }
    }

    public boolean registrarPersonal(Usuario usuario) {
        String sql = "INSERT INTO Usuario (rut, clave, cargo) VALUES (?, ?, 'personal')";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getRut());
            ps.setString(2, usuario.getClave());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar personal: " + e.toString());
            return false;
        }
    }

    public boolean modificarPersonal(Usuario usuario) {
        String sql = "UPDATE Usuario SET rut=?, clave=?, cargo='personal' WHERE idUsuario=? AND cargo='personal'";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getRut());
            ps.setString(2, usuario.getClave());
            ps.setInt(3, usuario.getIdUsuario());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar personal: " + e.toString());
            return false;
        }
    }

    public boolean eliminarPersonal(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE idUsuario=? AND cargo='personal'";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar personal: " + e.toString());
            return false;
        }
    }
}
