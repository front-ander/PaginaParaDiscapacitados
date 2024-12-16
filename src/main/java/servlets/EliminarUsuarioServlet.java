package servlets;
import DAO.UsuarioDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/eliminarUsuario")
public class EliminarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            boolean eliminado = usuarioDAO.eliminarUsuario(id);
            if (eliminado) {
                response.sendRedirect("usuarios.jsp?mensaje=Usuario eliminado con éxito");
            } else {
                response.sendRedirect("usuarios.jsp?mensaje=Error al eliminar usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("usuarios.jsp?mensaje=Error en el servidor");
        }
    }
}
