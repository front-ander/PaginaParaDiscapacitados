package servlets;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAO.MascotaDAO;
import DAO.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import DAO.ServicioDAO;
import DAO.ProductoDAO;
import DAO.VentaDAO;
import DAO.DetalleVentaDAO;
import Logica.Venta;
import Logica.DetalleVenta;
import Logica.Mascota;
import Logica.Servicios;
import Logica.Producto;
import DAO.DetalleCarritoDAO;
import Logica.DetalleCarrito;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;

@WebServlet("/COMPRAR")
public class ComprarServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DetalleCarritoDAO.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");
        String idCarrito = request.getParameter("id");
        double total = 0;
        if (userID == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Crear instancias de DAO
        VentaDAO ventaDAO = new VentaDAO();
        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();
        MascotaDAO mascotaDAO = new MascotaDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        ServicioDAO servicioDAO = new ServicioDAO();

        // Obtener detalles del carrito
        Venta venta = new Venta();
        
        String ultimoId = ventaDAO.obtenerUltimoIdUsuario();
        String nuevoId;
        if (ultimoId != null && ultimoId.startsWith("VNT")) {
            int numero = Integer.parseInt(ultimoId.substring(3)) + 1;
            nuevoId = "VNT" + numero;
        } else {
            nuevoId = "VNT1"; // Si no hay usuarios, comenzamos en "USR1"
        }
        venta.setIdVenta(nuevoId);  // Método para generar un ID único para la venta
        venta.setFecha(new Date());          // Fecha actual
        venta.setIdUsuario(userID);
        venta.setMetodoPago("Tarjeta");      // Aquí puedes usar el método de pago que desees
        venta.setTotal(0.00); // El total es igual al precio de la mascota en este caso
        boolean exito = ventaDAO.insertarVenta(venta);
        
        if (exito) {
            List<DetalleCarrito> carrito = obtenerCarrito(idCarrito);
        for (DetalleCarrito car : carrito) {
            
        }
        } else {
            response.sendRedirect("/PasandoOW/carrito.jsp?mensaje=Error al realizar la compra");
        }
        
}
    public List<DetalleCarrito> obtenerCarrito(String idCarrito) {
        List<DetalleCarrito> carrito = new ArrayList<>();
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

            ps.setString(1, idCarrito);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleCarrito detalle = new DetalleCarrito();
                    detalle.setId(rs.getString("ID"));
                    detalle.setVentas(rs.getString("Ventas"));
                    detalle.setCantidad(rs.getInt("Cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("Precio_Unitario"));
                    detalle.setSubtotal(rs.getDouble("Subtotal"));
                    detalle.setIndice(rs.getInt("indice"));
                    carrito.add(detalle);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener el carrito de compras", e);
        }

        return carrito;
    }
}
