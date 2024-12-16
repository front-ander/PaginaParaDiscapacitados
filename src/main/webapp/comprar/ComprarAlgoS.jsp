<%-- 
    Document   : ComprarAlgo
    Created on : 14 nov 2024, 10:25:53 a.m.
    Author     : dnayj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.ServicioDAO" %>
<%@page import="Logica.Servicios" %>
<%
    String id = request.getParameter("id");
    ServicioDAO servicioDAO = new ServicioDAO();
    Servicios serv = servicioDAO.obtenerPorId(id);
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
        <script src="../scripts/VozCP_1_11.js" type="text/javascript"></script>
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
                <a href="../HistorialCompra.jsp"><i class="fas fa-bars"></i></a>
            </div>
        </header>
        
        <main>
            <div>
            <div class="purchase-container">
                <div style="display: flex; flex-direction: row;  width: 900px; height: auto">
                    <div> <!-- 2 -->
                        <div class="image-section">
                           
                            <img src="data:image/jpeg;base64,<%= serv.getImagenBase64()%>" alt="Imagen de la mascota" style="width: auto; height: 200px"/>
                        </div >  <!-- imagen -->
                        <div >
                            <input type="hidden" name="id" value="<%= serv.getID() %>">
                            <div class="info-item" style=" margin-right: 5px"><strong><h3>Categoría:</strong> <%= serv.getCategoria()%></h3></div>
                            <div class="info-item" style=" margin-right: 5px"><strong><h3>Tiempo:</strong> <%= serv.getTiempo()%></h3></div>
                            <div class="info-item" style=" margin-right: 5px"><strong><h3>Precio:</strong> S/<%= serv.getPrecio()%></h3></div>
                        </div> <!-- datos -->
                        
                        <form action="../ComprarAlgoS" method="POST">
                            <input type="hidden" name="id" value="<%= serv.getID() %>">
                        <button type="submit" id="confirmButton">Comprar</button>
                        </form>
                        <form action="../CarritoS" method="POST">
                            <input type="hidden" name="id" value="<%= serv.getID() %>">
                        <button type="submit"  id="confirmButtonC">Carrito</button>
                        </form>
                        <button type="submit" onclick="window.location.href='../servicios.jsp';" id="cancelButton">Cancelar</button>
                        
                    </div> <!-- 2 -->
                    <div> <!-- 3 -->
                        <div style="display: flex; flex-direction: row; justify-content: space-between; align-items: center;"> <!-- 4 -->
                            <div class="info-item">
                                    <h2><strong>Nombre:</strong> <%= serv.getNombre() %></h2>
                                </div> <!-- nombre -->
                                <div class="info-item" style="background-color: #777; border-radius: 10px">
                                    <h4 style="margin: 3px; padding: 3px;"><strong></strong> <%= serv.getCalificacion() %> ✩</h4>
                                </div> <!-- categoria -->
                        </div> <!-- 4 -->
                        <div class="info-item" >
                            <h2> <%= serv.getDescripcionC()%></h2>
                        </div>
                    </div> <!-- 3 -->
                </div> <!-- 1 -->
            </div> <!-- purchase -->
            </div>
        </main>
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
                        <script src="../JS/scroll.js" type="text/javascript"></script>
                        <script>
    const producto = {
        nombre: "<%= serv.getNombre() %>",
        descripcion: "<%= serv.getDescripcionC()%>",
        calificacion: "<%= serv.getCalificacion()%>",
        precio: "<%= serv.getPrecio() %>"
    };
</script>
    </body>
</html>
