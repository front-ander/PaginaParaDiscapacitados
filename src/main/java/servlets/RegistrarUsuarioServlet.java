package servlets;

import DAO.UsuarioDAO;
import Logica.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistrarUsuarioServlet")
@MultipartConfig
public class RegistrarUsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    // Obtener el último ID y generar el nuevo
    String ultimoId = usuarioDAO.obtenerUltimoIdUsuario();
    String nuevoId;
    if (ultimoId != null && ultimoId.startsWith("USR")) {
        int numero = Integer.parseInt(ultimoId.substring(3)) + 1;
        nuevoId = "USR" + numero;
    } else {
        nuevoId = "USR1"; // Si no hay usuarios, comenzamos en "USR1"
    }

    // Obtener los demás parámetros del formulario
    String dni = request.getParameter("dni").trim();
    String correo = request.getParameter("Correo").trim();
    String photoBase64 = request.getParameter("photo");
    String categoria = "CAT3";
    if (photoBase64 != null && photoBase64.startsWith("data:image")) {
        photoBase64 = photoBase64.split(",")[1];
    }

    // Verificar si el DNI o correo ya existen
    if (usuarioDAO.existeDniOCorreo(dni, correo)) {
        String mensajeError = "El DNI o el correo ya están registrados. Por favor, verifica los datos.";
        request.setAttribute("mensajeError", mensajeError);
        request.setAttribute("datosUsuario", new Usuario(nuevoId, dni, correo, null, categoria));
        request.getRequestDispatcher("Registrar.jsp").forward(request, response);
        return;
    }

    // Crear el usuario
    Usuario usuario = new Usuario();
    usuario.setId(nuevoId);
    usuario.setDni(dni);
    usuario.setCorreo(correo);
    usuario.setCaraBase64(photoBase64);
     usuario.setCategoria(categoria);

    // Registrar usuario y manejar posibles errores
    String mensajeError = "";
    try {
        boolean exito = usuarioDAO.registrarUsuario(usuario);
        if (exito) {
            response.sendRedirect("IniciarSesion.jsp");
            return;
        } else {
            mensajeError = "Error al registrar el usuario. Por favor, intenta nuevamente.";
        }
    } catch (Exception e) {
        mensajeError = "Error en el registro: " + e.getMessage();
    }

    request.setAttribute("mensajeError", mensajeError);
    request.setAttribute("datosUsuario", usuario);
    request.getRequestDispatcher("Registrar.jsp").forward(request, response);
}


}
