document.addEventListener("keydown", function(event) {
    if (event.key === "f" || event.key === "F") {
        // Obtener los datos que queremos leer en voz alta (esto depende de los datos de la tabla)
        let fecha = document.querySelector("td:nth-child(2)").textContent; // Fecha
        let compra = document.querySelector("td:nth-child(3)").textContent; // Compra
        let precio = document.querySelector("td:nth-child(5)").textContent; // Precio Unitario
        
        // Convertir la información a un mensaje que la voz leerá
        let mensaje = `La fecha de la compra es ${fecha}, la compra es ${compra}, el precio unitario es ${precio}. ¿Quieres ver la factura o regresar?`;

        // Utilizar SpeechSynthesis para leer la información
        let voz = new SpeechSynthesisUtterance(mensaje);
        voz.lang = "es-ES"; // Idioma español
        
        // Iniciar la lectura de la voz
        window.speechSynthesis.speak(voz);

        // Esperar a que la voz termine para comenzar a escuchar el comando
        voz.onend = function() {
            // Inicializar el reconocimiento de voz después de que la voz termine
            const reconocimiento = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
            reconocimiento.lang = "es-ES"; // Idioma español
            reconocimiento.continuous = false; // No sigue escuchando después de la respuesta

            // Iniciar el reconocimiento de voz
            reconocimiento.start();

            // Cuando se detecta una respuesta
            reconocimiento.onresult = function(event) {
                let comando = event.results[0][0].transcript.toLowerCase();
                console.log("Comando reconocido: " + comando);

                if (comando.includes("factura")) {
                    // Activar el botón de factura
                    let facturaButton = document.querySelector("form[action='generarPDF'] button");
                    if (facturaButton) {
                        facturaButton.click(); // Esto simula un clic en el botón
                    }
                } else if (comando.includes("regresar")) {
                    // Redirigir a la página HistorialCompra.jsp
                    window.location.href = "HistorialCompra.jsp";
                } else {
                    alert("Comando no reconocido. Por favor, di 'factura' o 'regresar'.");
                }
            };

            // En caso de error al reconocer la voz
            reconocimiento.onerror = function(event) {
                alert("Hubo un error al reconocer la voz. Inténtalo de nuevo.");
            };
        };
    }
});
