// Selecciona la sección donde están los productos
const carritoSection = document.querySelector('.carrito-section');
const productsSection = document.querySelector('.products-section');
const totalAmount = document.getElementById('total-amount');
const cartCount = document.getElementById('cart-count'); // Para actualizar el contador de productos

// Función para obtener el carrito desde la API
async function obtenerCarrito() {
  try {
    // Realiza la solicitud a la API
    const response = await fetch('http://localhost:4567/getcarrito/1/');
    
    // Verifica si la respuesta fue exitosa
    if (!response.ok) {
      throw new Error('Error al obtener los datos del carrito');
    }

    // Convierte la respuesta en formato JSON
    const carritoData = await response.json();
    
    // Verifica si se obtuvieron productos
    if (carritoData.productos.length === 0) {
      productsSection.innerHTML = '<p class="text-center">Tu carrito está vacío.</p>';
      totalAmount.textContent = '$0.00';
      cartCount.textContent = '0';
      cartCount.style.display = 'none';
      return;
    }

    // Renderiza el carrito con los productos
    renderizarCarrito(carritoData);
  } catch (error) {
    console.error('Error al obtener el carrito:', error);
  }
}

// Función para renderizar el contenido del carrito
function renderizarCarrito(carritoData) {
  // Primero limpia los productos previos
  productsSection.innerHTML = '';

  // Renderiza los productos del carrito
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

    // Inserta el producto en la sección del carrito
    productsSection.appendChild(productoHTML);

    // Agregar evento para eliminar el producto
    productoHTML.querySelector('button').addEventListener('click', () => {
      eliminarProducto(producto.idProducto, carritoData);
    });
  });

  // Actualizar el total y el contador de productos
  totalAmount.textContent = `$${carritoData.total.toFixed(2)}`;
  cartCount.textContent = carritoData.productos.length;
  cartCount.style.display = carritoData.productos.length > 0 ? 'block' : 'none';
}

// Función para eliminar un producto
function eliminarProducto(idProducto, carritoData) {
  // Filtrar el producto a eliminar
  carritoData.productos = carritoData.productos.filter(producto => producto.idProducto !== idProducto);

  // Actualizar el total después de eliminar
  carritoData.total = carritoData.productos.reduce((acc, producto) => acc + producto.subtotal, 0);

  // Re-renderizar el carrito
  renderizarCarrito(carritoData);
}

// Llamar a la función para obtener el carrito al cargar la página
obtenerCarrito();
