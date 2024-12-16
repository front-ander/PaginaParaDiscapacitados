/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.UsuarioDAO;
import Logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author dnayj
 */
@WebServlet("/actualizarUsuario")
public class ActualizarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String dni = request.getParameter("dni");
        String correo = request.getParameter("correo");
        String categoria = request.getParameter("categoria");

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setDni(dni);
        usuario.setCorreo(correo);
        usuario.setCategoria(categoria);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            boolean actualizado = usuarioDAO.actualizarUsuario(usuario);
            if (actualizado) {
                response.sendRedirect("usuarios.jsp");
            } else {
                response.sendRedirect("usuarios.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("usuarios.jsp");
        }
    }
}


