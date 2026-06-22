<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Camion;
import com.mycompany.transporteshirata.Logica.Mantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
<<<<<<< HEAD
 * Clase de Acceso a Datos (DAO) para el control técnico vehicular de la empresa.
 * Maneja el ciclo de vida de los registros de mantención física aplicados a la flota de camiones.
=======
 *
 * @author pccas
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
 */
public class MantenimientoDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

<<<<<<< HEAD
    /**
     * Obtiene el listado de todos los mantenimientos vehiculares incorporando
     * datos del camión respectivo a través de una operación INNER JOIN.
     * * @return Lista estructurada de objetos {@link Mantenimiento}.
     */
    public List<Mantenimiento> listarMantenimientos() {
        List<Mantenimiento> lista = new ArrayList<>();
        String sql = "SELECT m.*, c.patente, c.marca, c.modelo FROM Mantenimiento m INNER JOIN Camion c ON m.idCamion = c.idCamion";
=======
    public List<Mantenimiento> listarMantenimientos() {
        List<Mantenimiento> lista = new ArrayList<>();
        String sql = "SELECT m.*, c.patente, c.marca, c.modelo FROM Mantenimiento m INNER JOIN Camion c ON m.idCamion = c.idCamion";

>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
<<<<<<< HEAD
=======

>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            while (rs.next()) {
                Mantenimiento m = new Mantenimiento();
                m.setIdMantenimiento(rs.getInt("idMantenimiento"));
                m.setFecha(rs.getDate("fecha").toLocalDate());
                m.setTipo(rs.getString("tipo"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setKilometrajeMantenimiento(rs.getInt("kilometrajeMantenimiento"));

                Camion c = new Camion();
                c.setIdCamion(rs.getInt("idCamion"));
                c.setPatente(rs.getString("patente"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                m.setCamion(c);

                lista.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar mantenimientos: " + e.toString());
        }
        return lista;
    }

<<<<<<< HEAD
    /**
     * Registra una nueva bitácora de mantenimiento vehicular para un camión de la flota.
     * * @param m Instancia de {@link Mantenimiento} con los parámetros técnicos requeridos.
     * @return {@code true} si la inserción fue correcta; {@code false} si ocurre una falla técnica.
     */
=======
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public boolean registrarMantenimiento(Mantenimiento m) {
        String sql = "INSERT INTO Mantenimiento (fecha, tipo, descripcion, kilometrajeMantenimiento, idCamion) VALUES (?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(m.getFecha()));
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getDescripcion());
            ps.setInt(4, m.getKilometrajeMantenimiento());
            ps.setInt(5, m.getCamion().getIdCamion());
<<<<<<< HEAD
=======

>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar mantenimiento: " + e.toString());
            return false;
        }
    }

<<<<<<< HEAD
    /**
     * Modifica de manera integral los atributos de un registro de mantenimiento preexistente.
     * * @param m Objeto {@link Mantenimiento} con los datos editados.
     * @return {@code true} si la actualización fue exitosa; {@code false} ante un fallo.
     */
=======
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public boolean modificarMantenimiento(Mantenimiento m) {
        String sql = "UPDATE Mantenimiento SET fecha=?, tipo=?, descripcion=?, kilometrajeMantenimiento=?, idCamion=? WHERE idMantenimiento=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(m.getFecha()));
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getDescripcion());
            ps.setInt(4, m.getKilometrajeMantenimiento());
            ps.setInt(5, m.getCamion().getIdCamion());
            ps.setInt(6, m.getIdMantenimiento());
<<<<<<< HEAD
=======

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
     * Elimina una entrada de mantenimiento utilizando su identificador numérico.
     * * @param id Identificador numérico único de la mantención.
     * @return {@code true} si el registro fue destruido; {@code false} si falla la instrucción.
     */
=======
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public boolean eliminarMantenimiento(int id) {
        String sql = "DELETE FROM Mantenimiento WHERE idMantenimiento=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.toString());
            return false;
        }
    }

<<<<<<< HEAD
    /**
     * Consulta el kilometraje máximo registrado hasta la fecha para un camión específico.
     * Útil para validar intervalos mínimos entre servicios.
     * * @param idCamion Identificador del camión a evaluar.
     * @return El kilometraje más alto en forma de entero; {@code 0} si no existen registros previos.
     */
    public int obtenerUltimoKilometrajeMantenimiento(int idCamion) {
        int ultimoKm = 0;
=======
    public int obtenerUltimoKilometrajeMantenimiento(int idCamion) {
        int ultimoKm = 0;
        
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        String sql = "SELECT MAX(kilometrajeMantenimiento) AS ultimo FROM Mantenimiento WHERE idCamion = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCamion);
            rs = ps.executeQuery();
            if (rs.next()) {
                ultimoKm = rs.getInt("ultimo");
            }
        } catch (SQLException e) {
            System.out.println("Error en obtenerUltimoKilometrajeMantenimiento: " + e.getMessage());
        }
        return ultimoKm;
    }
<<<<<<< HEAD

    /**
     * Modifica los pormenores de un mantenimiento sin alterar la vinculación física del camión.
     * * @param m Parámetro de tipo {@link Mantenimiento}.
     * @return {@code true} si los campos sufrieron la mutación deseada; {@code false} si falla.
     */
=======
    
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    public boolean modificarDetallesMantenimiento(Mantenimiento m) {
        String sql = "UPDATE Mantenimiento SET fecha=?, tipo=?, descripcion=?, kilometrajeMantenimiento=? WHERE idMantenimiento=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(m.getFecha()));
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getDescripcion());
            ps.setInt(4, m.getKilometrajeMantenimiento());
            ps.setInt(5, m.getIdMantenimiento());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar detalles: " + e.toString());
            return false;
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
