const cameraFeed = document.getElementById('camera-feed');
const photoCanvas = document.getElementById('photo-canvas');
const userPhoto = document.getElementById('user-photo');
const captureButton = document.getElementById('capture-photo');
const loginForm = document.getElementById('login-form');
const photoInput = document.getElementById('photo'); // Campo oculto para la imagen
let stream;

document.addEventListener('DOMContentLoaded', function() {
    startCamera(); // Inicia la cámara cuando la página esté cargada
    loadFaceApiModels(); // Cargar modelos de face-api.js
});

// Función para iniciar la cámara
async function startCamera() {
    try {
        stream = await navigator.mediaDevices.getUserMedia({
            video: {
                facingMode: "user",
                width: { ideal: 1280 },
                height: { ideal: 720 }
            }
        });
        cameraFeed.srcObject = stream; // Muestra la transmisión en el elemento de video
    } catch (err) {
        console.error("Error al acceder a la cámara: ", err);
        if (err.name === "NotAllowedError") {
            alert("Permiso de la cámara denegado. Por favor, revisa la configuración de tu navegador.");
        } else if (err.name === "NotFoundError") {
            alert("No se detectó ninguna cámara en tu dispositivo.");
        } else if (err.name === "NotReadableError") {
            alert("La cámara está siendo utilizada por otra aplicación.");
        } else {
            alert("Error desconocido al acceder a la cámara: " + err.message);
        }
    }
}

// Cargar modelos de face-api.js
async function loadFaceApiModels() {
    await faceapi.nets.tinyFaceDetector.loadFromUri('models');
}

// Capturar la imagen desde el video
captureButton.addEventListener('click', async () => {
    if (cameraFeed.srcObject) {
        // Ajusta el tamaño del canvas a la resolución del video
        photoCanvas.width = cameraFeed.videoWidth;
        photoCanvas.height = cameraFeed.videoHeight;

        // Limpiar el canvas antes de capturar una nueva imagen
        const context = photoCanvas.getContext('2d');
        context.clearRect(0, 0, photoCanvas.width, photoCanvas.height);

        // Dibuja la imagen capturada en el canvas
        context.drawImage(cameraFeed, 0, 0, photoCanvas.width, photoCanvas.height);

        // Convierte el contenido del canvas en una imagen de base64
        const imageDataUrl = photoCanvas.toDataURL('image/jpeg');
        userPhoto.src = imageDataUrl;  // Actualiza la imagen mostrada
        userPhoto.style.display = 'block';  // Muestra la imagen capturada
        cameraFeed.style.display = 'none';  // Oculta el feed de la cámara

        // Verificar si se detecta una cara
        const faceDetected = await detectFace(photoCanvas);
        if (faceDetected) {
            // Almacena la imagen en el campo oculto del formulario
            photoInput.value = imageDataUrl;

            // Detener la cámara para liberar recursos
            if (stream) {
                stream.getTracks().forEach(track => track.stop());
            }
        } else {
            alert("No se detectó ninguna cara. Por favor, intenta nuevamente.");
            startCamera(); // Reiniciar la cámara si no se detecta una cara
            userPhoto.style.display = 'none';  // Oculta la imagen previa
            cameraFeed.style.display = 'block';  // Muestra nuevamente el feed de la cámara
        }
    } else {
        alert("No hay una cámara activa.");
    }
});


// Detectar cara en la imagen capturada
async function detectFace(canvas) {
    const detection = await faceapi.detectSingleFace(canvas, new faceapi.TinyFaceDetectorOptions());
    return !!detection; // Devuelve true si se detecta una cara, de lo contrario false
}

// Manejar el envío del formulario
loginForm.addEventListener('submit', (e) => {
    const dni = document.getElementById('dni').value;
    const email = document.getElementById('Correo').value;
    const photo = photoInput.value; // La foto en base64 desde el campo oculto

    // Valida que se haya capturado una foto
    if (!photo) {
        alert("Por favor, captura una imagen antes de continuar.");
        e.preventDefault(); // Evita el envío si no hay foto
        return;
    }

    console.log("DNI:", dni);
    console.log("Correo:", email);
    console.log("Foto (Base64):", photo);
});

// Iniciar la cámara cuando se carga la página
window.addEventListener('load', startCamera);

