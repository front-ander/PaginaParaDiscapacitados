<%-- 
    Document   : perfil
    Created on : 22 oct. 2024, 2:06:17 p. m.
    Author     : dnayj
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Perfil - OJITOS DE WEVO</title>
    <link href="CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/actualizar.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<%
    // Obtenemos los datos del usuario desde la sesión
    String userID = (String) session.getAttribute("userID");
    String userDNI = (String) session.getAttribute("userDNI");
    String userEmail = (String) session.getAttribute("userEmail");
    String userCategory = (String) session.getAttribute("userCategory");
    String userPhotoBase64 = (String) session.getAttribute("userPhotoBase64");
%>

<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="Centro.jsp">
            <img src="Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    
    <div class="icon-buttons">
        <a href="usuarios.jsp" id="usuariosLink" style="display: none;">
        <i class="fas fa-book"></i> Usuarios
    </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="trabajador/catalogoT.jsp"><i class="fas fa-book"></i> Catálogo</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="trabajador/mascotasT.jsp"><i class="fas fa-paw"></i> Mascotas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="trabajador/serviciosT.jsp"><i class="fas fa-heart"></i> Servicios</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="trabajador/contactanosT.jsp"><i class="fas fa-envelope"></i> Mensajes </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="perfilT.jsp"><i class="fas fa-user"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="trabajador/ventasT.jsp"><i class="fas fa-shopping-cart"></i> Ventas </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="#"><i class="fas fa-bars"></i> # </a>
    </div>
</header>

<!-- Contenido Principal -->
<main>
    <h1>Perfil - Usuario</h1>
    <h3>Información</h3>
    
    <div class="form-container">
        <p style="color: black"><strong>ID:</strong> <%= userID %></p>
        <p style="color: black"><strong>DNI:</strong> <%= userDNI %></p>
        <p style="color: black"><strong>Correo:</strong> <%= userEmail %></p>
        <p style="color: black"><strong>Categoría:</strong> <%= userCategory %></p>
        
        <h3>Foto</h3>
        <img src="data:image/jpeg;base64,<%= userPhotoBase64 %>" alt="Foto de usuario" width="250" height="200"/>
    </div>
</main>

<!-- Footer -->
<footer>
    <div class="footer-container">
        <div class="footer-column">
            <p>&copy; 2024 Ojitos de Wevo. Todos los derechos reservados.</p>
        </div>
        <div class="footer-column">
            <p>Dirección: San Isidro, Lima.</p>
            <p>Teléfono: +51 999999999</p>
        </div>
        <div class="footer-column">
            <p>Si tienes preguntas, contáctanos al correo: info@ojitosdewevo.com</p>
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