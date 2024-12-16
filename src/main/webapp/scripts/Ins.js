const cameraFeed = document.getElementById('camera-feed');
const photoCanvas = document.getElementById('photo-canvas');
const userPhoto = document.getElementById('user-photo');
const captureButton = document.getElementById('capture-photo');
const loginForm = document.getElementById('login-form');

let stream;

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
        cameraFeed.style.display = 'block';
        userPhoto.style.display = 'none';
    } catch (err) {
        console.error("Error al acceder a la cámara: ", err);
    }
}

function stopCamera() {
    if (stream) {
        stream.getTracks().forEach(track => track.stop());
        stream = null;
    }
}

captureButton.addEventListener('click', () => {
    photoCanvas.width = cameraFeed.videoWidth;
    photoCanvas.height = cameraFeed.videoHeight;
    photoCanvas.getContext('2d').drawImage(cameraFeed, 0, 0);
    userPhoto.src = photoCanvas.toDataURL('image/jpeg');
    cameraFeed.style.display = 'none';
    userPhoto.style.display = 'block';
    stopCamera();
});

loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const dni = document.getElementById('dni').value;
    const photo = userPhoto.src.split(',')[1];

    try {
        const response = await fetch('InSesTrajServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `dni=${encodeURIComponent(dni)}&photo=${encodeURIComponent(photo)}`
        });

        if (!response.ok) throw new Error("Usuario no encontrado o error en el servidor");

        const result = await response.json();
        const savedPhotoBase64 = result.caraBase64;
        const categoria = result.categoria;

        if (categoria === "Baja") {
            alert("Acceso denegado. Usuario no autorizado.");
            startCamera();
            return;
        }

        await Promise.all([
            faceapi.nets.ssdMobilenetv1.loadFromUri('models'),
            faceapi.nets.faceLandmark68Net.loadFromUri('models'),
            faceapi.nets.faceRecognitionNet.loadFromUri('models')
        ]);

        const capturedImage = await faceapi.bufferToImage(await (await fetch(`data:image/jpeg;base64,${photo}`)).blob());
        const savedImage = await faceapi.bufferToImage(await (await fetch(`data:image/jpeg;base64,${savedPhotoBase64}`)).blob());

        const capturedDescriptor = await faceapi.detectSingleFace(capturedImage).withFaceLandmarks().withFaceDescriptor();
        const savedDescriptor = await faceapi.detectSingleFace(savedImage).withFaceLandmarks().withFaceDescriptor();

        if (capturedDescriptor && savedDescriptor) {
            const distance = faceapi.euclideanDistance(capturedDescriptor.descriptor, savedDescriptor.descriptor);
            
            console.log("Distancia entre las caras:", distance);
            

            if (distance < 0.6) {
                
                
                window.location.href = "PaginaPrincipal.jsp";
            } else {
                alert("Las caras no coinciden. Acceso denegado.");
                startCamera();
            }
        } else {
            alert("No se pudo detectar una cara en una o ambas imágenes.");
            startCamera();
        }
    } catch (error) {
        console.error('Error:', error);
        alert("Error: " + error.message);
        startCamera();
    }
});

window.addEventListener('load', startCamera);
