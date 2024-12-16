<?php
$servername = "localhost";
$username = "root";
$password = "";
//$database = "TechcompanyDB";
$database = "wev";
$port = 3306;

// Crear conexión
$conn = new mysqli($servername, $username, $password, $database, $port);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Obtener datos del formulario
    $dni = $_POST['dni']; // Asegúrate de que el nombre coincida con el del formulario
    $correo = $_POST['email'];
    $photo = $_POST['photo']; // Asegúrate de que este campo esté siendo enviado correctamente

    $dni = $conn->real_escape_string($dni); // Asegúrate de que el nombre coincida con el del formulario
    $correo = $conn->real_escape_string($email);
    $photo = $conn->real_escape_string($photo); 
    
    $sql = "INSERT INTO usuario (ID, DNI, Correo, Cara) VALUES ('$dni', '$email', '$photo')";


    // Ejecutar la consulta
    if ($conn->query($sql) === TRUE) {
        echo "Nuevo registro creado exitosamente";
        header("Location: ../newhtml.html");
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    
        header("Location: /newhtml.html");
    }

}

// Cerrar conexión
$conn->close();
?>


