package servlets;

import DAO.MensajeDAO;
import Logica.Mensaje;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submitContact")
public class SubmitContactServlet extends HttpServlet {
private MensajeDAO mensajeDAO = new MensajeDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener datos del formulario
        String ultimoId = mensajeDAO.obtenerUltimoIdProducto();
        String nuevoId;
        if (ultimoId != null && ultimoId.startsWith("MEN")) {
            int numero = Integer.parseInt(ultimoId.substring(3)) + 1;
            nuevoId = "MEN" + numero;
        } else {
            nuevoId = "MEN1"; 
        }
        String nombre = request.getParameter("name");
        String correo = request.getParameter("email");
        String mensajeTexto = request.getParameter("message");

        // Validar campos (opcional)
        if (nombre == null || correo == null || mensajeTexto == null ||
            nombre.isEmpty() || correo.isEmpty() || mensajeTexto.isEmpty()) {
            response.sendRedirect("contactanos.jsp?error=Por+favor+complete+todos+los+campos");
            return;
        }

        // Crear objeto Mensaje
        Mensaje mensaje = new Mensaje();
        mensaje.setId(nuevoId);
        mensaje.setNombre(nombre);
        mensaje.setCorreo(correo);
        mensaje.setMensaje(mensajeTexto);

        // Guardar mensaje en la base de datos
        MensajeDAO mensajeDAO = new MensajeDAO();
        boolean exito = mensajeDAO.guardarMensaje(mensaje);

        // Redirigir con mensaje de éxito o error
        if (exito) {
            response.sendRedirect("contactanos.jsp?success=Mensaje+enviado+exitosamente");
        } else {
            response.sendRedirect("contactanos.jsp?error=Hubo+un+error+al+enviar+el+mensaje");
        }
    
    }
}
