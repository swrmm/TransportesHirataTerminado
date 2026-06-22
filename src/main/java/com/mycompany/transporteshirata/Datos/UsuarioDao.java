package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Usuario;
<<<<<<< HEAD
import java.sql.*;
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

<<<<<<< HEAD
/**
 * Clase de Acceso a Datos (DAO) para la gestión de credenciales y roles de actores.
 * Gestiona el Login y la persistencia de las cuentas que interactúan con el sistema.
 */
public class UsuarioDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     */
    public UsuarioDao() {
        this.con = Conexion.getConexion();
    }

  
    /**
     * Busca coincidencias de credenciales (RUT y clave) y extrae el cargo/rol del usuario administrativo.
     * @param rut El identificador del usuario (RUT).
     * @param clave La contraseña asociada al usuario.
     * @return Un objeto {@link Usuario} mapeado si las credenciales son válidas; null en caso contrario.
     */
    public Usuario validarUsuario(String rut, String clave) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE rut = ? AND clave = ?";
        try {
            con = Conexion.getConexion(); 
            ps = con.prepareStatement(sql);
            ps.setString(1, rut);
            ps.setString(2, clave);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setRut(rs.getString("rut"));
                usuario.setClave(rs.getString("clave"));
                usuario.setCargo(rs.getString("cargo"));
            }
        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }
        return usuario;
    }


    public boolean registrarUsuario(Usuario u) {
        if (u.getRut() == null || u.getRut().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El RUT no puede estar vacío.");
            return false;
        }
        String sql = "INSERT INTO Usuario (rut, clave, cargo) VALUES (?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getRut());
            ps.setString(2, u.getClave());
            ps.setString(3, u.getCargo());
            ps.execute();
            return true;
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "El RUT ingresado ya posee una cuenta en el sistema.");
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + ex.getMessage());
            return false;
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario ORDER BY cargo, rut";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setRut(rs.getString("rut"));
                u.setClave(rs.getString("clave"));
                u.setCargo(rs.getString("cargo"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar usuarios: " + ex.getMessage());
=======
public class UsuarioDao {

    public Usuario validarUsuario(String rut, String clave) {
        String sql = "SELECT * FROM Usuario WHERE rut = ? AND clave = ?";

        try (Connection conexion = Conexion.getConexion()) {
            if (conexion == null) {
                return null;
            }
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, rut);
                ps.setString(2, clave);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getInt("idUsuario"));
                        usuario.setRut(rs.getString("rut"));
                        usuario.setClave(rs.getString("clave"));
                        usuario.setCargo(rs.getString("cargo"));
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar usuario: " + e.toString());
        }
        return null;
    }

    public List<Usuario> listarPersonal() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario WHERE cargo = 'personal'";

        try (Connection conexion = Conexion.getConexion()) {
            if (conexion == null) {
                return lista;
            }
            try (PreparedStatement ps = conexion.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setRut(rs.getString("rut"));
                    usuario.setClave(rs.getString("clave"));
                    usuario.setCargo(rs.getString("cargo"));
                    lista.add(usuario);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar personal: " + e.toString());
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        }
        return lista;
    }

<<<<<<< HEAD
    public boolean modificarUsuario(Usuario u) {
        String sql = "UPDATE Usuario SET rut = ?, clave = ?, cargo = ? WHERE idUsuario = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getRut());
            ps.setString(2, u.getClave());
            ps.setString(3, u.getCargo());
            ps.setInt(4, u.getIdUsuario());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar usuario: " + ex.getMessage());
=======
    public boolean existeRut(String rut, int idIgnorar) {
        String sql = "SELECT idUsuario FROM Usuario WHERE rut = ? AND idUsuario <> ?";

        try (Connection conexion = Conexion.getConexion()) {
            if (conexion == null) {
                return true;
            }
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, rut);
                ps.setInt(2, idIgnorar);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar RUT de usuario: " + e.toString());
            return true;
        }
    }

    public boolean registrarPersonal(Usuario usuario) {
        String sql = "INSERT INTO Usuario (rut, clave, cargo) VALUES (?, ?, 'personal')";

        try (Connection conexion = Conexion.getConexion()) {
            if (conexion == null) {
                return false;
            }
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, usuario.getRut());
                ps.setString(2, usuario.getClave());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar personal: " + e.toString());
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            return false;
        }
    }

<<<<<<< HEAD
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar el usuario. Verifique restricciones de integridad.");
            return false;
        }
    }
}
=======
    public boolean modificarPersonal(Usuario usuario) {
        String sql = "UPDATE Usuario SET rut=?, clave=?, cargo='personal' WHERE idUsuario=? AND cargo='personal'";

        try (Connection conexion = Conexion.getConexion()) {
            if (conexion == null) {
                return false;
            }
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, usuario.getRut());
                ps.setString(2, usuario.getClave());
                ps.setInt(3, usuario.getIdUsuario());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar personal: " + e.toString());
            return false;
        }
    }

    public boolean eliminarPersonal(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE idUsuario=? AND cargo='personal'";

        try (Connection conexion = Conexion.getConexion()) {
            if (conexion == null) {
                return false;
            }
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar personal: " + e.toString());
            return false;
        }
    }
}
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
