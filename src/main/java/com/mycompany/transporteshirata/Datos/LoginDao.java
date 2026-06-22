<<<<<<< HEAD
package com.mycompany.transporteshirata.Datos;

=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Conductor;
import com.mycompany.transporteshirata.Logica.Login;
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import javax.swing.JOptionPane;

/**
 * Clase de Acceso a Datos (DAO) encargada de los procesos de autenticación básicos del personal conductor.
=======
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * /**
 *
 * @author danie
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
 */
public class LoginDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

<<<<<<< HEAD
    /**
     * Valida si las credenciales de un conductor coinciden con los registros de la base de datos.
     * * @param rut El identificador único (RUT) asociado al conductor.
     * @param contrasenaIngresada El intento de contraseña proporcionado en la interfaz.
     * @return {@code true} si el RUT existe y las contraseñas coinciden de forma exacta; {@code false} de lo contrario.
     */
    public boolean validarCredenciales(String rut, String contrasenaIngresada) {
        String sql = "SELECT clave FROM Conductor WHERE rut = ?";
=======
    public boolean validarCredenciales(String rut, String contrasenaIngresada) {
        
        String sql = "SELECT clave FROM Conductor WHERE rut = ?";

>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();

            if (rs.next()) {
                String contrasenaAlmacenada = rs.getString("clave");
                return contrasenaAlmacenada.equals(contrasenaIngresada);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar credenciales: " + e.toString());
        }
        return false;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
