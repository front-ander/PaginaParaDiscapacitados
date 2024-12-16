 document.addEventListener('DOMContentLoaded', () => {
    const dniInput = document.getElementById('dni');
    const emailInput = document.getElementById('Correo');
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
            .replace(/\s+/g, '') // Elimina espacios
            .replace(/arroba/g, '@'); // Sustituye 'arroba' por '@'
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
        speakMessage("Ingrese su correo electrónico", () => {
            activateMicrophone(emailInput, (email) => {
                if (/\S+@\S+\.\S+/.test(email)) {
                    speakMessage("Diga 'capturar' para tomar su foto", () => {
                        activateMicrophone(null, (command) => {
                            if (command.toLowerCase() === "capturar") {
                                capturePhotoButton.click();
                                speakMessage("Bien, ahora nos registraremos", () => {
                                    submitButton.click();
                                });
                            } else {
                                speakMessage("No entendí, repítalo por favor");
                            }
                        });
                    });
                } else {
                    speakMessage("El correo electrónico no es válido, repítalo por favor.");
                    handleEmailCapture(); // Solo repite el correo
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

    document.addEventListener('keydown', (event) => {
        if (!recognitionActive && event.key === 'f') { // Cambiado para escuchar la tecla 'F'
            recognitionActive = true;
            handleRegistrationProcess();
        }
    });
});
