package com.mycompany.transporteshirata.Datos;

import com.mycompany.transporteshirata.Logica.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PiezaDao {

    public List<Pieza> listarPiezas() {
        List<Pieza> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pieza ORDER BY nombre";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return lista;
            }
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPieza(rs));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar piezas: " + e.toString());
        }
        return lista;
    }

    public boolean existeCodigo(String codigo, int idIgnorar) {
        String sql = "SELECT idPieza FROM Pieza WHERE codigo = ? AND idPieza <> ?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return true;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, codigo);
                ps.setInt(2, idIgnorar);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar codigo de pieza: " + e.toString());
            return true;
        }
    }

    public Pieza buscarPorId(int idPieza) {
        String sql = "SELECT * FROM Pieza WHERE idPieza=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return null;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idPieza);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapearPieza(rs);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar pieza: " + e.toString());
        }
        return null;
    }

    public boolean registrarPieza(Pieza p) {
        String sql = "INSERT INTO Pieza (codigo, nombre, categoria, marca, stockActual, stockMinimo, ubicacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, p.getCodigo());
                ps.setString(2, p.getNombre());
                ps.setString(3, p.getCategoria());
                ps.setString(4, p.getMarca());
                ps.setInt(5, p.getStockActual());
                ps.setInt(6, p.getStockMinimo());
                ps.setString(7, p.getUbicacion());
                ps.setString(8, obtenerEstadoPorStock(p.getStockActual(), p.getStockMinimo(), p.getEstado()));
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar pieza: " + e.toString());
            return false;
        }
    }

    public boolean modificarPieza(Pieza p) {
        String sql = "UPDATE Pieza SET codigo=?, nombre=?, categoria=?, marca=?, stockActual=?, stockMinimo=?, ubicacion=?, estado=? WHERE idPieza=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, p.getCodigo());
                ps.setString(2, p.getNombre());
                ps.setString(3, p.getCategoria());
                ps.setString(4, p.getMarca());
                ps.setInt(5, p.getStockActual());
                ps.setInt(6, p.getStockMinimo());
                ps.setString(7, p.getUbicacion());
                ps.setString(8, obtenerEstadoPorStock(p.getStockActual(), p.getStockMinimo(), p.getEstado()));
                ps.setInt(9, p.getIdPieza());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar pieza: " + e.toString());
            return false;
        }
    }

    public boolean eliminarPieza(int idPieza) {
        String sql = "DELETE FROM Pieza WHERE idPieza=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idPieza);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar pieza: " + e.toString());
            return false;
        }
    }

    public boolean actualizarStock(int idPieza, int nuevoStock) {
        String sql = "UPDATE Pieza SET stockActual=?, "
                + "estado=CASE "
                + "WHEN ? <= 0 THEN 'sin stock' "
                + "WHEN ? <= stockMinimo THEN 'stock bajo' "
                + "ELSE 'disponible' END "
                + "WHERE idPieza=?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                return false;
            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, nuevoStock);
                ps.setInt(2, nuevoStock);
                ps.setInt(3, nuevoStock);
                ps.setInt(4, idPieza);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar stock: " + e.toString());
            return false;
        }
    }

    private Pieza mapearPieza(ResultSet rs) throws SQLException {
        Pieza p = new Pieza();
        p.setIdPieza(rs.getInt("idPieza"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombre(rs.getString("nombre"));
        p.setCategoria(rs.getString("categoria"));
        p.setMarca(rs.getString("marca"));
        p.setStockActual(rs.getInt("stockActual"));
        p.setStockMinimo(rs.getInt("stockMinimo"));
        p.setUbicacion(rs.getString("ubicacion"));
        p.setEstado(rs.getString("estado"));
        return p;
    }

    private String obtenerEstadoPorStock(int stockActual, int stockMinimo, String estadoFormulario) {
        if (stockActual <= 0) {
            return "sin stock";
        }
        if (stockActual <= stockMinimo) {
            return "stock bajo";
        }
        if (estadoFormulario == null || estadoFormulario.trim().isEmpty() || "sin stock".equals(estadoFormulario)) {
            return "disponible";
        }
        return estadoFormulario;
    }
}
