document.addEventListener("DOMContentLoaded", () => {
    const confirmButton = document.getElementById("confirmButton");
    const cancelButton = document.getElementById("cancelButton");
    const carritoButton = document.getElementById("confirmButtonC");
    const cantidadInput = document.getElementById("cantidad");

    // Asegúrate de que el objeto 'producto' esté definido previamente
    if (!producto) {
        console.error("El objeto 'producto' no está definido.");
        return;
    }

    const { nombre, descripcion, precio } = producto;

    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    if (SpeechRecognition) {
        const recognition = new SpeechRecognition();
        recognition.lang = "es-ES";
        recognition.continuous = false;

        let isListening = false; // Controla si está escuchando actualmente

        document.addEventListener("keydown", (event) => {
            if (event.key.toLowerCase() === "f" && !isListening) {
                isListening = true; // Evita activaciones múltiples
                const message = `El nombre del producto es ${nombre}. Descripción: ${descripcion}. Precio: ${precio}. ¿Deseas comprar, agregar al carrito o cancelar?`;
                speak(message, () => recognition.start()); // Iniciar reconocimiento de voz después de hablar
            }
        });

        recognition.onresult = (event) => {
            const transcript = event.results[0][0].transcript.toLowerCase();
            if (transcript.includes("comprar")) {
                speak("¿Cuántos productos deseas comprar?", () => {
                    recognition.start();
                    recognition.onresult = handleCantidadInput(confirmButton);
                });

                recognition.stop();
                isListening = false;
            } else if (transcript.includes("carrito")) {
                speak("¿Cuántos productos deseas agregar al carrito?", () => {
                    recognition.start();
                    recognition.onresult = handleCantidadInput(carritoButton);
                });

                recognition.stop();
                isListening = false;
            } else if (transcript.includes("cancelar")) {
                speak("Compra cancelada.");
                cancelButton?.click();
            } else {
                speak("No entendí tu respuesta. Por favor, di comprar, agregar al carrito o cancelar.");
            }
            isListening = false;
        };

        recognition.onerror = (event) => {
            speak("Ocurrió un error al reconocer tu voz. Intenta nuevamente.");
            console.error(event.error);
            isListening = false;
        };

        recognition.onend = () => {
            isListening = false;
        };

        // Manejo de la cantidad ingresada por voz
        function handleCantidadInput(button) {
            return (event) => {
                const cantidad = event.results[0][0].transcript;
                if (!isNaN(cantidad) && cantidad > 0) {
                    cantidadInput.value = cantidad; // Asignar la cantidad al campo
                    button?.click(); // Hacer clic en el botón correspondiente
                    speak(`Se han procesado ${cantidad} productos.`);
                } else {
                    speak("No entendí la cantidad. Por favor, di un número válido.", () => {
                        recognition.start();
                        recognition.onresult = handleCantidadInput(button); // Escuchar nuevamente
                    });
                }
            };
        }
    } else {
        speak("Tu navegador no soporta la API de reconocimiento de voz.");
    }

    // Función para hablar texto con callback opcional
    function speak(message, callback) {
        const synth = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(message);
        utterance.lang = "es-ES";
        utterance.onend = callback || (() => {});
        synth.speak(utterance);
    }
});
