/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Equipo;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
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
public class MantenimientoEquipoDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<MantenimientoEquipo> listarMantenimientosEquipo() {
        List<MantenimientoEquipo> lista = new ArrayList<>();
        String sql = "SELECT me.*, e.tipo AS tipoEquipo, e.codigo, e.marca, e.ubicacion, e.estado AS estadoEquipo "
                + "FROM MantenimientoEquipo me INNER JOIN equipos e ON me.idEquipo = e.idEquipo";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MantenimientoEquipo m = new MantenimientoEquipo();
                m.setIdMantenimientoEquipo(rs.getInt("idMantenimientoEquipo"));
                m.setFechaMantenimiento(rs.getDate("fechaMantenimiento").toLocalDate());
                m.setTipoMantenimiento(rs.getString("tipoMantenimiento"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setTecnicoResponsable(rs.getString("tecnicoResponsable"));
                m.setObservaciones(rs.getString("observaciones"));
                m.setEstado(rs.getString("estado"));

                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("idEquipo"));
                e.setTipo(rs.getString("tipoEquipo"));
                e.setCodigo(rs.getString("codigo"));
                e.setMarca(rs.getString("marca"));
                e.setUbicacion(rs.getString("ubicacion"));
                e.setEstado(rs.getString("estadoEquipo"));
                m.setEquipo(e);

                lista.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar historial de equipos: " + e.toString());
        }
        return lista;
    }

    public boolean registrarMantenimientoEquipo(MantenimientoEquipo m) {
        String sql = "INSERT INTO MantenimientoEquipo "
                + "(fechaMantenimiento, tipoMantenimiento, descripcion, tecnicoResponsable, observaciones, estado, idEquipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(m.getFechaMantenimiento()));
            ps.setString(2, m.getTipoMantenimiento());
            ps.setString(3, m.getDescripcion());
            ps.setString(4, m.getTecnicoResponsable());
            ps.setString(5, m.getObservaciones());
            ps.setString(6, m.getEstado());
            ps.setInt(7, m.getEquipo().getIdEquipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar mantenimiento de equipo: " + e.toString());
            return false;
        }
    }
}
