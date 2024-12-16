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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="scripts/Idx.js" type="text/javascript"></script>
</head>
<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="index.jsp">
            <img src="Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    <!--<div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="Buscar...">
    </div>-->
    <div class="icon-buttons">
        <a href="Registrar.jsp" id="LOG"><i class="fas fa-book"></i> Registrar</a>
        <div class="separator" ></div> <!-- Barra vertical -->
        <a href="IniciarSesion.jsp" id="INS"><i class="fas fa-user"></i> Iniciar Sesion</a>
        <div class="dropdown">
            <a href="#"><i class="fas fa-bars"></i></a>
            <div class="dropdown-content">
                <a href="IniciarSesionTrabajador.jsp">TRABAJADOR</a>
            </div>
        </div>
    </div>
</header>

<!-- Contenido Principal -->
<main>
    <h1>Bienvenido a Ojitos de Wevo</h1>
    <p>Ojitos de Wevo es tu aliado en la mejora de la calidad de vida para personas con discapacidades. Ofrecemos productos innovadores y servicios personalizados diseñados para apoyar la independencia, el bienestar y la felicidad.</p>
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