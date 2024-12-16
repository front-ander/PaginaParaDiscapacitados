/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.ProductoDAO;
import Logica.Producto;
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
@WebServlet("/actualizarProducto")
@MultipartConfig
public class ActualizarProductoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String categoria = request.getParameter("categoria");
        Part imagenPart = request.getPart("imagen");

        ProductoDAO prodtDAO = new ProductoDAO();
        Producto prod = prodtDAO.obtenerPorId(id); // Supongamos que este método obtiene el producto por su ID.

        if (prod == null) {
            response.sendRedirect("trabajador/editar/ECT.jsp?mensaje=Producto no encontrado");
            return;
        }

        // Actualizar solo si se subió una nueva imagen
        if (imagenPart != null && imagenPart.getSize() > 0) {
            try (InputStream inputStream = imagenPart.getInputStream()) {
                byte[] imagenBytes = inputStream.readAllBytes();
                prod.setImagen(imagenBytes); // Se actualiza la imagen si existe una nueva.
            }
        }

        // Actualizar los demás campos
        prod.setNombre(nombre);
        prod.setDescripcion(descripcion);
        prod.setPrecio(precio);
        prod.setStock(stock);
        prod.setCategoria(categoria);

        try {
            boolean actualizado = prodtDAO.actualizarProducto(prod);
            if (actualizado) {
                response.sendRedirect("trabajador/editar/ECT.jsp");
            } else {
                response.sendRedirect("trabajador/editar/ECT.jsp?mensaje=Error al actualizar producto");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("trabajador/editar/ECT.jsp?mensaje=Error en el servidor");
        }
    }
}


