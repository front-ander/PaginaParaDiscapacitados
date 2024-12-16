<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.VentaDAO" %>
<%@ page import="Logica.Venta" %>
<%@ page import="DAO.DetalleVentaDAO" %>
<%@ page import="Logica.DetalleVenta" %>
<%@ page import="DAO.CategoriaDAO" %>
<%@ page import="Logica.Categoria" %>
<%
    
    String userCategory = (String) session.getAttribute("userCategory");
    CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> cates = categoriaDAO.obtenerEstado();
    
    
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mensajes - Ojitos de Wevo</title>
    <link href="../CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="../CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #333;
            color: #000; /* Color negro para el texto */
        }

        .container {
            margin-top: 30px;
        }

        h2 {
            color: #000; /* Título en negro */
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        table th, table td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            color: #000; /* Texto negro en la tabla */
        }

        table th {
            background-color: #495057;
            color: white;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        .alert {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 5px;
        }

        .alert-success {
            color: #155724;
            background-color: #d4edda;
            border-color: #c3e6cb;
        }

        .alert-danger {
            color: #721c24;
            background-color: #f8d7da;
            border-color: #f5c6cb;
        }

        .button-danger {
            color: white;
            background-color: #dc3545;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .button-danger:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<!-- Header -->
<header>
    <div class="logo">
        <a href="../Centro.jsp">
            <img src="/PasandoOW/Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>

    <div class="icon-buttons">
        <a href="../usuarios.jsp" id="usuariosLink" style="display: none;">
            <i class="fas fa-book"></i> Usuarios
        </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="/PasandoOW/trabajador/catalogoT.jsp"><i class="fas fa-book"></i> Catálogo</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="/PasandoOW/trabajador/mascotasT.jsp"><i class="fas fa-paw"></i> Mascotas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="/PasandoOW/trabajador/serviciosT.jsp"><i class="fas fa-heart"></i> Servicios</a>
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
        <h2>Ventas</h2>

        <!-- Mostrar mensajes de éxito o error -->
        <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>FECHA</th>
                <th>TOTAL</th>
                <th>METODO DE PAGO</th>
                <th>ESTADO</th>
                <th>ACTUALIZAR ESTADO</th>
            </tr>
        </thead>
        <tbody>
            <% 
                VentaDAO ventaDAO = new VentaDAO();
                List<Venta> ventas = ventaDAO.obtenerVenta();
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
                    <form action="../actualizarVenta" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= detallex.getIdDetalleVenta()%>">
                        <label>
                    <select name="estado" required>
                        <%
                            for (Categoria cater : cates) {
                        %>
                            <option  value="<%= cater.getID()%>"><%= cater.getDescripcion() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </label>
                    <button type="submit" class="button-danger">ACTUALIZAR</button>
                    </form>
                    
                </td>
            </tr>
            <% } %>
            <% } %>
        </tbody>
    </table>
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
