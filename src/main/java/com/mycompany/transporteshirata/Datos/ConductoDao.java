<<<<<<< HEAD
package com.mycompany.transporteshirata.Datos;

=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transporteshirata.Datos;
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
import com.mycompany.transporteshirata.Logica.Conductor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
<<<<<<< HEAD

/**
 * Clase de Acceso a Datos (DAO) para el control del personal de conducción.
 * Realiza las operaciones CRUD relativas a la tabla Conductor.
 */
public class ConductoDao {

=======
/**
 *
 * @author pccas
 */
public class ConductoDao {
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

<<<<<<< HEAD
    /**
     * Lista todos los conductores registrados con sus datos completos de credenciales y contacto.
     * * @return Una lista de objetos {@link Conductor}.
     */
=======
 
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public List<Conductor> listarConductores() {
        List<Conductor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Conductor";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Conductor c = new Conductor();
                c.setIdConductor(rs.getInt("idConductor"));
                c.setRut(rs.getString("rut"));
                c.setNombre(rs.getString("nombre"));
                c.setLicencia(rs.getString("licencia"));
                c.setTelefono(rs.getString("telefono"));
                c.setClave(rs.getString("clave"));
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar conductores: " + e.toString());
        }
        return lista;
    }

<<<<<<< HEAD
    /**
     * Registra un nuevo conductor en el sistema.
     * * @param c Objeto {@link Conductor} que contiene los parámetros validados.
     * @return {@code true} si la inserción fue correcta; {@code false} si falla.
     */
    public boolean registrarConductor(Conductor c) {
=======

    public boolean registrarConductor(Conductor c) {
        
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        String sql = "INSERT INTO Conductor (rut, nombre, licencia, telefono, clave) VALUES (?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getRut());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getLicencia());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getClave()); 
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar : " + e.toString());
            return false;
<<<<<<< HEAD
        }
    }

    /**
     * Modifica la información de un conductor existente a partir de su ID.
     * * @param c Objeto {@link Conductor} con las modificaciones incorporadas.
     * @return {@code true} si los datos fueron guardados; {@code false} ante una anomalía de red o SQL.
     */
    public boolean modificarConductor(Conductor c) {
=======
    }
    }

    public boolean modificarConductor(Conductor c) {
        
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        String sql = "UPDATE Conductor SET rut=?, nombre=?, licencia=?, telefono=?, clave=? WHERE idConductor=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getRut());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getLicencia());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getClave()); 
            ps.setInt(6, c.getIdConductor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.toString());
            return false;
        }
    }

<<<<<<< HEAD
    /**
     * Elimina un conductor del registro por su ID único.
     * Previene el borrado si el conductor está referenciado como clave foránea en la tabla Camion.
     * * @param id Identificador numérico del conductor.
     * @return {@code true} si se eliminó de manera conforme; {@code false} en caso de restricción relacional.
     */
=======
 
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public boolean eliminarConductor(int id) {
        String sql = "DELETE FROM Conductor WHERE idConductor=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar. Probablemente tiene un camión asignado.");
            return false;
        }
    }

<<<<<<< HEAD
    /**
     * Lista resumida de conductores para poblar selectores visuales (ComboBox).
     * Solo extrae el RUT y el Nombre del trabajador para optimizar la carga.
     * * @return Lista de objetos {@link Conductor} simplificados.
     */
=======
   
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public List<Conductor> listarConductoresCbm() {
        List<Conductor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Conductor";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Conductor c = new Conductor();
                c.setRut(rs.getString("rut"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar conductores: " + e.toString());
        }
        return lista;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
