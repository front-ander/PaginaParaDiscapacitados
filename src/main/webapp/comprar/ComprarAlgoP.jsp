<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.ProductoDAO" %>
<%@page import="Logica.Producto" %>
<%
    String id = request.getParameter("id");
    ProductoDAO productoDAO = new ProductoDAO();
    Producto prod = productoDAO.obtenerPorId(id);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Comprar</title>
    <link href="../CSS/estiloprincipal.css" rel="stylesheet" type="text/css"/>
    <link href="../CSS/compras-productos.css" rel="stylesheet" type="text/css"/>
    <link href="../CSS/compra.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="../scripts/VozCp.js" type="text/javascript"></script>
</head>

<body>
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
        <div class="dropdown">
            <a href="#"><i class="fas fa-bars"></i></a>
            <div class="dropdown-content">
                <a href="../mascotas.jsp">Mascotas</a>
                <a href="../servicios.jsp">Servicios</a>
                <a href="../contactanos.jsp">Contactanos</a>
            </div>
        </div>
    </div>
    </header>

    <main>
    <div>
        <form action="../ComprarAlgoP" method="POST">
            <div class="purchase-container">
                <div style="display: flex; flex-direction: row; width: 900px; height: auto">
                    <div> 
                        <div class="image-section">
                            <img src="data:image/jpeg;base64,<%= prod.getImagenBase64()%>" alt="Imagen del producto" style="width: auto; height: 200px"/>
                        </div>
                        <div>
                            <input type="hidden" name="id" value="<%= prod.getID() %>">
                            <div class="info-item" style=" margin-right: 5px"><strong><h3>Categoría:</strong> <%= prod.getCategoria()%></h3></div>
                            <div class="info-item" style=" margin-right: 5px"><strong><h3>Precio:</strong> S/<%= prod.getPrecio()%></h3></div>
                            <input type="number" name="cantidad" id="cantidad" placeholder="0" style=" border-radius: 5px; margin-bottom: 7px" required><br>
                        </div>
                        
                        <button type="submit" id="confirmButton">Comprar</button>
                    </form>
                    <form action="../CarritoP" method="POST" id="formCarrito">
                        <input type="hidden" name="id" value="<%= prod.getID() %>">
                        <input type="hidden" name="cantidad" id="cantidadCarrito"> <!-- Campo oculto para cantidad -->
                        <button type="submit"  id="confirmButtonC" onclick="copyCantidadToCarrito()">Carrito</button>
                    </form>
                    <button type="submit" onclick="window.location.href='../catalogo.jsp';" id="cancelButton">Cancelar</button>
                </div>
                <div style="margin-right: 20px"> 
                    <div style="display: flex; flex-direction: row; justify-content: space-between; align-items: center;">
                        <div class="info-item">
                            <h2><strong>Nombre:</strong> <%= prod.getNombre() %></h2>
                        </div>
                    </div>
                    <div class="info-item">
                        <h2> <%= prod.getDescripcion()%></h2>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <footer>
        <!-- Footer content here -->
    </footer>

    <script src="../JS/scroll.js" type="text/javascript"></script>
    <script>
    const producto = {
        nombre: "<%= prod.getNombre() %>",
        descripcion: "<%= prod.getDescripcion() %>",
        precio: "<%= prod.getPrecio() %>"
    };
</script>
<script>
    function copyCantidadToCarrito() {
        var cantidad = document.getElementById('cantidad').value;
        
        // Si el campo de cantidad está vacío, asigna el valor "0"
        if (cantidad === "") {
            cantidad = "1";
        }
        
        document.getElementById('cantidadCarrito').value = cantidad;
    }
</script>


</body>
</html>