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
import java.util.List;
import Logica.Venta;
import Logica.DetalleVenta;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentaDAO {
    private static final Logger logger = Logger.getLogger(VentaDAO.class.getName());
    public boolean insertarVenta(Venta venta) {
        String sqlVenta = "INSERT INTO venta (ID, Fecha, IDUSU, Total, Metodo) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement psVenta = null;
        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();

        try {
            // Obtener la conexión y desactivar auto-commit para manejar la transacción manualmente
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);

            // Insertar la venta
            psVenta = conn.prepareStatement(sqlVenta);
            psVenta.setString(1, venta.getIdVenta());
            psVenta.setDate(2, new java.sql.Date(venta.getFecha().getTime()));
            psVenta.setString(3, venta.getIdUsuario());
            psVenta.setDouble(4, venta.getTotal());
            psVenta.setString(5, venta.getMetodoPago());
            psVenta.executeUpdate();

            // Insertar cada detalle de venta
            List<DetalleVenta> detalles = venta.getDetalles();
            for (DetalleVenta detalle : detalles) {
                detalle.setIdVenta(venta.getIdVenta());  // Asignar el ID de venta al detalle
                if (!detalleVentaDAO.insertarDetalle(conn, detalle)) {
                    conn.rollback();  // Revertir la transacción si falla algún detalle
                    return false;
                }
            }

            // Confirmar la transacción
            conn.commit();
            return true;

        } catch (SQLException e) {
            // En caso de error, revertir la transacción
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            // Cerrar recursos
            try {
                if (psVenta != null) psVenta.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public String obtenerUltimoIdUsuario() {
    String ultimoId = null;
    String query = "SELECT ID FROM venta ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

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
    public List<Venta> obtenerVentaPorId(String id) {
    List<Venta> ventax = new ArrayList<>();
    String query = "SELECT v.ID, v.Fecha, v.IDUSU, v.Total, v.Metodo FROM venta v JOIN usuario u ON v.IDUSU = u.ID WHERE u.ID = ?"
            /*"SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                   "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.ID = ?"*/;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getString("ID"));
                venta.setFecha(rs.getDate("Fecha"));
                venta.setIdUsuario(rs.getString("IDUSU"));
                venta.setTotal(rs.getDouble("Total"));
                venta.setMetodoPago(rs.getString("Metodo"));
                ventax.add(venta);
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener usuarios por ID", e);
    }

    return ventax; // Retornar lista vacía si no se encontraron resultados
}
    public List<Venta> obtenerVentaPorIdVenta(String id) {
    List<Venta> ventax = new ArrayList<>();
    String query = "SELECT v.ID, v.Fecha, v.IDUSU, v.Total, v.Metodo FROM venta v JOIN usuario u ON v.IDUSU = u.ID WHERE v.ID = ?"
            /*"SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                   "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.ID = ?"*/;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getString("ID"));
                venta.setFecha(rs.getDate("Fecha"));
                venta.setIdUsuario(rs.getString("IDUSU"));
                venta.setTotal(rs.getDouble("Total"));
                venta.setMetodoPago(rs.getString("Metodo"));
                ventax.add(venta);
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener usuarios por ID", e);
    }

    return ventax; // Retornar lista vacía si no se encontraron resultados
}
    public List<Venta> obtenerVenta() {
    List<Venta> ventax = new ArrayList<>();
    String query = "SELECT v.ID, v.Fecha, v.IDUSU, v.Total, v.Metodo, u.Estado FROM venta v JOIN detalle_venta u ON v.ID = u.IDVEN "
            /*"SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                   "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.ID = ?"*/;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {


        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta();
                DetalleVenta detalle = new DetalleVenta();
                venta.setIdVenta(rs.getString("ID"));
                venta.setFecha(rs.getDate("Fecha"));
                venta.setIdUsuario(rs.getString("IDUSU"));
                venta.setTotal(rs.getDouble("Total"));
                venta.setMetodoPago(rs.getString("Metodo"));
                detalle.setEstado(rs.getString("Estado"));
                ventax.add(venta);
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener usuarios por ID", e);
    }

    return ventax; // Retornar lista vacía si no se encontraron resultados
}
    public boolean actualizarTotalVenta(Venta venta) {
    String query = "UPDATE venta SET Total = ? WHERE ID = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        if (con == null) {
            logger.severe("Conexión con la base de datos fallida.");
            return false;
        }

        ps.setDouble(1, venta.getTotal());
        ps.setString(2, venta.getIdVenta());

        int filasAfectadas = ps.executeUpdate();
        logger.info("Filas afectadas al actualizar el total: " + filasAfectadas);

        return filasAfectadas > 0;

    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al actualizar el total de la venta: " + venta.getIdVenta(), e);
        return false;
    }
}


    // Método para insertar una venta
    public boolean insertarVentas(Venta venta) {
        String query = "INSERT INTO venta (ID, Fecha, IDUSU,  Total, Metodo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, venta.getIdVenta());
            ps.setDate(2, new java.sql.Date(venta.getFecha().getTime()));
            ps.setString(3, venta.getIdUsuario());
            ps.setString(4, venta.getMetodoPago());
            ps.setDouble(5, venta.getTotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar venta: " + venta.getIdVenta(), e);
        }
        return false;
    }

    // Método para obtener el último ID de venta registrado
    public String obtenerUltimoIdUsuarios() {
        String query = "SELECT MAX(ID) AS ultimoId FROM venta";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getString("ultimoId");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener el último ID de venta", e);
        }
        return null;
    }
}
