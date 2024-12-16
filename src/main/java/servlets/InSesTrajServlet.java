package servlets;

import DAO.UsuarioDAO;
import Logica.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "InSesTrajServlet", urlPatterns = {"/InSesTrajServlet"})
public class InSesTrajServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String dni = request.getParameter("dni").trim();
        String photoBase64 = request.getParameter("photo");

        Usuario usuario = usuarioDAO.obtenerUsuarioPorDNITRAJ(dni);

        if (usuario != null) {
            // Guardamos los datos del usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("userID", usuario.getId());
            session.setAttribute("userDNI", usuario.getDni());
            session.setAttribute("userEmail", usuario.getCorreo());
            session.setAttribute("userCategory", usuario.getCategoria());
            session.setAttribute("userPhotoBase64", usuario.getCaraBase64());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"caraBase64\": \"" + usuario.getCaraBase64() + "\", \"categoria\": \"" + usuario.getCategoria() + "\"}");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado o no autorizado");
        }
    }
}
