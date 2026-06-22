<<<<<<< HEAD
package com.mycompany.transporteshirata.Datos;

=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transporteshirata.Datos;
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
import com.mycompany.transporteshirata.Logica.Camion;
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
 * Clase de Acceso a Datos (DAO) para la gestión de la flota de camiones.
 * Administra el CRUD de vehículos y la asignación correspondiente de conductores.
 */
public class CamionDao {

=======
/**
 *
 * @author pccas
 */
public class CamionDao {
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

<<<<<<< HEAD
    /**
     * Recupera la lista completa de camiones registrados en el sistema,
     * incluyendo la información de sus conductores asignados mediante un LEFT JOIN.
     * * @return Lista de objetos {@link Camion}.
     */
    public List<Camion> listarCamiones() {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT c.*, d.rut, d.nombre FROM Camion c LEFT JOIN Conductor d ON c.idConductor = d.idConductor";
=======
    // listar camiones con su conductor asignado
    public List<Camion> listarCamiones() {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT c.*, d.rut, d.nombre FROM Camion c LEFT JOIN Conductor d ON c.idConductor = d.idConductor";
        
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
<<<<<<< HEAD
=======
            
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            while (rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(rs.getInt("idCamion"));
                c.setPatente(rs.getString("patente"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setAnio(rs.getInt("anio"));
                c.setKilometrajeActual(rs.getInt("kilometrajeActual"));

                Conductor d = new Conductor();
                d.setIdConductor(rs.getInt("idConductor"));
                if (rs.getString("rut") != null) {
                    d.setRut(rs.getString("rut"));
                    d.setNombre(rs.getString("nombre"));
                } else {
                    d.setNombre("Sin Conductor");
                    d.setRut("");
                }
                c.setConductor(d);
<<<<<<< HEAD
=======
                
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar camiones: " + e.toString());
        }
        return lista;
    }

<<<<<<< HEAD
    /**
     * Inserta un nuevo camión en la base de datos.
     * Si no se selecciona un conductor (ID igual a 0), guarda el campo como NULL.
     * * @param c Objeto {@link Camion} con los datos a ingresar.
     * @return {@code true} si el camión se registró con éxito; {@code false} si falla.
     */
=======
    
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public boolean registrarCamion(Camion c) {
        String sql = "INSERT INTO Camion (patente, marca, modelo, anio, kilometrajeActual, idConductor) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getPatente());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());
            ps.setInt(4, c.getAnio());
            ps.setInt(5, c.getKilometrajeActual());
<<<<<<< HEAD
            if (c.getConductor().getIdConductor() == 0) {
=======
            
            // si el id es 0, significa que no se selecciono   conductor
            if(c.getConductor().getIdConductor() == 0) {
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, c.getConductor().getIdConductor());
            }
<<<<<<< HEAD
=======
            
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar (¿Patente duplicada?): " + e.toString());
            return false;
        }
    }

<<<<<<< HEAD
    /**
     * Modifica los datos técnicos y el conductor asignado de un camión existente.
     * * @param c Objeto {@link Camion} con la información actualizada.
     * @return {@code true} si la modificación se efectuó; {@code false} en caso de error.
     */
    public boolean modificarCamion(Camion c) {
        String sql = "UPDATE Camion SET patente=?, marca=?, modelo=?, anio=?, idConductor=? WHERE idCamion=?";
=======
   
    public boolean modificarCamion(Camion c) {
        String sql = "UPDATE Camion SET patente=?, marca=?, modelo=?, anio=?, kilometrajeActual=?, idConductor=? WHERE idCamion=?";
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getPatente());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());
            ps.setInt(4, c.getAnio());
<<<<<<< HEAD
            if (c.getConductor().getIdConductor() == 0) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, c.getConductor().getIdConductor());
            }
            ps.setInt(6, c.getIdCamion());
=======
            
            ps.setInt(5, c.getKilometrajeActual());
            
            if(c.getConductor().getIdConductor() == 0) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, c.getConductor().getIdConductor());
            }
            
            ps.setInt(7, c.getIdCamion());
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.toString());
            return false;
        }
    }

<<<<<<< HEAD
    /**
     * Actualiza el kilometraje actual de un vehículo específico.
     * Utilizado principalmente en la interfaz del conductor.
     * * @param idCamion Identificador único del camión.
     * @param nuevoKilometraje El nuevo valor numérico del kilometraje.
     * @return {@code true} si la actualización fue exitosa; {@code false} de lo contrario.
     */
=======
    
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public boolean actualizarKilometraje(int idCamion, int nuevoKilometraje) {
        String sql = "UPDATE Camion SET kilometrajeActual = ? WHERE idCamion = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nuevoKilometraje);
            ps.setInt(2, idCamion);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar kilometraje: " + e.toString());
            return false;
        }
    }

<<<<<<< HEAD
    /**
     * Elimina un camión del sistema utilizando su identificador.
     * * @param id Identificador único del camión a eliminar.
     * @return {@code true} si el registro fue borrado; {@code false} si ocurre una violación de restricción.
     */
=======

>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public boolean eliminarCamion(int id) {
        String sql = "DELETE FROM Camion WHERE idCamion=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar camión: " + e.toString());
            return false;
        }
    }
<<<<<<< HEAD
}
=======

  
}
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
