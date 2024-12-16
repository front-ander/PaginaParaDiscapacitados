/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author dnayj
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Logica.DetalleVenta;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetalleVentaDAO {
    private static final Logger logger = Logger.getLogger(DetalleVentaDAO.class.getName());
    public boolean insertarDetalle(Connection conn, DetalleVenta detalle) {
        String sqlDetalleVenta = "INSERT INTO detalle_venta (ID, IDVEN, Ventas, Cantidad, Precio_Unitario, Subtotal, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement psDetalleVenta = null;

        try {
            psDetalleVenta = conn.prepareStatement(sqlDetalleVenta);
            psDetalleVenta.setString(1, detalle.getIdDetalleVenta());
            psDetalleVenta.setString(2, detalle.getIdVenta());
            psDetalleVenta.setString(3, detalle.getVentas());
            psDetalleVenta.setInt(4, detalle.getCantidad());
            psDetalleVenta.setDouble(5, detalle.getPrecioUnitario());
            psDetalleVenta.setDouble(6, detalle.getSubtotal());
            psDetalleVenta.setString(7, detalle.getEstado()); // Estado de la venta
            psDetalleVenta.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            // Cerrar el PreparedStatement
            try {
                if (psDetalleVenta != null) psDetalleVenta.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public String obtenerUltimoIdUsuario() {
    String ultimoId = null;
    String query = "SELECT ID FROM detalle_venta ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            ultimoId = rs.getString("ID");
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener el último ID de usuario", e);
    }
    return ultimoId;
}
    
    public List<DetalleVenta> obtenerDetalleVentaPorId(String id) {
    List<DetalleVenta> ventax = new ArrayList<>();
    String query = """
            SELECT 
                v.ID, 
                v.IDVEN, 
                CASE
                    WHEN v.Ventas LIKE 'MAC%' THEN (SELECT m.Nombre FROM mascota m WHERE m.ID = v.Ventas)
                    WHEN v.Ventas LIKE 'PRO%' THEN (SELECT p.Nombre FROM producto p WHERE p.ID = v.Ventas)
                    WHEN v.Ventas LIKE 'SER%' THEN (SELECT s.Nombre FROM servicio s WHERE s.ID = v.Ventas)
                    ELSE 'Desconocido'
                END AS Ventas,
                v.Cantidad, 
                v.Precio_Unitario, 
                v.Subtotal, 
                c.Descripcion AS Estado  
            FROM 
                detalle_venta v 
            JOIN 
                venta u ON v.IDVEN = u.ID 
            JOIN 
                estado c ON v.Estado = c.ID 
            WHERE 
                v.IDVEN = ?;
            """;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DetalleVenta venta = new DetalleVenta();
                venta.setIdDetalleVenta(rs.getString("ID"));
                venta.setIdVenta(rs.getString("IDVEN"));
                venta.setVentas(rs.getString("Ventas")); // Ahora contiene solo el Nombre
                venta.setCantidad(rs.getInt("Cantidad"));
                venta.setPrecioUnitario(rs.getDouble("Precio_Unitario"));
                venta.setSubtotal(rs.getDouble("Subtotal"));
                venta.setEstado(rs.getString("Estado"));
                ventax.add(venta);
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener detalles de venta por ID", e);
    }

    return ventax; // Retornar lista vacía si no se encontraron resultados
}
public boolean actualizarUsuario(DetalleVenta usuario) {
        String query = """
            UPDATE detalle_venta 
            SET Estado = ?
            WHERE ID = ?
        """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, usuario.getEstado());
            ps.setString(2, usuario.getIdDetalleVenta());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar usuario", e);
        }
        return false;
    }
public boolean insertarDetalles(DetalleVenta detalleVenta) {
        String query = "INSERT INTO detalle_venta (ID, IDVENTA, Ventas, Cantidad, Precio_Unitario, Subtotal, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, detalleVenta.getIdDetalleVenta());
            ps.setString(2, detalleVenta.getIdVenta());
            ps.setString(3, detalleVenta.getVentas());
            ps.setInt(4, detalleVenta.getCantidad());
            ps.setDouble(5, detalleVenta.getPrecioUnitario());
            ps.setDouble(6, detalleVenta.getSubtotal());
            ps.setString(7, detalleVenta.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar detalle de venta: " + detalleVenta.getIdDetalleVenta(), e);
        }
        return false;
    }

    // Método para obtener el último ID de detalle registrado
    public String obtenerUltimoIdUsuarios() {
        String query = "SELECT MAX(ID) AS ultimoId FROM detalle_venta";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getString("ultimoId");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener el último ID de detalle", e);
        }
        return null;
    }
}

