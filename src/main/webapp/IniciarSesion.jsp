<%-- 
    Document   : IniciarSesion
    Created on : 22 oct 2024, 9:44:46 a.m.
    Author     : dnayj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="faceapi/face-api.min.js"></script>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión  Ojitos de Wevo</title>
    <link rel="stylesheet" href="estilos/Ins.css">
    <script src="scripts/Ins.js" defer></script>
    <script defer src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs"></script>
    <script defer src="https://cdn.jsdelivr.net/npm/@vladmandic/face-api"></script>
    <script src="scripts/VozI.js" type="text/javascript"></script>
</head>
<body>
    <header>
        <div style="display: flex; align-items: center;">
            <a href="index.jsp"><img src="Imagenes/Logo/LogoOjitosDeWevo-removebg.png" alt="Dino, dado multicaras" style="width: 50px; height: auto; margin-right: 10px;"></a>
            <h1>Ojitos de Wevo</h1>
        </div>
    </header>
    
    <main>
        <div class="login-container">
            <h2>Iniciar Sesión</h2>
            <form action="InSesTrajServlet" method="POST" class="login-form" id="login-form">
                <label for="dni">DNI:</label>
                <input type="text" id="dni" name="dni" placeholder="Ingrese su DNI" required maxlength="8" pattern="\d{8}" title="Por favor, ingrese 8 números">
                <div id="camera-container">
                    <video id="camera-feed" autoplay playsinline></video>
                    <canvas id="photo-canvas" style="display: none;"></canvas>
                    <img id="user-photo" name="photo" alt="Foto del usuario" style="display: none;">
                </div>
                <button type="button" id="capture-photo">Capturar Foto</button>
                <button type="submit" id="submit-button">Iniciar Sesión</button>
            </form>
        </div>
    </main>
    
    <footer>
        <p>&copy; 2024 Ojitos de Wevo. Todos los derechos reservados.</p>
    </footer>
    
</body>
</html>
</html>