package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.ActualizacionSoftware;
<<<<<<< HEAD
import com.mycompany.transporteshirata.Logica.EquipoOficina;
import java.sql.*;
=======
import com.mycompany.transporteshirata.Logica.Equipo;
import com.mycompany.transporteshirata.Logica.Software;
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
 * Clase de Acceso a Datos (DAO) para la gestión de actualizaciones de software.
 * Permite registrar, listar y buscar el historial de versiones en los equipos.
 * Cumple con el requerimiento funcional RF-07.
 */
public class ActualizacionSoftwareDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Registra un nuevo evento de actualización de software en la base de datos.
     * Valida que el equipo, el nombre del software y la versión nueva no sean nulos.
     * * @param a Objeto {@link ActualizacionSoftware} con la información a registrar.
     * @return {@code true} si el registro fue exitoso; {@code false} en caso contrario.
     */
    public boolean registrarActualizacion(ActualizacionSoftware a) {
        if (a.getEquipo() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un equipo.");
            return false;
        }
        if (a.getNombreSoftware() == null || a.getNombreSoftware().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del software no puede estar vacío.");
            return false;
        }
        if (a.getVersionNueva() == null || a.getVersionNueva().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la versión nueva.");
            return false;
        }

        String sql = "INSERT INTO ActualizacionSoftware "
                   + "(idEquipo, nombreSoftware, versionAnterior, versionNueva, fechaActualizacion, responsable) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, a.getEquipo().getIdEquipo());
            ps.setString(2, a.getNombreSoftware());
            ps.setString(3, a.getVersionAnterior());
            ps.setString(4, a.getVersionNueva());
            ps.setDate(5, Date.valueOf(a.getFechaActualizacion()));
            ps.setString(6, a.getResponsable());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar actualización: " + ex.getMessage());
=======
public class ActualizacionSoftwareDao {

    public boolean registrarActualizacion(ActualizacionSoftware a) {
        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);
            try {
                insertarActualizacion(con, a);
                if ("realizada".equals(a.getEstado())) {
                    actualizarSoftwareEquipo(con, a);
                    actualizarVersionActual(con, a.getSoftware().getIdSoftware(), a.getVersionNueva());
                }
                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Error al registrar actualizacion de software: " + e.getMessage());
                return false;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar actualizacion de software: " + e.toString());
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            return false;
        }
    }

<<<<<<< HEAD
    /**
     * Obtiene la lista completa de todas las actualizaciones de software registradas.
     * Incluye los datos relacionales del equipo asociado mediante un INNER JOIN.
     * * @return Una lista de objetos {@link ActualizacionSoftware} ordenadas por fecha de forma descendente.
     */
    public List<ActualizacionSoftware> listarActualizaciones() {
        List<ActualizacionSoftware> lista = new ArrayList<>();
        String sql = "SELECT a.*, eq.nombre, eq.tipo, eq.marca, eq.modelo "
                   + "FROM ActualizacionSoftware a "
                   + "INNER JOIN EquipoOficina eq ON a.idEquipo = eq.idEquipo "
                   + "ORDER BY a.fechaActualizacion DESC";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar actualizaciones: " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Busca registros de actualización filtrando por coincidencia parcial en el nombre del software.
     * * @param nombreSoftware Cadena de texto con el nombre del software a buscar.
     * @return Una lista de objetos {@link ActualizacionSoftware} que coinciden con el criterio de búsqueda.
     */
    public List<ActualizacionSoftware> buscarPorSoftware(String nombreSoftware) {
        List<ActualizacionSoftware> lista = new ArrayList<>();
        String sql = "SELECT a.*, eq.nombre, eq.tipo, eq.marca, eq.modelo "
                   + "FROM ActualizacionSoftware a "
                   + "INNER JOIN EquipoOficina eq ON a.idEquipo = eq.idEquipo "
                   + "WHERE a.nombreSoftware LIKE ? "
                   + "ORDER BY a.fechaActualizacion DESC";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombreSoftware + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar actualizaciones: " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Convierte una fila del ResultSet en un objeto de tipo ActualizacionSoftware.
     * * @param rs El {@link ResultSet} posicionado en la fila actual.
     * @return Un objeto {@link ActualizacionSoftware} completamente mapeado.
     * @throws SQLException Si ocurre un error al extraer los datos del ResultSet.
     */
    private ActualizacionSoftware mapearFila(ResultSet rs) throws SQLException {
        ActualizacionSoftware a = new ActualizacionSoftware();
        a.setIdActualizacion(rs.getInt("idActualizacion"));
        a.setNombreSoftware(rs.getString("nombreSoftware"));
        a.setVersionAnterior(rs.getString("versionAnterior"));
        a.setVersionNueva(rs.getString("versionNueva"));
        a.setFechaActualizacion(rs.getDate("fechaActualizacion").toLocalDate());
        a.setResponsable(rs.getString("responsable"));

        EquipoOficina eq = new EquipoOficina();
        eq.setIdEquipo(rs.getInt("idEquipo"));
        eq.setNombre(rs.getString("nombre"));
        eq.setTipo(rs.getString("tipo"));
        eq.setMarca(rs.getString("marca"));
        eq.setModelo(rs.getString("modelo"));
        a.setEquipo(eq);

        return a;
    }
    /**
     * Filtra el historial de versiones de software según el identificador de un equipo.
     * @param idEquipo Identificador único del equipo de oficina.
     * @return Lista cronológica de actualizaciones aplicadas al activo.
     */
    public List<ActualizacionSoftware> listarPorEquipo(int idEquipo) {
        List<ActualizacionSoftware> lista = new ArrayList<>();
        String sql = "SELECT a.*, eq.nombre, eq.tipo, eq.marca, eq.modelo "
                   + "FROM ActualizacionSoftware a "
                   + "INNER JOIN EquipoOficina eq ON a.idEquipo = eq.idEquipo "
                   + "WHERE a.idEquipo = ? "
                   + "ORDER BY a.fechaActualizacion DESC";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEquipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                ActualizacionSoftware a = new ActualizacionSoftware();
                a.setIdActualizacion(rs.getInt("idActualizacion"));
                a.setNombreSoftware(rs.getString("nombreSoftware"));
                a.setVersionAnterior(rs.getString("versionAnterior"));
                a.setVersionNueva(rs.getString("versionNueva"));
                
                java.sql.Date sqlDate = rs.getDate("fechaActualizacion");
                if (sqlDate != null) {
                    a.setFechaActualizacion(sqlDate.toLocalDate());
                }
                
                a.setResponsable(rs.getString("responsable"));

                EquipoOficina eq = new EquipoOficina();
                eq.setIdEquipo(rs.getInt("idEquipo"));
                eq.setNombre(rs.getString("nombre"));
                eq.setTipo(rs.getString("tipo"));
                eq.setMarca(rs.getString("marca"));
                eq.setModelo(rs.getString("modelo"));
                a.setEquipo(eq);

                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar software por equipo: " + ex.getMessage());
        }
        return lista;
    }
}
=======
    public String obtenerVersionInstalada(int idEquipo, int idSoftware) {
        String sql = "SELECT versionInstalada FROM SoftwareEquipo WHERE idEquipo=? AND idSoftware=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return "";
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idEquipo);
                ps.setInt(2, idSoftware);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("versionInstalada");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar version instalada: " + e.toString());
        }
        return "";
    }

    public List<ActualizacionSoftware> listarActualizaciones() {
        List<ActualizacionSoftware> lista = new ArrayList<>();
        String sql = "SELECT a.*, e.codigo, e.tipo AS tipoEquipo, s.nombre, s.versionActual, s.proveedor, s.tipo AS tipoSoftware, s.estado AS estadoSoftware "
                + "FROM ActualizacionSoftware a "
                + "INNER JOIN equipos e ON a.idEquipo = e.idEquipo "
                + "INNER JOIN Software s ON a.idSoftware = s.idSoftware "
                + "ORDER BY a.fechaActualizacion DESC, a.idActualizacion DESC";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return lista;
            }
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ActualizacionSoftware a = new ActualizacionSoftware();
                    a.setIdActualizacion(rs.getInt("idActualizacion"));
                    a.setFechaActualizacion(rs.getDate("fechaActualizacion").toLocalDate());
                    a.setVersionAnterior(rs.getString("versionAnterior"));
                    a.setVersionNueva(rs.getString("versionNueva"));
                    a.setTecnicoResponsable(rs.getString("tecnicoResponsable"));
                    a.setEstado(rs.getString("estado"));
                    a.setObservaciones(rs.getString("observaciones"));

                    Equipo e = new Equipo();
                    e.setIdEquipo(rs.getInt("idEquipo"));
                    e.setCodigo(rs.getString("codigo"));
                    e.setTipo(rs.getString("tipoEquipo"));
                    a.setEquipo(e);

                    Software s = new Software();
                    s.setIdSoftware(rs.getInt("idSoftware"));
                    s.setNombre(rs.getString("nombre"));
                    s.setVersionActual(rs.getString("versionActual"));
                    s.setProveedor(rs.getString("proveedor"));
                    s.setTipo(rs.getString("tipoSoftware"));
                    s.setEstado(rs.getString("estadoSoftware"));
                    a.setSoftware(s);

                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar actualizaciones: " + e.toString());
        }
        return lista;
    }

    private void insertarActualizacion(Connection con, ActualizacionSoftware a) throws SQLException {
        String sql = "INSERT INTO ActualizacionSoftware "
                + "(fechaActualizacion, versionAnterior, versionNueva, tecnicoResponsable, estado, observaciones, idEquipo, idSoftware) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(a.getFechaActualizacion()));
            ps.setString(2, a.getVersionAnterior());
            ps.setString(3, a.getVersionNueva());
            ps.setString(4, a.getTecnicoResponsable());
            ps.setString(5, a.getEstado());
            ps.setString(6, a.getObservaciones());
            ps.setInt(7, a.getEquipo().getIdEquipo());
            ps.setInt(8, a.getSoftware().getIdSoftware());
            ps.executeUpdate();
        }
    }

    private void actualizarSoftwareEquipo(Connection con, ActualizacionSoftware a) throws SQLException {
        String sql = "INSERT INTO SoftwareEquipo "
                + "(idEquipo, idSoftware, versionInstalada, fechaInstalacion, estado) "
                + "VALUES (?, ?, ?, ?, 'actualizado') "
                + "ON DUPLICATE KEY UPDATE versionInstalada=VALUES(versionInstalada), "
                + "fechaInstalacion=VALUES(fechaInstalacion), estado='actualizado'";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getEquipo().getIdEquipo());
            ps.setInt(2, a.getSoftware().getIdSoftware());
            ps.setString(3, a.getVersionNueva());
            ps.setDate(4, java.sql.Date.valueOf(a.getFechaActualizacion()));
            ps.executeUpdate();
        }
    }

    private void actualizarVersionActual(Connection con, int idSoftware, String versionNueva) throws SQLException {
        String sql = "UPDATE Software SET versionActual=? WHERE idSoftware=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, versionNueva);
            ps.setInt(2, idSoftware);
            ps.executeUpdate();
        }
    }
}
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
