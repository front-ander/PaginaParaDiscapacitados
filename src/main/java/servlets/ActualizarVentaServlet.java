/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DetalleVentaDAO;
import Logica.DetalleVenta;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dnayj
 */
@WebServlet("/actualizarVenta")
@MultipartConfig
public class ActualizarVentaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String estado = request.getParameter("estado");

        DetalleVenta detalle = new DetalleVenta();
        detalle.setEstado(estado);
        detalle.setIdDetalleVenta(id);

        DetalleVentaDAO detalled = new DetalleVentaDAO();
        try {
            boolean actualizado = detalled.actualizarUsuario(detalle);
            if (actualizado) {
                response.sendRedirect("trabajador/ventasT.jsp");
            } else {
                response.sendRedirect("trabajador/ventasT.jsp?mensaje=Error al actualizar producto");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("trabajador/ventasT.jsp?mensaje=Error en el servidor");
        }
    }
}


