<%--  
    Document   : Login
    Created on : 22 oct 2024, 9:45:06 a.m.
    Author     : dnayj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="estilos/Log.css">
    <script src="scripts/Log.js" defer></script>
    <script src="scripts/Vozr.js" type="text/javascript"></script>
    <script src="faceapi/face-api.js" type="text/javascript"></script>
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
            <h2>Regístrate</h2>
            <form action="RegistrarUsuarioServlet" method="POST" class="login-form" id="login-form" enctype="multipart/form-data">
                <label for="dni">DNI:</label>
                <input type="text" id="dni" name="dni" placeholder="Ingrese su DNI" required maxlength="8" pattern="\d{8}" title="Por favor, ingrese 8 números"><br><br>

                <label for="email">Correo Electrónico:</label>
                <input type="email" id="Correo" name="Correo" placeholder="Ingrese su Correo" required maxlength="100" title="Por favor, ingrese su correo"><br><br>

                <div id="camera-container">
                    <video id="camera-feed" autoplay playsinline></video>
                    <canvas id="photo-canvas" style="display: none;"></canvas>
                    <img id="user-photo" alt="Foto del usuario" style="display: none;">
                </div>
                <input type="hidden" name="photo" id="photo">

                <button type="button" id="capture-photo">Capturar Foto</button>
                <button type="submit" id="submit-button">Regístrate</button>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Ojitos de Wevo. Todos los derechos reservados.</p>
    </footer>

</body>

</html>