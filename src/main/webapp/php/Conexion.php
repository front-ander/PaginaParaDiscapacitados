<?php
$servername = "localhost";
$username = "root";
$password = "";
//$database = "TechcompanyDB";
$database = "";
$port = 3306;

// Crear conexión
$conn = new mysqli($servername, $username, $password, $database, $port);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}
?>
