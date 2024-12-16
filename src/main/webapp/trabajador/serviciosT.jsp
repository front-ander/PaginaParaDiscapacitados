<%-- 
    Document   : servicios
    Created on : 22 oct. 2024, 2:05:52 p. m.
    Author     : dnayj
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Servicios</title>
    <link href="../CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="../CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<%
    // Obtenemos la categoría del usuario desde la sesión
    String userCategory = (String) session.getAttribute("userCategory");
%>
<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="../Centro.jsp">
            <img src="../Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    
    <div class="icon-buttons">
        <a href="../usuarios.jsp" id="usuariosLink" style="display: none;">
        <i class="fas fa-book"></i> Usuarios
    </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="catalogoT.jsp"><i class="fas fa-book"></i> Catálogo</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="mascotasT.jsp"><i class="fas fa-paw"></i> Mascotas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="serviciosT.jsp"><i class="fas fa-heart"></i> Servicios</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="contactanosT.jsp"><i class="fas fa-envelope"></i> Mensajes</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../perfilT.jsp"><i class="fas fa-user"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="ventasT.jsp"><i class="fas fa-shopping-cart"></i> Ventas </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="#"><i class="fas fa-bars"></i> # </a>
    </div>
</header>

<!-- Contenido Principal -->
<main>
    <div class="content">
        <h1>SERVICIOS</h1>
        
        <!-- Lista de productos -->
    <div class="product-list">
        <!-- Producto 1 -->
        <a href="editar/EST.jsp" style="text-decoration: none">
            <div class="product-item">
                <img src="../Imagenes/NIGA.jpg" alt="Producto1"/>
                <h3>EDITAR</h3>
            </div>
        </a>

        <!-- Producto 4 -->
        <a href="editar/AEST.jsp" style="text-decoration: none">
            <div class="product-item">
                <img src="../Imagenes/tikrik.jpg" alt="Producto4"/>
                <h3>AGREGAR</h3>
            </div>
        </a>
        
    </div>
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