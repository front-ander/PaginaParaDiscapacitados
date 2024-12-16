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
        <a href="PaginaPrincipal.jsp">
            <img src="Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    
    <div class="icon-buttons">
        
        <a href="catalogo.jsp"><i class="fas fa-book"></i> Catálogo</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="mascotas.jsp"><i class="fas fa-paw"></i> Mascotas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="servicios.jsp"><i class="fas fa-heart"></i> Servicios</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="contactanos.jsp"><i class="fas fa-phone"></i> Contáctanos </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="perfil.jsp"><i class="fas fa-user"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="carrito.jsp"><i class="fas fa-shopping-cart"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="HistorialCompra.jsp"><i class="fas fa-bars"></i></a>
    </div>
</header>

<!-- Contenido Principal -->
<main>
    <h1>Perfil - Usuario</h1>
    <h3>Información</h3>
    
    <div class="form-container">
        <form action="Eliminar" method="POST">
        <p style="color: black"><strong>ID:</strong> <%= userID %></p>
        <p style="color: black"><strong>DNI:</strong> <%= userDNI %></p>
        <p style="color: black"><strong>Correo:</strong> <%= userEmail %></p>
        <p style="color: black"><strong>Categoría:</strong> <%= userCategory %></p>
        
        <h3>Foto</h3>
        <img src="data:image/jpeg;base64,<%= userPhotoBase64 %>" alt="Foto de usuario" width="250" height="200"/>
        
            <input type="hidden" name="id" value="<%= userID %>">
            <input type="hidden" name="dni" value="<%= userDNI %>"><!-- comment -->
            <input type="hidden" name="correo" value="<%= userEmail %>">
            <input type="hidden" name="categoria" value="<%= userCategory %>">
            <button type="submit">Eliminar</button>
        </form>
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

</body>
</html>