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
import Logica.Carrito;
import Logica.DetalleCarrito;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarritoDAO {
    private static final Logger logger = Logger.getLogger(CarritoDAO.class.getName());
    public boolean insertarVenta(Carrito Carrito) {
        String sqlVenta = "INSERT INTO carrito (ID, IDUSU) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement psVenta = null;
        DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();

        try {
            // Obtener la conexión y desactivar auto-commit para manejar la transacción manualmente
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);

            // Insertar la venta
            psVenta = conn.prepareStatement(sqlVenta);
            psVenta.setString(1, Carrito.getId());
            psVenta.setString(2, Carrito.getIdusu());
            psVenta.executeUpdate();

            List<DetalleCarrito> detalles = Carrito.getDetalles();
            for (DetalleCarrito detalle : detalles) {
                detalle.setIDCARR(Carrito.getId());  // Asignar el ID de venta al detalle
                if (!detalleCarritoDAO.insertarDetalle(conn, detalle)) {
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
    String query = "SELECT ID FROM carrito ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

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
    public List<Carrito> obtenerVentaPorIdUsuario(String id) {
    List<Carrito> carritox = new ArrayList<>();
    String query = "SELECT v.ID, v.IDUSU FROM carrito v JOIN usuario u ON v.IDUSU = u.ID WHERE u.ID = ?"
            /*"SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                   "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.ID = ?"*/;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Carrito carr = new Carrito();
                carr.setId(rs.getString("ID"));
                carr.setIdusu(rs.getString("IDUSU"));
                carritox.add(carr);
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener usuarios por ID", e);
    }

    return carritox; // Retornar lista vacía si no se encontraron resultados
}
    public List<Carrito> obtenerVentaPorIdCarrito(String id) {
    List<Carrito> carritox = new ArrayList<>();
    String query = "SELECT v.ID, v.IDUSU FROM carrito v JOIN usuario u ON v.IDUSU = u.ID WHERE v.ID = ?"
            /*"SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                   "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.ID = ?"*/;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Carrito carr = new Carrito();
                carr.setId(rs.getString("ID"));
                carr.setIdusu(rs.getString("IDUSU"));
                carritox.add(carr);
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener usuarios por ID", e);
    }

    return carritox; // Retornar lista vacía si no se encontraron resultados
}
    public List<Carrito> obtenerVenta() {
    List<Carrito> carritox = new ArrayList<>();
    String query = "SELECT v.ID, v.IDUSU FROM carrito v JOIN detalle_carrito u ON v.ID = u.IDCARR "
            /*"SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                   "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.ID = ?"*/;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {


        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Carrito venta = new Carrito();
                venta.setId(rs.getString("ID"));
                venta.setIdusu(rs.getString("IDUSU"));
                carritox.add(venta);
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener usuarios por ID", e);
    }

    return carritox; // Retornar lista vacía si no se encontraron resultados
}
    public String obtenerUltimoIdUsuarioEX(String id) {
    String ultimoId = null;
    String query = "SELECT ID FROM carrito WHERE IDUSU = ?"; // Se ordena para obtener el último ID

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
         
        ps.setString(1, id); // Primero se establece el parámetro
        try (ResultSet rs = ps.executeQuery()) { // Luego se ejecuta la consulta
            if (rs.next()) {
                ultimoId = rs.getString("ID");
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener el último ID de usuario", e);
    }
    return ultimoId;
}
public boolean insertarSoloDetalleDeVenta(Carrito carrito) {
    Connection conn = null;
    DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();

    try {
        // Obtener la conexión y manejar la transacción manualmente
        conn = Conexion.getConnection();
        conn.setAutoCommit(false);

        // Validar que el carrito exista
        String queryValidarCarrito = "SELECT COUNT(*) FROM carrito WHERE ID = ?";
        try (PreparedStatement psValidar = conn.prepareStatement(queryValidarCarrito)) {
            psValidar.setString(1, carrito.getId());
            try (ResultSet rs = psValidar.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    System.err.println("Error: El carrito con ID " + carrito.getId() + " no existe.");
                    conn.rollback();
                    return false;
                }
            }
        }

        // Insertar detalles
        for (DetalleCarrito detalle : carrito.getDetalles()) {
            detalle.setIDCARR(carrito.getId()); // Asociar el ID de carrito al detalle
            if (!detalleCarritoDAO.insertarDetalle(conn, detalle)) {
                conn.rollback(); // Revertir si falla algún detalle
                System.err.println("Error al insertar el detalle con ID: " + detalle.getId());
                return false;
            }
        }

        // Confirmar transacción
        conn.commit();
        return true;

    } catch (SQLException e) {
        // Revertir en caso de error
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
        // Liberar recursos
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
public Carrito obtenerVentaPorIdUsuariox(String id) {
    Carrito carritox = new Carrito();
    String query = "SELECT v.ID, v.IDUSU FROM carrito v JOIN usuario u ON v.IDUSU = u.ID WHERE u.ID = ?"
            /*"SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                   "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.ID = ?"*/;

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Carrito carr = new Carrito();
                carr.setId(rs.getString("ID"));
                carr.setIdusu(rs.getString("IDUSU"));
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener usuarios por ID", e);
    }

    return carritox; // Retornar lista vacía si no se encontraron resultados
}
}
