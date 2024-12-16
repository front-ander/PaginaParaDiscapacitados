<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="DAO.UsuarioDAO" %>
<%@page import="Logica.Usuario" %>
<%@page import="DAO.CategoriaDAO" %>
<%@page import="Logica.Categoria" %>
<%
        String id = request.getParameter("id");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obtenerUsuariosPorId(id).get(0);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> cates = categoriaDAO.obtenerCategoriasU();
 // Obtener el usuario
    %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Usuario</title>
    <link href="CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/actualizar.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
        <a href="usuarios.jsp"><i class="fas fa-book"></i> Usuarios</a>
        <div class="separator"></div> <!-- Barra vertical -->
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
        <h1>Actualizar Usuario</h1>
        
        <div class="form-container">
            <form action="actualizarUsuario" method="post">
        <input type="hidden" name="id" value="<%= usuario.getId() %>">
        <label>DNI: <input type="text" name="dni" value="<%= usuario.getDni() %>"></label><br>
        <label>Correo: <input type="text" name="correo" value="<%= usuario.getCorreo() %>"></label><br>
        <label>CATEGORÍA: 
                    <select name="categoria" required>
                        <%
                            for (Categoria cater : cates) {
                        %>
                            <option value="<%= cater.getID() %>"><%= cater.getDescripcion() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </label><br>
        <button type="submit">Actualizar</button>
    </form>
        </div>
    </main>

    <!-- Footer -->
    <footer>
        <div class="footer-container">
            <div class="footer-column">
                <p>&copy; 2024 Thiago´s Pizza. Todos los derechos reservados.</p>
            </div>
        </div>
    </footer>
</body>
</html>
