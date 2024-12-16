<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.ServicioDAO" %>
<%@ page import="Logica.Servicios" %>
<%
    String userCategory = (String) session.getAttribute("userCategory");
    String catBusqueda = request.getParameter("dni"); // Obtener el parámetro de búsqueda
    ServicioDAO mascotaDAO = new ServicioDAO();
    List<Servicios> mac;
    
    if (catBusqueda != null && !catBusqueda.isEmpty()) {
        mac = mascotaDAO.obtenerPorCategoria(catBusqueda); // Obtener solo los usuarios que coinciden con el DNI
    } else {
        mac = mascotaDAO.obtenerTodos(); // Obtener todos los usuarios si no hay búsqueda
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mascotas</title>
    <link href="../../CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="../../CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
    <link href="../../CSS/tablas.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="../../Centro.jsp">
            <img src="../../Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    <div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="Buscar...">
    </div>
    <div class="icon-buttons">
        <a href="../usuarios.jsp" id="usuariosLink" style="display: none;">
        <i class="fas fa-book"></i> Usuarios
    </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../catalogoT.jsp"><i class="fas fa-book"></i> Catálogo</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../mascotasT.jsp"><i class="fas fa-paw"></i> Mascotas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../serviciosT.jsp"><i class="fas fa-heart"></i> Servicios</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../contactanosT.jsp"><i class="fas fa-envelope"></i> Mensaje </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../../perfilT.jsp"><i class="fas fa-user"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../ventasT.jsp"><i class="fas fa-shopping-cart"></i> Ventas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="#"><i class="fas fa-bars"></i></a>
    </div>
</header>

<!-- Contenido Principal -->
<main>
    <h1>Gestión de MASCOTAS</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>DESCRIPCION</th>
                <th>PRECIO</th>
                <th>TIEMPO</th>
                <th>CALIFICACIÓN</th>
                <th>CATEGORÍA</th>
                <th>IMAGEN</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                
                for (Servicios serv : mac) { 
            %>
            <tr>
                <td><%= serv.getID()%></td>
                <td><%= serv.getNombre()%></td>
                <td><%= serv.getDescripcion()%></td>
                <td><%= serv.getPrecio()%></td>
                <td><%= serv.getTiempo()%></td>
                <td><%= serv.getCalificacion()%></td>
                <td><%= serv.getCategoria() %></td>
                <td>
                        <img src="data:image/jpeg;base64,<%= serv.getImagenBase64() %>" alt="Imagen de la cara" width="100" height="100"/>
                    </td>
                <td>
                    <form action="EEST.jsp" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= serv.getID() %>">
                        <button type="submit">Editar</button>
                    </form>
                    <form action="#" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= serv.getID() %>">
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
<script>
    // Pasar la categoría del usuario desde JSP a JavaScript
    const userCategory = '<%= userCategory %>';

    // Mostrar el enlace solo si el usuario es administrador
    if (userCategory === 'Admin') {
        document.getElementById('usuariosLink').style.display = 'inline';
    }
</script>
</body>
</html>