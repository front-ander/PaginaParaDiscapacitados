<%-- 
    Document   : carrito
    Created on : 22 oct. 2024, 2:06:34 p. m.
    Author     : danyj
--%>

<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.DetalleCarritoDAO" %>
<%@ page import="Logica.DetalleCarrito" %>
<%@ page import="Logica.Carrito" %>
<%@ page import="DAO.CarritoDAO" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Perfil - OJITOS DE WEVO</title>
    <link href="CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="CSS/tablas.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="PaginaPrincipal.jsp">
            <img src="Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    <div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="Buscar...">
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
    <h1>CARRITO DE DESEOS</h1>
    <div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>PRODUCTO</th>
                <th>PRECIO UNITARIO</th>
                <th>TOTAL</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                CarritoDAO ventaDAO = new CarritoDAO();
                 String userID = (String) session.getAttribute("userID");
                List<Carrito> ventas = ventaDAO.obtenerVentaPorIdUsuario(userID);
                
                for (Carrito ventax : ventas) { 
                
                DetalleCarritoDAO detalleventaDAO = new DetalleCarritoDAO();
                List<DetalleCarrito> detalle = detalleventaDAO.obtenerDetalleVentaPorId(ventax.getId());
                for (DetalleCarrito detallex : detalle) { 
            %>
            <tr class="compras-item">
                <td><%= ventax.getId() %></td>
                <td class="fecha"><%= detallex.getVentas()%></td>
                <td class="total"><%= detallex.getPrecioUnitario()%></td>
                <td><%= detallex.getSubtotal()%></td>
                
                <td>
                    <form action="ELIMINARCARR" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= detallex.getIndice()%>">
                        <input type="hidden" name="vr" value="<%= detallex.getVentas()%>">
                        <button type="submit" class="detalles-button">ELIMINAR</button>
                    </form>
                    
                </td>
            </tr>
            <% } %>
            <form action="COMPRAR" method="POST">
            
            <input type="text" name="id" value="<%= ventax.getId() %>">
            <button type="submit" class="detalles-button">COMPRAR</button>
            
        </form>
            <% } %>
        </tbody>
    </table>
        <br>
        
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