<%-- 
    Document   : servicios
    Created on : 22 oct. 2024, 2:05:52 p. m.
    Author     : dnayj
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="Logica.Servicios"%>
<%@page import="DAO.ServicioDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Movilidad y Orientación</title>
    <link href="../CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="../CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="../scripts/VozCoC_1_1.js" type="text/javascript"></script>
</head>
<%
    ServicioDAO servicioDAO = new ServicioDAO();
    String cat = "Movilidad";
    List<Servicios> servicios = servicioDAO.obtenerPorCategoria(cat);
%>
<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="../PaginaPrincipal.jsp">
            <img src="../Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    <div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="Buscar...">
    </div>
    <div class="icon-buttons">
        <a href="../catalogo.jsp"><i class="fas fa-book"></i> Catálogo</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../mascotas.jsp"><i class="fas fa-paw"></i> Mascotas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../servicios.jsp"><i class="fas fa-heart"></i> Servicios</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../contactanos.jsp"><i class="fas fa-phone"></i> Contáctanos </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../perfil.jsp"><i class="fas fa-user"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../carrito.jsp"><i class="fas fa-shopping-cart"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../HistorialCompra.jsp"><i class="fas fa-bars"></i></a>
    </div>
</header>

<!-- Contenido Principal -->
<main>
    <div class="product-list">
        <!-- Producto 1 -->
        <%
            for (Servicios servi : servicios) {
        %>
            <div class="product-item">
                <form action="../comprar/ComprarAlgoS.jsp" method="post">
                <h3><%= servi.getNombre() %></h3>
                <input type="hidden" name="id" value="<%= servi.getID() %>">
                <img src="data:image/jpeg;base64,<%= servi.getImagenBase64()%>" alt="Imagen de la cara" width="100" height="100"/>     
                <p><%= servi.getDescripcion()%></p>
                <p class="time"> Tiempo: <%= servi.getTiempo()%></p>
                <p class="cali"> Calificacion: <%= servi.getCalificacion()%></p>
                
                <p style="display: none"><%= servi.getCategoria()%></p>
                <span class="price"> Precio: <%= servi.getPrecio()%></span>
                <button type="submit">Comprar</button>
                </form>
            </div>
        <%
            }
        %>

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