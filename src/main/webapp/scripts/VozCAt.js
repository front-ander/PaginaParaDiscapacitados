// Escuchar la tecla "F" para iniciar el proceso
document.addEventListener('keydown', (event) => {
    if (event.key.toLowerCase() === 'f') {
        // Definir enlaces de productos
        const bastonesLink = document.querySelector('a[href="Catalogo/Bastones.jsp"]');
        const sillasLink = document.querySelector('a[href="Catalogo/Sillas.jsp"]');
        const lentesLink = document.querySelector('a[href="Catalogo/Lentes.jsp"]');
        const generalLink = document.querySelector('a[href="Catalogo/General.jsp"]');

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
        function askForProduct() {
            speakMessage("¿Qué estás buscando? Tenemos bastones, sillas, lentes y productos generales.", () => {
                activateMicrophone((command) => {
                    switch (command) {
                        case 'bastones':
                            bastonesLink.click();
                            break;
                        case 'sillas':
                            sillasLink.click();
                            break;
                        case 'lentes':
                            lentesLink.click();
                            break;
                        case 'general':
                            generalLink.click();
                            break;
                        default:
                            speakMessage("No entendí la opción, por favor repítelo.");
                            askForProduct();
                            break;
                    }
                });
            });
        }

        // Llamar a la función solo después de presionar "F"
        askForProduct();
    }
});
