<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.VentaDAO" %>
<%@ page import="Logica.Venta" %>
<%@ page import="DAO.DetalleVentaDAO" %>
<%@ page import="Logica.DetalleVenta" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Compras</title>
    <link href="CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/tablas.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="scripts/VozHi.js" type="text/javascript"></script>
</head>
<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="PaginaPrincipal.jsp" class="back-link">
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
    <h1>Registro de compras</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>FECHA</th>
                <th>TOTAL</th>
                <th>METODO DE PAGO</th>
                <th>ESTADO</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                VentaDAO ventaDAO = new VentaDAO();
                 String userID = (String) session.getAttribute("userID");
                List<Venta> ventas = ventaDAO.obtenerVentaPorId(userID);
                for (Venta ventax : ventas) { 
                
                DetalleVentaDAO detalleventaDAO = new DetalleVentaDAO();
                List<DetalleVenta> detalle = detalleventaDAO.obtenerDetalleVentaPorId(ventax.getIdVenta());
                for (DetalleVenta detallex : detalle) { 
            %>
            <tr class="compras-item">
                <td><%= ventax.getIdVenta() %></td>
                <td class="fecha"><%= ventax.getFecha() %></td>
                <td class="total"><%= ventax.getTotal() %></td>
                <td><%= ventax.getMetodoPago() %></td>
                <td><%= detallex.getEstado()%></td>
                
                <td>
                    <form action="Detalles.jsp" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= ventax.getIdVenta() %>">
                        <button type="submit" class="detalles-button">DETALLES</button>
                    </form>
                    <!--<form action="eliminarUsuario" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= ventax.getIdVenta() %>">
                        <button type="submit" onclick="return confirm('¿Estás seguro de que quieres eliminar este usuario?');">Eliminar</button>
                    </form>-->
                </td>
            </tr>
            <% } %>
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
</body>
</html>