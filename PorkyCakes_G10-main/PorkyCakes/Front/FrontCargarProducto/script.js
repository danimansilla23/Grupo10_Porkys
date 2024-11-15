document.addEventListener('DOMContentLoaded', function () {
    fetch('http://localhost:4567/getPBase')
        .then(response => response.json())
        .then(data => {
            const selectElement = document.getElementById('id_pbase');

            if (Array.isArray(data)) {
                data.forEach(productoBase => {
                    const option = document.createElement('option');
                    option.value = productoBase.idProductosBase;
                    option.textContent = `${productoBase.Nombre_Base || "Nombre no disponible"} (${productoBase.Descripcion || "Descripción no disponible"})`;
                    selectElement.appendChild(option);
                });
            } else {
                console.error('La respuesta no es un array:', data);
            }
        })
        .catch(error => {
            console.error('Error al cargar productos base:', error);
        });
});

// Manejador del evento de envío del formulario
document.getElementById('productForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Evitar el envío del formulario por defecto

    // Obtener los valores generales del formulario
    const nombre_product = document.getElementById('nombre_product').value;
    const descripcion_product = document.getElementById('descripcion_product').value;
    const id_pbaseValue = document.getElementById('id_pbase').value;
    const imagen_url = document.getElementById('imagen_url').value;

    // Lista de tamaños y precios con sus respectivos checkboxes
    const tamañosPrecios = [
        { 
            tamaño: document.getElementById('tamaño_chico').value, 
            precio: document.getElementById('precio_chico').value, 
            check: document.getElementById('check_chico').checked 
        },
        { 
            tamaño: document.getElementById('tamaño_mediano').value, 
            precio: document.getElementById('precio_mediano').value, 
            check: document.getElementById('check_mediano').checked 
        },
        { 
            tamaño: document.getElementById('tamaño_grande').value, 
            precio: document.getElementById('precio_grande').value, 
            check: document.getElementById('check_grande').checked 
        }
    ];

    // Validar que al menos uno de los campos de tamaño o precio esté lleno y marcado para validación
    const hayDatosValidos = tamañosPrecios.some(item => (item.check && (item.tamaño || item.precio)));

    if (!hayDatosValidos) {
        document.getElementById('responseMessage').innerText = 'Debe ingresar al menos un tamaño o un precio en los campos seleccionados.';
        return; // Salir de la función si no hay datos válidos
    }

    // Recorrer los tamaños y precios y enviar cada uno
    tamañosPrecios.forEach(item => {
        const { tamaño, precio, check } = item;

        // Solo enviar si el checkbox está marcado
        if (check) {
            // Solo enviar si hay un tamaño o un precio
            if (precio || tamaño) {
                let url = `http://localhost:4567/addProduct?nombre_product=${encodeURIComponent(nombre_product)}&descripcion_product=${encodeURIComponent(descripcion_product)}&imagen_url=${encodeURIComponent(imagen_url)}`;

                // Añadir parámetros según lo que se haya ingresado
                if (precio) {
                    url += `&precio_vta=${precio}`;
                }
                if (tamaño) {
                    url += `&tamaño=${tamaño}`;
                }

                // Solo añadir el parámetro id_pbase si no es "Ninguno"
                if (id_pbaseValue !== "none") {
                    url += `&id_pbase=${id_pbaseValue}`;
                }

                // Realizar la solicitud AJAX
                fetch(url, {
                    method: 'POST',
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Error: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    document.getElementById('responseMessage').innerText += `Producto agregado con éxito.\n`;

                    // Mostrar un alert y recargar la página
                    alert('Producto agregado exitosamente');
                    location.reload(); // Recargar la página
                })
                .catch(error => {
                    document.getElementById('responseMessage').innerText += `Error al agregar producto: ${error.message}\n`;
                });
            }
        }
    });
});





























