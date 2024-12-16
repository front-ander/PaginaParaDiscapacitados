<%-- 
    Document   : paginaprincipal
    Created on : 22 oct. 2024, 1:57:46 p. m.
    Author     : dnayj
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
    <script src="scripts/VozPPF.js" type="text/javascript"></script>
    
</head>
<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="index.jsp">
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
        <h1>Bienvenido a OJITOS DE WEVO</h1>
        <p>IMAGENES MOTIVACIONALES, NO IMPORTA LO QUE DIGAN TU ERES PERFECTO</p>
        
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