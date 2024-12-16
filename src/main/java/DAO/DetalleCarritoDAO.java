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
import Logica.DetalleCarrito;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetalleCarritoDAO {
    private static final Logger logger = Logger.getLogger(DetalleCarritoDAO.class.getName());
    public boolean insertarDetalle(Connection conn, DetalleCarrito detalle) {
        String sqlDetalleVenta = "INSERT INTO detalle_carrito (ID, Ventas,  Cantidad, IDCARR, Precio_Unitario, Subtotal, indice) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement psDetalleVenta = null;

        try {
            psDetalleVenta = conn.prepareStatement(sqlDetalleVenta);
            psDetalleVenta.setString(1, detalle.getId());
            psDetalleVenta.setString(2, detalle.getVentas());
            psDetalleVenta.setInt(3, detalle.getCantidad());
            psDetalleVenta.setString(4, detalle.getIDCARR());
            psDetalleVenta.setDouble(5, detalle.getPrecioUnitario());
            psDetalleVenta.setDouble(6, detalle.getSubtotal());
            psDetalleVenta.setInt(7, detalle.getIndice());
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
    String query = "SELECT ID FROM detalle_carrito ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

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
    
    public List<DetalleCarrito> obtenerDetalleVentaPorId(String id) {
    List<DetalleCarrito> carritox = new ArrayList<>();
    String query = """
            SELECT 
                v.ID, 
                CASE
                    WHEN v.Ventas LIKE 'MAC%' THEN (SELECT m.Nombre FROM mascota m WHERE m.ID = v.Ventas)
                    WHEN v.Ventas LIKE 'PRO%' THEN (SELECT p.Nombre FROM producto p WHERE p.ID = v.Ventas)
                    WHEN v.Ventas LIKE 'SER%' THEN (SELECT s.Nombre FROM servicio s WHERE s.ID = v.Ventas)
                    ELSE 'Desconocido'
                END AS Ventas,
                v.Cantidad, 
                v.Precio_Unitario, 
                v.Subtotal,
                v.indice
            FROM 
                detalle_carrito v 
            JOIN 
                carrito u ON v.IDCARR = u.ID 
            
            WHERE 
                v.IDCARR = ?;
            """;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DetalleCarrito venta = new DetalleCarrito();
                venta.setId(rs.getString("ID"));
                venta.setVentas(rs.getString("Ventas")); // Ahora contiene solo el Nombre
                venta.setCantidad(rs.getInt("Cantidad"));
                venta.setPrecioUnitario(rs.getDouble("Precio_Unitario"));
                venta.setSubtotal(rs.getDouble("Subtotal"));
                venta.setIndice(rs.getInt("indice"));
                carritox.add(venta);
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener detalles de venta por ID", e);
    }

    return carritox; // Retornar lista vacía si no se encontraron resultados
}
public boolean actualizarUsuario(DetalleCarrito usuario) {
        String query = """
            UPDATE detalle_carrito 
            SET Estado = ?
            WHERE ID = ?
        """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {


            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar usuario", e);
        }
        return false;
    }
public int obtenerUltimoIndice() {
    int ultimoId = 1;
    String query = "SELECT indice FROM detalle_carrito ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            ultimoId = rs.getInt("indice");
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener el último ID de usuario", e);
    }
    return ultimoId;
}
public boolean eliminarDetalle(String id) {
        String query = "DELETE FROM detalle_carrito WHERE indice = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Establecer el parámetro del ID
            ps.setInt(1, Integer.parseInt(id));

            // Ejecutar la consulta de eliminación
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0; // Si se eliminaron filas, la operación fue exitosa

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Si ocurre un error, retorna false
        }
    }
}

