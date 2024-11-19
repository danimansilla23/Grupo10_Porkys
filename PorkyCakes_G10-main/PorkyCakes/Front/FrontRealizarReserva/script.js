document.addEventListener("DOMContentLoaded", function () {
    const fechaEntrega = document.getElementById("fechaEntrega");
    const horarioEntrega = document.getElementById("horarioEntrega");

    // Configurar fecha mínima
    const hoy = new Date();
    hoy.setHours(hoy.getHours() + 72);
    const minFecha = hoy.toISOString().split("T")[0];
    fechaEntrega.setAttribute("min", minFecha);

    let formaDePago = 0; // Inicializamos la forma de pago como 0 (sin seleccionar)

    // Manejar la selección de forma de pago
    document.querySelectorAll('.btn-outline-pink').forEach(boton => {
        boton.addEventListener('click', function () {
            // Remover la clase 'active' de todos los botones
            document.querySelectorAll('.btn-outline-pink').forEach(btn => btn.classList.remove('active'));

            // Agregar la clase 'active' al botón seleccionado
            boton.classList.add('active');

            // Obtener el valor de forma de pago desde el atributo data-forma-pago
            formaDePago = boton.getAttribute('data-forma-pago');
        });
    });

    // Botón de "Reservar"
    document.querySelector(".btn-pink").addEventListener("click", function () {
        const fecha = fechaEntrega.value;
        const horario = horarioEntrega.value;

        if (!fecha || !horario) {
            mostrarModal("Por favor, completa todos los campos antes de continuar.");
            return;
        }

        if (!formaDePago) {
            mostrarModal("Por favor, selecciona una forma de pago.");
            return;
        }

        // Generar la URL con parámetros separados para fecha y hora
        const url = `http://localhost:4567/reserva/1/?fecha_entrega=${fecha}&hora_entrega=${horario}&forma_de_pago=${formaDePago}`;
        console.log("URL generada:", url); // Depurar URL generada

        // Enviar datos a la API
        fetch(url, { method: "POST" })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error en la reserva: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log("Respuesta de la API:", data); // Depurar respuesta del servidor

                mostrarModal("Reserva realizada con éxito.");

                // Esperar unos segundos para que el usuario vea el modal, luego redirigir
                setTimeout(() => {
                    window.location.href = "../FrontAgregarAlCarrito/";
                }, 2000); // Redirigir después de 2 segundos (puedes ajustar este tiempo)

            })
            .catch(error => {
                console.error("Error al realizar la reserva:", error);
                mostrarModal("Hubo un error al realizar la reserva. Por favor, verifica los datos o intenta más tarde.");
            });
    });

    // Función para mostrar el modal
    function mostrarModal(mensaje) {
        const modalMensaje = document.getElementById("modalMensaje");
        modalMensaje.textContent = mensaje;

        const modalReserva = new bootstrap.Modal(document.getElementById("modalReserva"));
        modalReserva.show();
    }
});




