document.addEventListener('DOMContentLoaded', () => {
    const dniInput = document.getElementById('dni');
    const capturePhotoButton = document.getElementById('capture-photo');
    const submitButton = document.getElementById('submit-button');
    let recognitionActive = false;
    let dniCaptured = false;

    function speakMessage(message, callback) {
        const utterance = new SpeechSynthesisUtterance(message);
        utterance.lang = 'es-ES';
        utterance.onend = callback;
        speechSynthesis.speak(utterance);
    }

    function replaceSpecialWords(transcript) {
        // Reemplaza palabras clave con caracteres especiales
        return transcript
            .toLowerCase()
            .replace(/\s+/g, ''); // Elimina espacios
    }

    function activateMicrophone(field, callback) {
        if ('webkitSpeechRecognition' in window) {
            const recognition = new webkitSpeechRecognition();
            recognition.lang = 'es-ES';
            recognition.continuous = false;
            recognition.interimResults = false;

            recognition.onresult = (event) => {
                let transcript = event.results[0][0].transcript.trim();
                transcript = replaceSpecialWords(transcript); // Reemplazar palabras clave
                field && (field.value = transcript);
                callback && callback(transcript);
            };

            recognition.onerror = () => {
                speakMessage("No entendí, repítalo por favor", () => activateMicrophone(field, callback));
            };

            recognition.start();
        } else {
            console.error("El reconocimiento de voz no está disponible en este navegador.");
        }
    }

    function handleEmailCapture() {
        speakMessage("Diga 'capturar' para tomar su foto", () => {
            activateMicrophone(null, (command) => {
                if (command.toLowerCase() === "capturar") {
                    capturePhotoButton.click();
                    speakMessage("Bien, ahora ingresaremos", () => {
                        submitButton.click();
                    });
                } else {
                    speakMessage("No entendí, repítalo por favor");
                }
            });
        });
    }

    function handleRegistrationProcess() {
        if (!dniCaptured) {
            speakMessage("Ingrese su DNI de 8 dígitos", () => {
                activateMicrophone(dniInput, (dni) => {
                    if (/^\d{8}$/.test(dni)) {
                        dniCaptured = true;
                        handleEmailCapture();
                    } else {
                        speakMessage("El DNI no es válido, repítalo por favor.");
                        handleRegistrationProcess();
                    }
                });
            });
        } else {
            handleEmailCapture(); // Si ya se capturó el DNI, no lo pide de nuevo
        }
    }

    // Escuchar la tecla "F" para iniciar el proceso
    document.addEventListener('keydown', (event) => {
        if (event.key.toLowerCase() === 'f' && !recognitionActive) {
            recognitionActive = true;
            handleRegistrationProcess();
        }
    });
});
