<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="DAO.ServicioDAO" %>
<%@page import="Logica.Servicios" %>
<%@page import="DAO.CategoriaDAO" %>
<%@page import="Logica.Categoria" %>
<%
    String userCategory = (String) session.getAttribute("userCategory");
        //String id = request.getParameter("id");
        //ProductoDAO productoDAO = new ProductoDAO();
        //Producto productos = productoDAO.obtenerPorId(id);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> cates = categoriaDAO.obtenerCategoriasS();
    %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Servicio</title>
    <link href="../../CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="../../CSS/actualizar.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <!-- Header -->
    <header>
    <div class="logo">
        <a href="../../Centro.jsp">
            <img src="../../Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Logo Ojito" class="logo-imagen"/>
        </a>
    </div>
    
    <div class="icon-buttons">
        <a href="../usuarios.jsp" id="usuariosLink" style="display: none;">
        <i class="fas fa-book"></i> Usuarios
    </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../catalogoT.jsp"><i class="fas fa-book"></i> Catálogo</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../mascotasT.jsp"><i class="fas fa-paw"></i> Mascotas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../serviciosT.jsp"><i class="fas fa-heart"></i> Servicios</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../contactanosT.jsp"><i class="fas fa-envelope"></i> Mensaje </a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../../perfilT.jsp"><i class="fas fa-user"></i></a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="../ventasT.jsp"><i class="fas fa-shopping-cart"></i> Ventas</a>
        <div class="separator"></div> <!-- Barra vertical -->
        <a href="#"><i class="fas fa-bars"></i></a>
    </div>
</header>

    <!-- Contenido Principal -->
    <main>
        <h1>Agregar Mascota</h1>
        
        <div class="form-container">
        <form action="../../agregarServicio" method="post" enctype="multipart/form-data">
        <label>Nombre: <input type="text" name="nombre" required></label><br>
        <label>DESCRIPCIÓN: <input type="text" name="descripcion" required></label><br>
        <label>DESCRIPCIÓNC: <input type="text" name="descripcionC" required></label><br>
        <label>PRECIO: <input type="text" name="precio" required></label><br>
        <label>TIEMPO: <input type="text" name="tiempo" required> </label><br>
        <label>CALIFICACIÓN: <input type="text" name="calificacion" required> </label><br>
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
        <label>IMAGEN: <input type="file" name="imagen" accept="image/*" required></label><br>
        <button type="submit">Agregar Proveedor</button>
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
