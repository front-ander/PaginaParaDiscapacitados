<%-- 
    Document   : index
    Created on : 22 oct 2024, 9:39:30 a.m.
    Author     : dnayj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Inicio</title>
    <link rel="stylesheet" href="estilos/Idx.css">
    <script src="scripts/Idx.js"></script>
</head>
<body>
    <header>
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <div style="display: flex; align-items: center;">
                <a href="index.jsp"><img src="Imagenes/Dino, dado multicaras.jpg" alt="Dino, dado multicaras" style="width: 50px; height: auto; margin-right: 10px;"></a>
                
                <h1>Ojitos de Wevo</h1>
            </div>
            <nav>
                <button onclick="window.location.href='IniciarSesion.jsp'" id="INS">Iniciar Sesión</button>
                <button onclick="window.location.href='Registrar.jsp'" id="LOG">Registrar</button>
                <button>Carrito</button>
                <button>Historial de Compras</button>
            </nav>
        </div>
    </header>
    
    <main>
        <h2>Bienvenido a Ojitos de Wevo</h2>
        <p>Ojitos de Wevo es tu aliado en la mejora de la calidad de vida para personas con discapacidades. Ofrecemos productos innovadores y servicios personalizados diseñados para apoyar la independencia, el bienestar y la felicidad.</p>
    </main>
    
    <footer>
        <p>&copy; 2024 Ojitos de Wevo. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
