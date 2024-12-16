package servlets;

import DAO.ServicioDAO;
import Logica.Servicios;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
@WebServlet("/agregarServicio")
@MultipartConfig
public class AgregarServicioServlet extends HttpServlet {
    private ServicioDAO mascotaDAO = new ServicioDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ultimoId = mascotaDAO.obtenerUltimoIdProducto();
        String nuevoId;
        if (ultimoId != null && ultimoId.startsWith("SER")) {
            int numero = Integer.parseInt(ultimoId.substring(3)) + 1;
            nuevoId = "SER" + numero;
        } else {
            nuevoId = "SER1"; 
        }

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String descripcionC = request.getParameter("descripcionC");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String calificacion = request.getParameter("calificacion");
        String tiempo = request.getParameter("tiempo");
        String categoria = request.getParameter("categoria");

        Part imagenPart = request.getPart("imagen");
        byte[] imagenBytes = null;

        if (imagenPart != null && imagenPart.getSize() > 0) {
            try (InputStream inputStream = imagenPart.getInputStream()) {
                imagenBytes = inputStream.readAllBytes();
            }
        }

        Servicios mactD = new Servicios();
        mactD.setID(nuevoId);
        mactD.setNombre(nombre);
        mactD.setDescripcion(descripcion);
        mactD.setDescripcionC(descripcionC);
        mactD.setPrecio(precio);
        mactD.setCalificacion(calificacion);
        mactD.setTiempo(tiempo);
        mactD.setImagen(imagenBytes);
        mactD.setCategoria(categoria);

        boolean agregado = mascotaDAO.agregarServicio(mactD);

        if (agregado) {
            response.sendRedirect("trabajador/editar/EST.jsp");
        } else {
            response.getWriter().println("Error al agregar el producto.");
        }
    }
}
