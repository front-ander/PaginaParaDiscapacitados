<%@page import="java.util.List"%>
<%@page import="DAO.CategoriaDAO"%>
<%@page import="Logica.Categoria"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="DAO.ProductoDAO" %>
<%@page import="Logica.Producto" %>
<%
        String id = request.getParameter("id");
        ProductoDAO productoDAO = new ProductoDAO();
        Producto productos = productoDAO.obtenerPorId(id);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> cates = categoriaDAO.obtenerCategoriasP();
 // Obtener el usuario
    %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Producto</title>
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
        <a href="../../trabajador/contactanosT.jsp"><i class="fas fa-envelope"></i> Mensaje </a>
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
        <h1>Actualizar Producto</h1>
        
        <div class="form-container">
            <form action="../../actualizarProducto" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= productos.getID() %>">
        <label>Nombre: <input type="text" name="nombre" value="<%= productos.getNombre()%>"></label><br>
        <label>Descripcion: <input type="text" name="descripcion" value="<%= productos.getDescripcion()%>"></label><br>
        <label>Precio: <input type="text" name="precio" value="<%= productos.getPrecio()%>"></label><br>
        <label>Stock: <input type="text" name="stock" value="<%= productos.getStock()%>"></label><br>
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
        <label>IMAGEN: <input type="file" name="imagen" accept="image/*" ></label><br>
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
