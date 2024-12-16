/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.MascotaDAO;
import Logica.Mascota;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;

/**
 *
 * @author dnayj
 */
@WebServlet("/actualizarMascota")
@MultipartConfig
public class ActualizarMascotaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String descripcionC = request.getParameter("descripcionC");
        Double precio = Double.parseDouble(request.getParameter("precio"));
        String tiempo = request.getParameter("tiempo");
        String calificacion = request.getParameter("calificacion");
        String categoria = request.getParameter("categoria");
        Part imagenPart = request.getPart("imagen");

        MascotaDAO mascotaDAO = new MascotaDAO();
        Mascota mascota = mascotaDAO.obtenerPorId(id); // Recupera la mascota existente.

        if (mascota == null) {
            response.sendRedirect("trabajador/editar/EMT.jsp?mensaje=Mascota no encontrada");
            return;
        }

        // Actualizar solo si se subió una nueva imagen
        if (imagenPart != null && imagenPart.getSize() > 0) {
            try (InputStream inputStream = imagenPart.getInputStream()) {
                byte[] imagenBytes = inputStream.readAllBytes();
                mascota.setImagen(imagenBytes); // Se actualiza la imagen solo si se proporciona una nueva.
            }
        }

        // Actualizar los demás campos
        mascota.setNombre(nombre);
        mascota.setDescripcion(descripcion);
        mascota.setDescripcionC(descripcionC);
        mascota.setPrecio(precio);
        mascota.setTiempo(tiempo);
        mascota.setCalificacion(calificacion);
        mascota.setCategoria(categoria);

        try {
            boolean actualizado = mascotaDAO.actualizarMascota(mascota);
            if (actualizado) {
                response.sendRedirect("trabajador/editar/EMT.jsp");
            } else {
                response.sendRedirect("trabajador/editar/EMT.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("trabajador/editar/EMT.jsp?mensaje=Error en el servidor");
        }
    }
}



