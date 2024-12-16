<%-- 
    Document   : paginaprincipal
    Created on : 22 oct. 2024, 1:57:46 p. m.
    Author     : Sebastian
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pagina Principal - OJITOS DE WEVO</title>
    <link href="CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    
    <link href="CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
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
        <a href="index.jsp">
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
        <a href="trabajador/contactanosT.jsp"><i class="fas fa-heart"></i> Mensajes</a>
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
    <div class="content">
        <h1>Bienvenido a OJITOS DE WEVO</h1>
        <p>IMAGENES MOTIVACIONALES, SÉ QUE ERES CIEGO</p>
        
        <!-- Lista de productos -->
    <div class="product-list">
        <!-- Producto 1 -->
        
            <div class="product-item">
                <img src="Imagenes/gaaaa/Rad dejame decirte algo.png" alt="Producto1"/>
                <h3>?_?</h3>
            </div>

        <!-- Producto 4 -->
            <div class="product-item">
                <img src="Imagenes/gaaaa/ºOº.jpg" alt="Producto4"/>
                <h3>º0º</h3>
            </div>
        
        <!-- Producto 8 -->
            <div class="product-item">
                <img src="Imagenes/gaaaa/Dino, dado multicaras.jpg" alt="Producto8"/>
                <h3>ª_ª</h3>
            </div>
        <div class="product-item">
            <img src="Imagenes/gaaaa/-_-.jpg" alt="Producto9"/>
                <h3>-_-</h3>
            </div>
        
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