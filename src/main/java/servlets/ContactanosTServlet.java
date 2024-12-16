package servlets;

import DAO.MensajeDAO;
import Logica.Mensaje;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contactanosT")
public class ContactanosTServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar los mensajes recibidos
        MensajeDAO mensajeDAO = new MensajeDAO();
        List<Mensaje> mensajes = mensajeDAO.obtenerMensajes();

        // Pasar los mensajes al JSP
        request.setAttribute("mensajes", mensajes);

        // Redirigir al JSP
        //request.getRequestDispatcher("/trabajador/contactanosT.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Eliminar un mensaje
        String accion = request.getParameter("accion");
        if ("eliminar".equals(accion)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                MensajeDAO mensajeDAO = new MensajeDAO();
                boolean eliminado = mensajeDAO.eliminarMensaje(Integer.parseInt(id));

                // Mensaje de éxito o error
                if (eliminado) {
                    request.setAttribute("success", "Mensaje eliminado con éxito.");
                } else {
                    request.setAttribute("error", "No se pudo eliminar el mensaje.");
                }
            }
        }

        // Recargar los mensajes después de la eliminación
        doGet(request, response);
    }
}
