document.addEventListener('keydown', (event) => {
    if (event.key.toLowerCase() === 'f') {
        const compras = document.querySelectorAll('.compras-item');
        let currentCompraIndex = 0;  // Controla el índice de la compra actual
        const totalCompras = compras.length;

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

        // Función para leer la información de la compra
        function readCompraInfo() {
            const compra = compras[currentCompraIndex];
            const id = compra.querySelector('td:nth-child(1)').textContent;
            const fecha = compra.querySelector('td:nth-child(2)').textContent;
            const total = compra.querySelector('td:nth-child(3)').textContent;

            const message = `Compra ID: ${id}. Fecha: ${fecha}. Total: ${total}. ¿Quieres saber los detalles? Si quieres pasar al siguiente, di 'siguiente'. Si deseas retroceder, di 'retroceder'.`;
            speakMessage(message, () => activateMicrophone(handleVoiceCommand));
        }

        // Función para manejar los comandos de voz
        function handleVoiceCommand(command) {
            switch (command) {
                case 'detalles':
                    const detallesButton = compras[currentCompraIndex].querySelector('form[action="Detalles.jsp"] button[type="submit"]');
                    detallesButton.click();
                    break;
                case 'retroceder':
                    window.location.href = 'PaginaPrincipal.jsp'; // Redirige a la página principal
                    break;
                case 'siguiente':
                    currentCompraIndex = (currentCompraIndex + 1) % totalCompras;
                    readCompraInfo();
                    break;
                case 'anterior':
                    currentCompraIndex = (currentCompraIndex - 1 + totalCompras) % totalCompras;
                    readCompraInfo();
                    break;
                default:
                    speakMessage("No entendí la opción, por favor repítelo.");
                    activateMicrophone(handleVoiceCommand);
                    break;
            }
        }

        // Iniciar el proceso con la primera compra
        readCompraInfo();
    }
});
