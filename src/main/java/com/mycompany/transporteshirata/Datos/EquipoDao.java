/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Equipo;
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
public class EquipoDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Equipo> listarEquipos() {
        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipos";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("idEquipo"));
                e.setTipo(rs.getString("tipo"));
                e.setCodigo(rs.getString("codigo"));
                e.setMarca(rs.getString("marca"));
                e.setUbicacion(rs.getString("ubicacion"));
                e.setEstado(rs.getString("estado"));
                lista.add(e);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar equipos: " + e.toString());
        }
        return lista;
    }

    public boolean existeEquipo(int idEquipo) {
        String sql = "SELECT idEquipo FROM equipos WHERE idEquipo = ?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEquipo);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar equipo: " + e.toString());
            return false;
        }
    }

    public boolean existeCodigo(String codigo, int idIgnorar) {
        String sql = "SELECT idEquipo FROM equipos WHERE codigo = ? AND idEquipo <> ?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setInt(2, idIgnorar);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar codigo de equipo: " + e.toString());
            return true;
        }
    }

    public int obtenerSiguienteNumeroCodigo(String prefijo) {
        int ultimoNumero = 0;
        String sql = "SELECT codigo FROM equipos WHERE codigo LIKE ?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prefijo + "-%");
            rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                if (codigo != null && codigo.startsWith(prefijo + "-")) {
                    try {
                        int numero = Integer.parseInt(codigo.substring(prefijo.length() + 1));
                        if (numero > ultimoNumero) {
                            ultimoNumero = numero;
                        }
                    } catch (NumberFormatException e) {
                        // Ignora codigos antiguos que no usan el formato automatico.
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar codigo de equipo: " + e.toString());
        }

        return ultimoNumero + 1;
    }

    public boolean registrarEquipo(Equipo e) {
        String sql = "INSERT INTO equipos (tipo, codigo, marca, ubicacion, estado) VALUES (?, ?, ?, ?, ?)";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getTipo());
            ps.setString(2, e.getCodigo());
            ps.setString(3, e.getMarca());
            ps.setString(4, e.getUbicacion());
            ps.setString(5, e.getEstado());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar equipo: " + ex.toString());
            return false;
        }
    }

    public boolean modificarEquipo(Equipo e) {
        String sql = "UPDATE equipos SET tipo=?, codigo=?, marca=?, ubicacion=?, estado=? WHERE idEquipo=?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getTipo());
            ps.setString(2, e.getCodigo());
            ps.setString(3, e.getMarca());
            ps.setString(4, e.getUbicacion());
            ps.setString(5, e.getEstado());
            ps.setInt(6, e.getIdEquipo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar equipo: " + ex.toString());
            return false;
        }
    }

    public boolean eliminarEquipo(int idEquipo) {
        String sql = "DELETE FROM equipos WHERE idEquipo=?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEquipo);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar equipo: " + e.toString());
            return false;
        }
    }
}
