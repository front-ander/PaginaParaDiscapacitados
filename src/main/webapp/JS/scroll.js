// Script para manejar el scroll
    let lastScrollTop = 0;
    const footer = document.querySelector('footer');

    // Mostrar el footer al cargar la página
    footer.style.transform = 'translateY(0)';

    window.addEventListener('scroll', function() {
        const scrollPosition = window.scrollY + window.innerHeight;
        const documentHeight = document.body.offsetHeight;

        // Si se ha llegado al final de la página o al principio
        if (scrollPosition >= documentHeight - footer.offsetHeight || window.scrollY === 0) {
            footer.style.transform = 'translateY(0)'; // Muestra el footer
        } else {
            footer.style.transform = 'translateY(100%)'; // Esconde el footer
        }

        lastScrollTop = window.scrollY; // Actualiza la posición de scroll anterior
    });

    // Opcional: manejar el evento de carga para asegurarse de que el footer esté visible al abrir
    window.addEventListener('load', function() {
        footer.style.transform = 'translateY(0)';
    });