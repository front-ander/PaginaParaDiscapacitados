package servlets;

import DAO.ServicioDAO;
import Logica.Servicios;
import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/actualizarServicio")
@MultipartConfig
public class ActualizarServicioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar parámetros del formulario
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String descripcionC = request.getParameter("descripcionC");
        String precioParam = request.getParameter("precio");
        String tiempo = request.getParameter("tiempo");
        String calificacion = request.getParameter("calificacion");
        String categoria = request.getParameter("categoria");
        Part imagenPart = request.getPart("imagen");

        // Validar campos obligatorios
        if (id == null || nombre == null || descripcion == null || descripcionC == null ||
            precioParam == null || tiempo == null || calificacion == null || categoria == null) {
            response.sendRedirect("trabajador/editar/EST.jsp?mensaje=Faltan datos obligatorios");
            return;
        }

        // Convertir precio a Double
        Double precio;
        try {
            precio = Double.parseDouble(precioParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("trabajador/editar/EST.jsp?mensaje=Precio inválido");
            return;
        }

        // Obtener el servicio existente
        ServicioDAO servicioDAO = new ServicioDAO();
        Servicios servicio = servicioDAO.obtenerPorId(id);
        if (servicio == null) {
            response.sendRedirect("trabajador/editar/EMT.jsp?mensaje=Servicio no encontrado");
            return;
        }

        // Actualizar campos del servicio
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setDescripcionC(descripcionC);
        servicio.setPrecio(precio);
        servicio.setTiempo(tiempo);
        servicio.setCalificacion(calificacion);
        servicio.setCategoria(categoria);

        // Actualizar imagen si se proporciona una nueva
        if (imagenPart != null && imagenPart.getSize() > 0) {
            try (InputStream inputStream = imagenPart.getInputStream()) {
                byte[] imagenBytes = inputStream.readAllBytes();
                servicio.setImagen(imagenBytes);
            } catch (IOException e) {
                e.printStackTrace();
                response.sendRedirect("trabajador/editar/EST.jsp?mensaje=Error al procesar la imagen");
                return;
            }
        }

        // Intentar actualizar el servicio en la base de datos
        try {
            boolean actualizado = servicioDAO.actualizarServicio(servicio);
            if (actualizado) {
                response.sendRedirect("trabajador/editar/EST.jsp?mensaje=Servicio actualizado exitosamente");
            } else {
                response.sendRedirect("trabajador/editar/EST.jsp?mensaje=No se pudo actualizar el servicio");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("trabajador/editar/EST.jsp?mensaje=Error en el servidor");
        }
    }
}



