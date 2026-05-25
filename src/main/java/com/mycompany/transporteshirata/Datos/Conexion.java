package com.mycompany.transporteshirata.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/Transportes_Hirata_v2";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "Stomas.2023";

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
