$(document).ready(function () {
    let cartCount = 0;

    document.querySelectorAll('.plus').forEach(button => {
        button.addEventListener('click', function () {
            const input = this.parentNode.querySelector('input[type="text"]');
            let currentValue = parseInt(input.value);
            input.value = currentValue + 1; 
        });
    });

    document.querySelectorAll('.minus').forEach(button => {
        button.addEventListener('click', function () {
            const input = this.parentNode.querySelector('input[type="text"]');
            let currentValue = parseInt(input.value);
            if (currentValue > 0) {
                input.value = currentValue - 1; 
            }
        });
    });

    // Manejar el evento de clic en el botón "Agregar"
    document.querySelector('form').addEventListener('submit', function (event) {
        event.preventDefault(); // Evitar el envío del formulario
    
        // Obtener el tamaño y la cantidad seleccionada
        const selectedSize = document.getElementById('size-select').value;
        const quantity = parseInt(document.getElementById('quantity').value);
    
        let idProducto; 
        let price;
    
        // Asignar ID y precio basado en el tamaño seleccionado
        if (selectedSize === 'grande') {
            idProducto = 16;
            price = 3500;
        } else if (selectedSize === 'mediana') {
            idProducto = 18;
            price = 1500;
        } else if (selectedSize === 'chica') {
            idProducto = 17;
            price = 1000;
        }
    
        // Validar que la cantidad sea mayor a 0
        if (quantity > 0) {
            const totalPrice = quantity * price;
    
            // Obtener el valor de las observaciones
            const observacionesElement = document.getElementById('observaciones');
            let observacion = observacionesElement.value.trim();
    
            // Datos a enviar
            let data = {
                idProducto: idProducto,
                cantidad: quantity,
                precio: totalPrice
            };
    
            if (observacion) {
                data.observacion = observacion;
            }
    
            // Enviar datos mediante AJAX
            $.ajax({
                type: "POST",
                url: `http://localhost:4567/carrito/1/`, 
                data: data,
                success: function (response) {
                    console.log(response);
    
                    // Actualizar contador del carrito
                    cartCount += quantity; 
                    const cartCountElement = document.getElementById('cart-count');
                    cartCountElement.innerText = cartCount;
                    cartCountElement.style.display = 'inline'; 
    
                    // Reiniciar campos del formulario
                    document.getElementById('quantity').value = 0;
                    observacionesElement.value = '';
                },
                error: function (xhr, status, error) {
                    console.error("Error en la solicitud:", status, error);
                    alert("Error al agregar el producto al carrito.");
                }
            });
        } else {
            alert("Por favor, selecciona una cantidad mayor a 0.");
        }
    });
    
});