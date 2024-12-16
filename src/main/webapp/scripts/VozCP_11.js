document.addEventListener("DOMContentLoaded", () => { 
    const confirmButton = document.getElementById("confirmButton");
    const cancelButton = document.getElementById("cancelButton");
    const confirmButtonC = document.getElementById("confirmButtonC"); // Obtener el botón de carrito

    // Asegúrate de que el objeto 'producto' esté definido previamente
    if (!producto) {
        console.error("El objeto 'producto' no está definido.");
        return;
    }

    const { nombre, descripcion, calificacion, precio } = producto;

    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    if (SpeechRecognition) {
        const recognition = new SpeechRecognition();
        recognition.lang = "es-ES";
        recognition.continuous = false;

        let isListening = false; // Controla si está escuchando actualmente

        document.addEventListener("keydown", (event) => {
            if (event.key.toLowerCase() === "f" && !isListening) {
                isListening = true; // Evita activaciones múltiples
                const message = `El nombre de la mascota es ${nombre}. Descripción: ${descripcion}. Calificación: ${calificacion}. Precio: ${precio}. ¿Deseas confirmar la compra, agregar al carrito o cancelar?`;
                speak(message);

                // Esperar a que termine de hablar antes de empezar a escuchar
                const synth = window.speechSynthesis;
                const checkIfSpeaking = setInterval(() => {
                    if (!synth.speaking) {
                        clearInterval(checkIfSpeaking);
                        recognition.start(); // Iniciar reconocimiento de voz después de hablar
                    }
                }, 100);
            }
        });

        recognition.onresult = (event) => {
            const transcript = event.results[0][0].transcript.toLowerCase();
            if (transcript.includes("confirmar")) {
                speak("Compra confirmada.");
                confirmButton?.click();
            } else if (transcript.includes("carrito")) {
                speak("Producto agregado al carrito.");
                confirmButtonC?.click(); // Activar el botón de carrito
            } else if (transcript.includes("cancelar")) {
                speak("Compra cancelada.");
                cancelButton?.click(); // Activar el botón de cancelar
            } else {
                speak("No entendí tu respuesta. Por favor, di confirmar, carrito o cancelar.");
                isListening = false; // Permitir otra interacción
                recognition.start(); // Reactivar el reconocimiento de voz
            }
        };

        recognition.onerror = (event) => {
            speak("Ocurrió un error al reconocer tu voz. Intenta nuevamente.");
            console.error(event.error);
            isListening = false; // Permitir otra interacción
            recognition.start(); // Reactivar el reconocimiento de voz
        };

        recognition.onend = () => {
            isListening = false; // Resetear después de finalizar
        };
    } else {
        speak("Tu navegador no soporta la API de reconocimiento de voz.");
    }

    // Función para hablar texto
    function speak(message) {
        const synth = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(message);
        utterance.lang = "es-ES";
        synth.speak(utterance);
    }
});
