package com.mycompany.transporteshirata.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/Transportes_Hirata_v2";
    private static final String USUARIO = "root";
<<<<<<< HEAD
    private static final String CONTRASENA = "admin123";
=======
    private static final String CONTRASENA = "Stomas.2023";
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be

    public static Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion a BD Hirata: " + e.getMessage());
            return null;
        }
    }
}
