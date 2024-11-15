document.addEventListener("DOMContentLoaded", function () {
    // Obtener el elemento de fecha
    const fechaEntrega = document.getElementById("fechaEntrega");

    // Calcular la fecha mínima permitida (72 horas a partir de ahora)
    const hoy = new Date();
    hoy.setHours(hoy.getHours() + 72); // Sumar 72 horas

    // Formatear la fecha en formato YYYY-MM-DD
    const year = hoy.getFullYear();
    const month = (hoy.getMonth() + 1).toString().padStart(2, '0'); // Mes en formato de dos dígitos
    const day = hoy.getDate().toString().padStart(2, '0'); // Día en formato de dos dígitos
    const minFecha = `${year}-${month}-${day}`;

    // Establecer la fecha mínima en el campo de fecha
    fechaEntrega.setAttribute("min", minFecha);

    // Opcional: Validar que el usuario no seleccione una fecha anterior a la mínima permitida
    fechaEntrega.addEventListener("input", function () {
        if (fechaEntrega.value < minFecha) {
            fechaEntrega.value = minFecha; // Restablecer a la fecha mínima si se selecciona una fecha inválida
            alert("Por favor, selecciona una fecha con al menos 72 horas de anticipación.");
        }
    });
});
