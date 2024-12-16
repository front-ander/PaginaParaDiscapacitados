<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.UsuarioDAO" %>
<%@ page import="Logica.Usuario" %>
<%
    String dniBusqueda = request.getParameter("dni"); // Obtener el parámetro de búsqueda
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<Usuario> usuarios;
    
    if (dniBusqueda != null && !dniBusqueda.isEmpty()) {
        usuarios = usuarioDAO.obtenerUsuariosPorDni(dniBusqueda); // Obtener solo los usuarios que coinciden con el DNI
    } else {
        usuarios = usuarioDAO.obtenerUsuarios(); // Obtener todos los usuarios si no hay búsqueda
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carta</title>
    <link href="CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/tablas.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="index.jsp">
            <img src="Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    <div class="search-bar">
    <i class="fas fa-search"></i>
    <form action="usuarios.jsp" method="get" style="display: inline;">
        <input type="text" id="buscar" name="dni" placeholder="Buscar por DNI" value="<%= request.getParameter("dni") != null ? request.getParameter("dni") : "" %>" />
        <button type="submit" style="display: none;">Buscar</button>
    </form>
</div>
    <div class="icon-buttons">
        <a href="usuarios.jsp"><i class="fas fa-book"></i> Usuarios</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="trabajador/catalogoT.jsp"><i class="fas fa-book"></i> Catálogo</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="trabajador/mascotasT.jsp"><i class="fas fa-paw"></i> Mascotas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="trabajador/serviciosT.jsp"><i class="fas fa-heart"></i> Servicios</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="contactanosT.jsp"><i class="fas fa-phone"></i> Contáctanos </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="perfilT.jsp"><i class="fas fa-user"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="#"><i class="fas fa-shopping-cart"></i> # </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="#"><i class="fas fa-bars"></i> # </a>
    </div>
</header>

<!-- Contenido Principal -->
<main>
    <h1>Gestión de Usuarios</h1>
    <div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>DNI</th>
                    <th>Correo</th>
                    <th>Categoría</th>
                    <th>Cara</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 

                    for (Usuario usuario : usuarios) { 
                %>
                <tr>
                    <td><%= usuario.getId() %></td>
                    <td><%= usuario.getDni() %></td>
                    <td><%= usuario.getCorreo() %></td>
                    <td><%= usuario.getCategoria() %></td>
                    <td>
                            <img src="data:image/jpeg;base64,<%= usuario.getCaraBase64() %>" alt="Imagen de la cara" width="100" height="100"/>
                        </td>
                    <td>
                        <form action="actualizarUsuario.jsp" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= usuario.getId() %>">
                            <button type="submit">Editar</button>
                        </form>
                        <form action="eliminarUsuario" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= usuario.getId() %>">
                            <button type="submit" onclick="return confirm('¿Estás seguro de que quieres eliminar este usuario?');">Eliminar</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
            
        </table>
</main>

        <br>
        <br><!--  <br> --> <br>

<!-- Footer -->
<footer>
    <div class="footer-container">
        <div class="footer-column">
            <p>&copy; 2024 Thiago´s Pizza. Todos los derechos reservados.</p>
        </div>
        <div class="footer-column">
            <p>Dirección: San Isidro, Lima.</p>
            <p>Teléfono: +51 999999999</p>
        </div>
        <div class="footer-column">
            <p>Si tienes preguntas, contáctanos al correo: info@Thiago´sPizza.com</p>
        </div>
    </div>
</footer>
<script src="JS/scroll.js" type="text/javascript"></script>
</body>
</html>