package servlets;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAO.MascotaDAO;
import DAO.VentaDAO;
import DAO.DetalleVentaDAO;
import Logica.Venta;
import Logica.DetalleVenta;
import Logica.Mascota;

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

@WebServlet("/ComprarAlgoM")
public class ComprarAlgoMServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener el ID de la mascota desde el formulario
        String idMascota = request.getParameter("id");

        // Obtener la sesión para recuperar el usuario actual
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID"); // Suponiendo que el ID del usuario esté guardado en la sesión

        if (userID == null) {
            // Si el usuario no ha iniciado sesión, redirigir a la página de inicio de sesión
            response.sendRedirect("index.jsp");
            return;
        }

        // Crear instancias de DAO
        MascotaDAO mascotaDAO = new MascotaDAO();
        VentaDAO ventaDAO = new VentaDAO();
        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();

        // Obtener la información de la mascota
        Mascota mascota = mascotaDAO.obtenerPorId(idMascota);
        if (mascota == null) {
            // Si no se encuentra la mascota, redirigir a una página de error o mostrar mensaje
            response.sendRedirect("ComprarAlgoM.jsp?mensaje=Mascota no encontrada");
            return;
        }

        // Crear una nueva venta
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
        venta.setTotal(mascota.getPrecio()); // El total es igual al precio de la mascota en este caso

        // Crear el detalle de la venta
        DetalleVenta detalle = new DetalleVenta();
        String ultimoIds = detalleVentaDAO.obtenerUltimoIdUsuario();
    String nuevoIds;
    if (ultimoIds != null && ultimoIds.startsWith("DVN")) {
        int numerox = Integer.parseInt(ultimoIds.substring(3)) + 1;
        nuevoIds = "DVN" + numerox;
    } else {
        nuevoIds = "DVN1"; // Si no hay usuarios, comenzamos en "USR1"
    }
        detalle.setIdDetalleVenta(nuevoIds); // Método para generar un ID único para el detalle
        detalle.setIdVenta(venta.getIdVenta());
        detalle.setVentas(idMascota);
        detalle.setCantidad(1); // Cantidad fija en 1 ya que es una mascota
        detalle.setPrecioUnitario(mascota.getPrecio());
        detalle.setSubtotal(mascota.getPrecio()); // Subtotal es igual al precio en este caso
        detalle.setEstado("EST2"); // Estado inicial de la venta

        // Agregar el detalle a la venta
        List<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(detalle);
        venta.setDetalles(detalles);

        // Guardar la venta y el detalle en la base de datos
        boolean exito = ventaDAO.insertarVenta(venta);
        
        if (exito) {
            response.sendRedirect("HistorialCompra.jsp?mensaje=Compra realizada con éxito");
        } else {
            response.sendRedirect("../comprar/ComprarAlgoM.jsp?mensaje=Error al realizar la compra");
        }
    }

}

