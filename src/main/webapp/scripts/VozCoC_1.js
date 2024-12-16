document.addEventListener('keydown', (event) => {
    if (event.key.toLowerCase() === 'f') {
        const productos = document.querySelectorAll('.product-item');
        let currentProductIndex = 0;  // Controla el índice del producto actual
        const totalProducts = productos.length;

        // Función para hablar un mensaje
        function speakMessage(message, callback) {
            const utterance = new SpeechSynthesisUtterance(message);
            utterance.lang = 'es-ES';
            utterance.onend = callback;
            speechSynthesis.speak(utterance);
        }

        // Reemplazar palabras clave en la transcripción
        function replaceSpecialWords(transcript) {
            return transcript.toLowerCase().replace(/\s+/g, '');
        }

        // Activar micrófono y escuchar la respuesta
        function activateMicrophone(callback) {
            if ('webkitSpeechRecognition' in window) {
                const recognition = new webkitSpeechRecognition();
                recognition.lang = 'es-ES';
                recognition.continuous = false;
                recognition.interimResults = false;

                recognition.onresult = (event) => {
                    let transcript = event.results[0][0].transcript.trim();
                    transcript = replaceSpecialWords(transcript);
                    callback(transcript);
                };

                recognition.onerror = () => {
                    speakMessage("No entendí, repítelo por favor", () => activateMicrophone(callback));
                };

                recognition.start();
            } else {
                console.error("El reconocimiento de voz no está disponible en este navegador.");
            }
        }

        // Función para leer la información del producto
        function readProductInfo() {
            const product = productos[currentProductIndex];
            const nombre = product.querySelector('h3').textContent;
            const descripcion = product.querySelector('p').textContent;
            const calificacion = product.querySelector('.cali').textContent;
            const precio = product.querySelector('.price').textContent;

            const message = `Mascota: ${nombre}. Descripción: ${descripcion}. Calificación: ${calificacion}. Precio: ${precio}. ¿Te gustaría comprarlo? Si quieres pasar al siguiente, di 'siguiente'.`;
            speakMessage(message, () => activateMicrophone(handleVoiceCommand));
        }

        // Función para manejar los comandos de voz
        function handleVoiceCommand(command) {
            switch (command) {
                case 'comprar':
                    const buyButton = productos[currentProductIndex].querySelector('button[type="submit"]');
                    buyButton.click();
                    break;
                case 'siguiente':
                    currentProductIndex = (currentProductIndex + 1) % totalProducts;
                    readProductInfo();
                    break;
                default:
                    speakMessage("No entendí la opción, por favor repítelo.");
                    activateMicrophone(handleVoiceCommand);
                    break;
            }
        }

        // Iniciar el proceso con el primer producto
        readProductInfo();
    }
});
