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

        const grandeQuantity = parseInt(document.getElementById('quantity-grande').value);
        const medianaQuantity = parseInt(document.getElementById('quantity-mediana').value);
        const chicaQuantity = parseInt(document.getElementById('quantity-chica').value);

        const selectedSize = document.querySelector('input[name="size"]:checked').value;
        let idProducto; 
        let price; 

        if (selectedSize === 'grande') {
            idProducto = 16;
            price = 3500; // Precio por unidad
        } else if (selectedSize === 'mediana') {
            idProducto = 18;
            price = 1500; // Precio por unidad
        } else if (selectedSize === 'chica') {
            idProducto = 17;
            price = 1000; // Precio por unidad
        }

        let cantidad = 0; 
        if (selectedSize === 'grande') {
            cantidad = grandeQuantity; 
        } else if (selectedSize === 'mediana') {
            cantidad = medianaQuantity; 
        } else if (selectedSize === 'chica') {
            cantidad = chicaQuantity; 
        }

        if (cantidad > 0) {
            const totalPrice = cantidad * price;

            const observacionesElement = document.getElementById('observaciones');
            let observacion = observacionesElement.value.trim(); // Eliminar espacios en blanco

            let data = {
                idProducto: idProducto,
                cantidad: cantidad,
                precio: totalPrice
            };

            if (observacion !== null && observacion !== '') {
                data.observacion = observacion;
            }


            $.ajax({
                type: "POST",
                url: `http://localhost:4567/carrito/1/`, 
                data: data,
                success: function (response) {
                    console.log(response);

                    cartCount += cantidad; 
                    const cartCountElement = document.getElementById('cart-count');
                    cartCountElement.innerText = cartCount;
                    cartCountElement.style.display = 'inline'; 

                    document.getElementById('quantity-grande').value = 0;
                    document.getElementById('quantity-mediana').value = 0;
                    document.getElementById('quantity-chica').value = 0;
                },
                error: function (xhr, status, error) {
                    console.error("Error en la solicitud:", status, error);
                    alert("Error al agregar el producto al carrito.");
                }
            });
        } else {
            alert("Por favor, selecciona una cantidad mayor a 0 para el tamaño seleccionado.");
        }
    });
});