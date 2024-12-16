package servlets;

import DAO.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FinalizarCompraServlet")
public class FinalizarCompraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String tarjeta = request.getParameter("tarjeta");

        if (nombre == null || direccion == null || telefono == null || email == null || tarjeta == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        String sql = "INSERT INTO compras (nombre, direccion, telefono, email, tarjeta) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Setear los parámetros en el PreparedStatement
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setString(3, telefono);
            stmt.setString(4, email);
            stmt.setString(5, tarjeta);

            // Ejecutar la consulta
            int filasAfectadas = stmt.executeUpdate();
            
            // Verificar si se insertaron filas correctamente
            if (filasAfectadas > 0) {
                response.sendRedirect("confirmacion.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            // Log del error y redirección a la página de error
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
