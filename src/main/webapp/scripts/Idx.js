let lecturaEnProgreso = false;

function leerContenido() {
    const texto = document.querySelector('main p').textContent;
    const utterance = new SpeechSynthesisUtterance(texto);
    utterance.lang = 'es-ES';
    lecturaEnProgreso = true;

    utterance.onend = function () {
        lecturaEnProgreso = false;
        preguntarSiEsNuevo();
    };

    speechSynthesis.speak(utterance);
}

function preguntarSiEsNuevo() {
    const pregunta = new SpeechSynthesisUtterance("¿Eres nuevo por aquí?");
    pregunta.lang = 'es-ES';

    pregunta.onend = function () {
        activarMicrofono();
    };

    speechSynthesis.speak(pregunta);
}

function activarBoton(id) {
    const boton = document.getElementById(id);
    if (boton) {
        boton.focus();
        boton.click();
    } else {
        console.error(`Botón con ID "${id}" no encontrado.`);
    }
}

function manejarTecla(evento) {
    // Verifica si la tecla presionada es "F"
    if (!lecturaEnProgreso && evento.key === 'f') {
        leerContenido();
        document.removeEventListener('keydown', manejarTecla);
    } else if (lecturaEnProgreso && evento.key === 'Escape') {
        speechSynthesis.cancel();
        lecturaEnProgreso = false;
    }
}

window.onload = function () {
    // Solo activa la función cuando se presiona la tecla "F"
    document.addEventListener('keydown', manejarTecla);
};

function activarMicrofono() {
    if ('webkitSpeechRecognition' in window) {
        const recognition = new webkitSpeechRecognition();
        recognition.lang = 'es-ES';
        recognition.continuous = false;
        recognition.interimResults = false;

        recognition.onresult = function (event) {
            const respuesta = event.results[0][0].transcript.toLowerCase();
            if (respuesta.includes('sí')) {
                activarBoton('LOG');
            } else if (respuesta.includes('no')) {
                activarBoton('INS');
            } else {
                const noEntendi = new SpeechSynthesisUtterance("No entendí, repítalo por favor.");
                noEntendi.lang = 'es-ES';
                noEntendi.onend = function () {
                    activarMicrofono();
                };
                speechSynthesis.speak(noEntendi);
            }
        };

        recognition.onerror = function (event) {
            console.error("Error de reconocimiento de voz:", event.error);
            const errorMensaje = new SpeechSynthesisUtterance("Hubo un error con el reconocimiento de voz. Intenta nuevamente.");
            errorMensaje.lang = 'es-ES';
            errorMensaje.onend = function () {
                activarMicrofono();
            };
            speechSynthesis.speak(errorMensaje);
        };

        recognition.start();
    } else {
        alert('El reconocimiento de voz no está soportado en este navegador.');
    }
}
