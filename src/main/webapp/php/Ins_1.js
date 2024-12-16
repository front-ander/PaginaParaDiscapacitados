        const cameraFeed = document.getElementById('camera-feed');
const photoCanvas = document.getElementById('photo-canvas');
const userPhoto = document.getElementById('user-photo');
const captureButton = document.getElementById('capture-photo');
const loginForm = document.getElementById('login-form');

let stream;

// Iniciar la cámara
async function startCamera() {
    try {
        stream = await navigator.mediaDevices.getUserMedia({
            video: {
                facingMode: "user",
                width: { ideal: 1280 },
                height: { ideal: 720 }
            }
        });
        cameraFeed.srcObject = stream;
    } catch (err) {
        console.error("Error al acceder a la cámara: ", err);
    }
}

// Capturar foto
captureButton.addEventListener('click', () => {
    photoCanvas.width = cameraFeed.videoWidth;
    photoCanvas.height = cameraFeed.videoHeight;
    photoCanvas.getContext('2d').drawImage(cameraFeed, 0, 0);
    userPhoto.src = photoCanvas.toDataURL('image/jpeg');
    cameraFeed.style.display = 'none';
    userPhoto.style.display = 'block';
    if (stream) {
        stream.getTracks().forEach(track => track.stop());
    }
});

// Manejar envío del formulario
loginForm.addEventListener('submit', async (e) => {
    e.preventDefault(); // Evitar el envío normal del formulario
    const dni = document.getElementById('dni').value;
    const photo = userPhoto.src.split(',')[1]; // La imagen capturada en Base64 sin el prefijo

    try {
        // Enviar datos al servidor
        const response = await fetch('InSesServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `dni=${encodeURIComponent(dni)}&photo=${encodeURIComponent(photo)}`
        });

        if (!response.ok) throw new Error("Usuario no encontrado o error en el servidor");

        const result = await response.json();
        const savedPhotoBase64 = result.caraBase64;

        // Cargar modelos de face-api.js
        await Promise.all([
            faceapi.nets.ssdMobilenetv1.loadFromUri('models'),
            faceapi.nets.faceLandmark68Net.loadFromUri('models'),
            faceapi.nets.faceRecognitionNet.loadFromUri('models')
        ]);

        // Convertir ambas fotos a formato Image
        const capturedImage = await faceapi.bufferToImage(await (await fetch(`data:image/jpeg;base64,${photo}`)).blob());
        const savedImage = await faceapi.bufferToImage(await (await fetch(`data:image/jpeg;base64,${savedPhotoBase64}`)).blob());

        // Detectar y comparar caras
        const capturedDescriptor = await faceapi.detectSingleFace(capturedImage).withFaceLandmarks().withFaceDescriptor();
        const savedDescriptor = await faceapi.detectSingleFace(savedImage).withFaceLandmarks().withFaceDescriptor();

        if (capturedDescriptor && savedDescriptor) {
            const distance = faceapi.euclideanDistance(capturedDescriptor.descriptor, savedDescriptor.descriptor);
            console.log("Distancia entre las caras:", distance);

            // Verificar similitud con un umbral
            if (distance < 0.6) {
                alert("Bienvenido, acceso concedido");
                window.location.href = "PaginaPrincipal.jsp"; // Redirigir si las caras coinciden
            } else {
                alert("Las caras no coinciden. Acceso denegado.");
            }
        } else {
            alert("No se pudo detectar una cara en una o ambas imágenes.");
        }
    } catch (error) {
        console.error('Error:', error);
        alert("Error: " + error.message);
    }
});

// Iniciar la cámara al cargar la página
window.addEventListener('load', startCamera);
