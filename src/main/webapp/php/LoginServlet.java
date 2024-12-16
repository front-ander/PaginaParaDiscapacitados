package servlets;

import java.io.IOException;
import java.util.Base64;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;



@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
@MultipartConfig
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Obtén los parámetros del formulario
        String dni = request.getParameter("dni");
        String correo = request.getParameter("Correo");
        String photoBase64 = request.getParameter("photo");  // Recibe la imagen en formato base64

        // Verifica que la imagen haya sido capturada
        if (photoBase64 != null && !photoBase64.isEmpty()) {
            try {
                // Decodifica la imagen de base64 a bytes
                byte[] photoBytes = Base64.getDecoder().decode(photoBase64.split(",")[1]);

                // Aquí puedes continuar con el procesamiento o guardado en la base de datos
                // Guarda `dni`, `correo` y `photoBytes` en la base de datos o realiza otras acciones

                // Respuesta al cliente
                response.sendRedirect("IniciarSesion.jsp?mensaje=Registro exitoso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error al decodificar la imagen: " + e.getMessage());
                response.sendRedirect("IniciarSesion.jsp?mensaje=Error al procesar la imagen.");
            }
        } else {
            System.out.println("La foto no fue capturada correctamente.");
            response.sendRedirect("IniciarSesion.jsp?mensaje=Por favor, captura una imagen.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
