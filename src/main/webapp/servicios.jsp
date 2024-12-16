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
    <link href="CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="scripts/VozCAt_1_1.js" type="text/javascript"></script>
</head>
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
    <div class="content">
        <h1>Catálogo de Productos</h1>
        <p>Explora nuestra variedad de productos.</p>
        
        <!-- Lista de productos -->
    <div class="product-list">
        <!-- Producto 1 -->
        <a href="Servicios/RehabilitaTerapi.jsp" style="text-decoration: none">
            <div class="product-item">
                <img src="Imagenes/servicios/Rehabilitacion-o-fisioterapia.jpg" alt="Producto1"/>
                <h3>REHABILITACIÓN Y TERAPIAS</h3>
            </div>
        </a>

        <!-- Producto 4 -->
        <a href="Servicios/MovilidadOrientacion.jsp" style="text-decoration: none">
            <div class="product-item">
                <img src="Imagenes/servicios/gta.jpg" alt="Producto4"/>
                <h3>MOVILIDAD Y ORIENTACIÓN</h3>
            </div>
        </a>
        
        <!-- Producto 8 -->
        <a href="Servicios/ApoyoAsesoramiento.jsp" style="text-decoration: none">
            <div class="product-item">
                <img src="Imagenes/servicios/manos-en-circulo.jpg" alt="Producto8"/>
                <h3>APOYO Y ASESORAMIENTO</h3>
            </div>
        </a>
        <a href="Servicios/General.jsp" style="text-decoration: none">
            <div class="product-item">
                <img src="Imagenes/gaaaa/ºOº.jpg" alt="Producto8"/>
                <h3>General</h3>
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
</body>
</html>