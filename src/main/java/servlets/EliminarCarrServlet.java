/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.DetalleCarritoDAO;
import Logica.DetalleCarrito;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ELIMINARCARR")
public class EliminarCarrServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID del detalle a eliminar desde el formulario
        String idDetalle = request.getParameter("id");

        if (idDetalle == null || idDetalle.isEmpty()) {
            // Si no se proporciona un ID válido, redirigir a una página de error o mensaje
            response.sendRedirect("error.jsp?mensaje=ID inválido");
            return;
        }

        // Crear una instancia de DetalleCarritoDAO
        DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();

        // Eliminar el detalle del carrito en la base de datos
        boolean exito = detalleCarritoDAO.eliminarDetalle(idDetalle);

        if (exito) {
            // Si la eliminación fue exitosa, redirigir al carrito actualizado o a una página de éxito
            response.sendRedirect("carrito.jsp?mensaje=Elemento eliminado correctamente");
        } else {
            // Si ocurrió un error, redirigir a una página de error o mostrar un mensaje
            response.sendRedirect("carrito.jsp?mensaje=Error al eliminar el elemento");
        }
    }
}
