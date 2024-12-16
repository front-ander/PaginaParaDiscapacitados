package servlets;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAO.ServicioDAO;
import DAO.CarritoDAO;
import DAO.DetalleCarritoDAO;
import Logica.Carrito;
import Logica.DetalleCarrito;
import Logica.Servicios;

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

@WebServlet("/CarritoS")
public class CarritoSServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener el ID de la mascota desde el formulario
        String idservicio = request.getParameter("id");

        // Obtener la sesión para recuperar el usuario actual
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID"); // Suponiendo que el ID del usuario esté guardado en la sesión

        if (userID == null) {
            // Si el usuario no ha iniciado sesión, redirigir a la página de inicio de sesión
            response.sendRedirect("index.jsp");
            return;
        }

        // Crear instancias de DAO
        ServicioDAO servicioDAO = new ServicioDAO();
        CarritoDAO ventaDAO = new CarritoDAO();
        DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();

        // Obtener la información de la mascota
        Servicios servicio = servicioDAO.obtenerPorId(idservicio);
        if (servicio == null) {
            // Si no se encuentra la mascota, redirigir a una página de error o mostrar mensaje
            response.sendRedirect("../ComprarAlgoS.jsp");
            return;
        }

        // Crear una nueva venta
Carrito venta = new Carrito();
String nuevoId;

// Verificar si ya existe una entrada con el userID en la tabla carrito
String ultimoId = ventaDAO.obtenerUltimoIdUsuarioEX(userID); // Busca por IDUSU en la tabla carrito

if (ultimoId != null) {
    // Si ya existe una entrada, utilizamos ese ID
    nuevoId = ultimoId;
} else {
    // Si no existe, generamos un nuevo ID para carrito
    String ultimoIdGenerado = ventaDAO.obtenerUltimoIdUsuario(); // Busca el último ID general
    if (ultimoIdGenerado != null && ultimoIdGenerado.startsWith("CAR")) {
        int numero = Integer.parseInt(ultimoIdGenerado.substring(3)) + 1;
        nuevoId = "CAR" + numero;
    } else {
        nuevoId = "CAR1"; // Comenzar desde "CAR1" si no hay registros
    }
}

// Asignar el ID generado o existente a la venta
venta.setId(nuevoId);
venta.setIdusu(userID); // Asociar el ID de usuario a la venta
// Aquí puedes establecer otros atributos de la venta, como el método de pago o el total

        // Crear el detalle de la venta
        DetalleCarrito detalle = new DetalleCarrito();
        String ultimoIds = detalleCarritoDAO.obtenerUltimoIdUsuario();
        int indice = detalleCarritoDAO.obtenerUltimoIndice();
    String nuevoIds;
    if (ultimoIds != null && ultimoIds.startsWith("DCA")) {
        int numerox = Integer.parseInt(ultimoIds.substring(3)) + 1;
        nuevoIds = "DCA" + numerox;
        indice += 1;
    } else {
        nuevoIds = "DCA1"; // Si no hay usuarios, comenzamos en "USR1"
    }
        detalle.setId(nuevoIds); // Método para generar un ID único para el detalle
        detalle.setIDCARR(venta.getId());
        detalle.setVentas(idservicio);
        detalle.setCantidad(1); // Cantidad fija en 1 ya que es una mascota
        detalle.setPrecioUnitario(servicio.getPrecio());
        detalle.setSubtotal(servicio.getPrecio()); // Subtotal es igual al precio en este caso
        detalle.setIndice(indice);
        List<DetalleCarrito> detalles = new ArrayList<>();
        detalles.add(detalle);
        venta.setDetalles(detalles);

        // Guardar la venta y el detalle en la base de datos
        if (ventaDAO.obtenerUltimoIdUsuarioEX(userID) != null) {
    // El carrito ya existe, solo agregar detalles
    boolean exito = ventaDAO.insertarSoloDetalleDeVenta(venta);
    if (exito) {
        response.sendRedirect("/PasandoOW/carrito.jsp?mensaje=Detalles añadidos al carrito");
    } else {
        response.sendRedirect("/PasandoOW/comprar/ComprarAlgoS.jsp?mensaje=Error al añadir detalles");
    }
} else {
    // Crear un nuevo carrito
    boolean exito = ventaDAO.insertarVenta(venta);
    if (exito) {
        response.sendRedirect("/PasandoOW/carrito.jsp?mensaje=Carrito creado y detalle añadido");
    } else {
        response.sendRedirect("/PasandoOW/comprar.jsp?mensaje=Error al crear el carrito");
    }
}
}
    }

