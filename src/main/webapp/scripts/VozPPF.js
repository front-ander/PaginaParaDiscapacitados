document.addEventListener('keydown', (event) => {
    if (event.key.toLowerCase() === 'f') {
        // Definir enlaces
        const catalogoLink = document.querySelector('a[href="catalogo.jsp"]');
        const mascotasLink = document.querySelector('a[href="mascotas.jsp"]');
        const serviciosLink = document.querySelector('a[href="servicios.jsp"]');
        const contacLink = document.querySelector('a[href="contactanos.jsp"]');
        const perfilLink = document.querySelector('a[href="perfil.jsp"]');
        const carritoLink = document.querySelector('a[href="carrito.jsp"]');
        const historialLink = document.querySelector('a[href="HistorialCompra.jsp"]');

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

        // Función para iniciar el proceso de voz
        function askForOption() {
            speakMessage("A donde vamos, podemos ir a Catalogo, Mascotas, Servicios, Contactanos, Perfil, Carrito y a Historial", () => {
                activateMicrophone((command) => {
                    switch (command) {
                        case 'catálogo':
                            catalogoLink.click();
                            break;
                        case 'mascotas':
                            mascotasLink.click();
                            break;
                        case 'servicios':
                            serviciosLink.click();
                            break;
                        case 'contáctanos':
                            contacLink.click();
                            break;
                        case 'perfil':
                            perfilLink.click();
                            break;
                        case 'carrito':
                            carritoLink.click();
                            break;
                        case 'historial de compras':
                            historialLink.click();
                            break;
                        default:
                            speakMessage("No entendí la opción, por favor repítelo.");
                            askForOption();
                            break;
                    }
                });
            });
        }

        // Iniciar el proceso al presionar "F"
        askForOption();
    }
});
