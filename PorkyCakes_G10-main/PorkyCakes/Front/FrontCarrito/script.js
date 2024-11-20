// Selecciona elementos del DOM
const productsSection = document.querySelector('.products-section');
const totalAmount = document.getElementById('total-amount');
const cartCount = document.getElementById('cart-count');
const btnReserva = document.getElementById('btn-reserva');

// Función para obtener el carrito desde la API
async function obtenerCarrito() {
  try {
    const response = await fetch('http://localhost:4567/getcarrito/1/');
    if (!response.ok) throw new Error('Error al obtener los datos del carrito');
    const carritoData = await response.json();

    // Si el carrito está vacío
    if (carritoData.productos.length === 0) {
      productsSection.innerHTML = '<p class="text-center">Tu carrito está vacío.</p>';
      totalAmount.textContent = '$0.00';
      cartCount.textContent = '0';
      cartCount.style.display = 'none';
    } else {
      renderizarCarrito(carritoData);
    }

    // Controla si el botón "Realizar Reserva" debe estar habilitado
    btnReserva.disabled = cartCount.textContent === '0';
  } catch (error) {
    console.error('Error al obtener el carrito:', error);
  }
}

// Función para renderizar los productos del carrito
function renderizarCarrito(carritoData) {
  productsSection.innerHTML = ''; // Limpia los productos previos
  carritoData.productos.forEach(producto => {
    const productoHTML = document.createElement('div');
    productoHTML.classList.add('cart-item', 'card', 'p-3', 'mb-3', 'rounded');
    productoHTML.innerHTML = `
      <div class="d-flex align-items-center justify-content-between">
        <div>
          <h5 class="product-name">${producto.nombreProducto}</h5>
          <p class="product-size">${producto.tamaño} (X${producto.cantidad})</p>
        </div>
        <div class="d-flex align-items-center">
          <span class="product-price">$${producto.subtotal.toFixed(2)}</span>
          <button class="btn btn-outline-danger ms-3" data-producto-id="${producto.idProducto}">X</button>
        </div>
      </div>
    `;

    productsSection.appendChild(productoHTML);

    // Evento para eliminar el producto
    productoHTML.querySelector('button').addEventListener('click', () => {
      eliminarProducto(producto.idProducto, carritoData);
    });
  });

  // Actualiza el total y el contador de productos
  totalAmount.textContent = `$${carritoData.total.toFixed(2)}`;
  cartCount.textContent = carritoData.productos.length;
  cartCount.style.display = carritoData.productos.length > 0 ? 'block' : 'none';
}

// Función para eliminar un producto del carrito
function eliminarProducto(idProducto, carritoData) {
  carritoData.productos = carritoData.productos.filter(producto => producto.idProducto !== idProducto);
  carritoData.total = carritoData.productos.reduce((acc, producto) => acc + producto.subtotal, 0);
  renderizarCarrito(carritoData);
  btnReserva.disabled = cartCount.textContent === '0'; // Actualiza el estado del botón
}

// Evento para redirigir al hacer clic en el botón
btnReserva.addEventListener('click', () => {
  if (!btnReserva.disabled) {
    window.location.href = '../FrontRealizarReserva/index.html';
  }
});

// Carga inicial
obtenerCarrito();


