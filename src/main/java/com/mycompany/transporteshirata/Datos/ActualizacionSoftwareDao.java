package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.ActualizacionSoftware;
import com.mycompany.transporteshirata.Logica.EquipoOficina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            return false;
        }
    }

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