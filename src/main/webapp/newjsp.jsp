<%-- 
    Document   : newjsp
    Created on : 26 oct 2024, 5:01:34 p.m.
    Author     : dnayj
--%>

<%@page import="Logica.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.UsuarioDAO" %>
<script src="faceapi/face-api.min.js"></script>

<%
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Usuarios</title>
    </head>
    <body>
        <h1>Lista de Usuarios</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>DNI</th>
                <th>Correo</th>
                <th>Categoría</th>
                <th>Cara</th>
            </tr>
            <%
                for (Usuario usuario : usuarios) {
            %>
                <tr>
                    <td><%= usuario.getId() %></td>
                    <td><%= usuario.getDni() %></td>
                    <td><%= usuario.getCorreo() %></td>
                    <td><%= usuario.getCategoria()%></td>
                    <td>
                        <img src="data:image/jpeg;base64,<%= usuario.getCaraBase64() %>" alt="Imagen de la cara" width="100" height="100"/>
                    </td>
                </tr>
            <%
                }
            %>
        </table>

        <h2>Resultados de Comparación de Caras</h2>
        <div id="resultados">Cargando modelos y detectando rostros...</div> <!-- Mensaje inicial -->

        <script>
            window.addEventListener("load", async function() {
                const resultadosDiv = document.getElementById("resultados");

                async function loadModels() {
                    try {
                        resultadosDiv.innerHTML = "Cargando modelos de Face API...";
                        await faceapi.nets.ssdMobilenetv1.loadFromUri('models');
                        await faceapi.nets.faceLandmark68Net.loadFromUri('models');
                        await faceapi.nets.faceRecognitionNet.loadFromUri('models');
                        resultadosDiv.innerHTML = "Modelos cargados correctamente. Detectando rostros...";
                    } catch (error) {
                        resultadosDiv.innerHTML = "Error al cargar los modelos.";
                        console.error("Error al cargar los modelos:", error);
                    }
                }

                async function compareFaces() {
                    try {
                        const images = document.querySelectorAll('img'); // Selecciona todas las imágenes con caras
                        const descriptors = [];

                        // Detecta rostros y calcula descriptores
                        for (let img of images) {
                            const detection = await faceapi.detectSingleFace(img).withFaceLandmarks().withFaceDescriptor();
                            if (detection) {
                                descriptors.push({ descriptor: detection.descriptor, img });
                            } else {
                                resultadosDiv.innerHTML += `<p>No se detectó rostro en una de las imágenes.</p>`;
                                console.warn("No se detectó rostro en una de las imágenes.");
                            }
                        }

                        // Si no hay suficientes descriptores, no realizar la comparación
                        if (descriptors.length < 2) {
                            resultadosDiv.innerHTML += "<p>No se detectaron suficientes rostros para comparar.</p>";
                            return;
                        }

                        resultadosDiv.innerHTML = "<p>Comparando rostros...</p>";

                        // Compara descriptores
                        for (let i = 0; i < descriptors.length - 1; i++) {
                            for (let j = i + 1; j < descriptors.length; j++) {
                                const distance = faceapi.euclideanDistance(descriptors[i].descriptor, descriptors[j].descriptor);
                                const resultText = `Distancia entre imagen ${i + 1} y imagen ${j + 1}: ${distance.toFixed(2)}`;
                                
                                if (distance < 0.6) { // Umbral para similitud
                                    resultadosDiv.innerHTML += `<p>${resultText} - <strong>Similares</strong></p>`;
                                } else {
                                    resultadosDiv.innerHTML += `<p>${resultText} - No Similares</p>`;
                                }
                            }
                        }
                    } catch (error) {
                        resultadosDiv.innerHTML = "Error al detectar o comparar rostros.";
                        console.error("Error en la detección o comparación de rostros:", error);
                    }
                }

                // Cargar modelos y luego comparar caras
                await loadModels();
                compareFaces();
            });
        </script>
    </body>
</html>