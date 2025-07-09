// ================================================================
// ARCHIVO: app.js
// DESCRIPCIÓN: Archivo principal de la aplicación frontend
// Maneja el estado global, carrito de compras y funcionalidades principales
// ================================================================

// ================================================================
// VARIABLES GLOBALES DEL ESTADO DE LA APLICACIÓN
// ================================================================

// Array que almacena los artículos del carrito de compras
// Intentamos cargar datos guardados del localStorage, si no existe usamos array vacío
let carritoItems = JSON.parse(localStorage.getItem('carrito')) || [];

// Variable para manejar la autenticación de usuarios
let authManager = null;

// ================================================================
// INICIALIZACIÓN DE LA APLICACIÓN
// ================================================================

// Event listener que se ejecuta cuando el DOM está completamente cargado
document.addEventListener('DOMContentLoaded', function() {
    // Inicializamos el gestor de autenticación
    authManager = new AuthManager();
    
    // Llamamos a la función principal de inicialización
    inicializarApp();
});

// Función principal que inicializa toda la aplicación
function inicializarApp() {
    // Actualizamos el contador visual del carrito
    actualizarContadorCarrito();
    
    // Cargamos las estadísticas de la página principal
    cargarEstadisticas();
    
    // Configuramos todos los event listeners de la página
    configurarEventListeners();
}

// ================================================================
// CONFIGURACIÓN DE EVENT LISTENERS
// ================================================================

// Función que configura todos los event listeners de la aplicación
function configurarEventListeners() {
    // Configuramos el formulario de búsqueda de productos
    const searchForm = document.getElementById('searchForm');
    if (searchForm) {
        // Cuando se envía el formulario, ejecutamos la función de búsqueda
        searchForm.addEventListener('submit', buscarProductos);
    }
    
    // Configuramos el filtro de precios
    const priceFilter = document.getElementById('priceFilter');
    if (priceFilter) {
        // Cuando cambia el filtro, ejecutamos la función de filtrado
        priceFilter.addEventListener('change', filtrarProductos);
    }
    
    // Configuramos el botón de limpiar carrito
    const clearCartBtn = document.getElementById('clearCart');
    if (clearCartBtn) {
        // Cuando se hace clic, limpiamos el carrito
        clearCartBtn.addEventListener('click', limpiarCarrito);
    }
    
    // Configuramos el botón de finalizar compra
    const checkoutBtn = document.getElementById('checkoutBtn');
    if (checkoutBtn) {
        // Cuando se hace clic, procesamos la compra
        checkoutBtn.addEventListener('click', finalizarCompra);
    }
}

// ================================================================
// FUNCIONES DEL CARRITO DE COMPRAS
// ================================================================

// Función para agregar un artículo al carrito
function agregarAlCarrito(articulo, cantidad = 1) {
    // Buscamos si el artículo ya existe en el carrito
    const existingItem = carritoItems.find(item => item.id === articulo.id);
    
    if (existingItem) {
        // Si ya existe, solo aumentamos la cantidad
        existingItem.cantidad += cantidad;
    } else {
        // Si no existe, lo agregamos como nuevo item
        carritoItems.push({
            ...articulo,        // Copiamos todas las propiedades del artículo
            cantidad: cantidad  // Agregamos la cantidad solicitada
        });
    }
    
    // Guardamos el carrito en localStorage
    guardarCarrito();
    
    // Actualizamos el contador visual
    actualizarContadorCarrito();
    
    // Mostramos mensaje de confirmación al usuario
    showAlert(`${articulo.nombre} agregado al carrito`, 'success');
}

// Función para eliminar un artículo completamente del carrito
function eliminarDelCarrito(articuloId) {
    // Filtramos el array para quitar el artículo con el ID especificado
    carritoItems = carritoItems.filter(item => item.id !== articuloId);
    
    // Guardamos los cambios
    guardarCarrito();
    
    // Actualizamos el contador
    actualizarContadorCarrito();
    
    // Si estamos en la página del carrito, actualizamos la vista
    if (window.location.pathname.includes('carrito.html')) {
        renderizarCarrito();
    }
}

// Función para actualizar la cantidad de un artículo en el carrito
function actualizarCantidadCarrito(articuloId, nuevaCantidad) {
    // Buscamos el artículo en el carrito
    const item = carritoItems.find(item => item.id === articuloId);
    
    if (item) {
        if (nuevaCantidad > 0) {
            // Si la nueva cantidad es positiva, la actualizamos
            item.cantidad = nuevaCantidad;
        } else {
            // Si es 0 o negativa, eliminamos el artículo del carrito
            eliminarDelCarrito(articuloId);
            return;
        }
    }
    
    // Guardamos los cambios
    guardarCarrito();
    actualizarContadorCarrito();
    
    // Si estamos en la página del carrito, actualizamos la vista
    if (window.location.pathname.includes('carrito.html')) {
        renderizarCarrito();
    }
}

// Función para vaciar completamente el carrito
function limpiarCarrito() {
    // Verificamos si el carrito ya está vacío
    if (carritoItems.length === 0) {
        showAlert('El carrito ya está vacío', 'info');
        return;
    }
    
    // Pedimos confirmación al usuario antes de limpiar
    if (confirm('¿Estás seguro de que quieres limpiar el carrito?')) {
        // Vaciamos el array del carrito
        carritoItems = [];
        
        // Guardamos los cambios
        guardarCarrito();
        actualizarContadorCarrito();
        
        // Si estamos en la página del carrito, actualizamos la vista
        if (window.location.pathname.includes('carrito.html')) {
            renderizarCarrito();
        }
        
        // Mostramos mensaje de confirmación
        showAlert('Carrito limpiado', 'success');
    }
}

// Función para guardar el carrito en localStorage del navegador
function guardarCarrito() {
    // Convertimos el array a JSON y lo guardamos en localStorage
    localStorage.setItem('carrito', JSON.stringify(carritoItems));
}

// Función para actualizar el contador visual del carrito
function actualizarContadorCarrito() {
    // Buscamos el elemento del contador en el HTML
    const counter = document.getElementById('cart-count');
    if (counter) {
        // Actualizamos el texto con la cantidad de items únicos
        counter.textContent = carritoItems.length;
    }
}

// Función para calcular el total del carrito
function calcularTotalCarrito() {
    // Usamos reduce para sumar precio × cantidad de cada item
    return carritoItems.reduce((total, item) => total + (item.precio * item.cantidad), 0);
}

// ================================================================
// PROCESO DE FINALIZACIÓN DE COMPRA
// ================================================================

// Función asíncrona para procesar la compra
async function finalizarCompra() {
    // Verificamos que el usuario esté autenticado
    if (!authManager || !authManager.isAuthenticated()) {
        showAlert('Debes iniciar sesión para realizar una compra', 'warning');
        return;
    }
    
    // Verificamos que el carrito no esté vacío
    if (carritoItems.length === 0) {
        showAlert('Tu carrito está vacío', 'warning');
        return;
    }
    
    try {
        // Mostramos spinner de carga
        showLoading();
        
        // Obtenemos los datos del usuario actual
        const usuario = authManager.getCurrentUser();
        
        // Creamos el objeto pedido para enviar al backend
        const pedido = {
            emailCliente: usuario.email,
            observaciones: 'Pedido realizado desde el frontend'
        };
        
        // Enviamos el pedido al backend
        const nuevoPedido = await pedidosAPI.crear(pedido);
        
        // Agregamos cada artículo del carrito al pedido
        for (const item of carritoItems) {
            await pedidosAPI.agregarArticulo(nuevoPedido.id, item.id, item.cantidad);
        }
        
        // Actualizamos el total del pedido en el backend
        await pedidosAPI.actualizarTotal(nuevoPedido.id);
        
        // Limpiamos el carrito local
        limpiarCarrito();
        
        // Mostramos mensaje de éxito
        showAlert('¡Compra realizada con éxito! Pedido #' + nuevoPedido.id, 'success');
        
        // Redirigimos a la página principal después de 2 segundos
        setTimeout(() => {
            window.location.href = 'index.html';
        }, 2000);
        
    } catch (error) {
        // Manejamos errores y los mostramos al usuario
        console.error('Error al procesar la compra:', error);
        showAlert('Error al procesar la compra: ' + error.message, 'danger');
    } finally {
        // Siempre ocultamos el spinner al final
        hideLoading();
    }
}

// ================================================================
// FUNCIONES DE CARGA DE DATOS Y ESTADÍSTICAS
// ================================================================

// Función para cargar y mostrar estadísticas en la página principal
async function cargarEstadisticas() {
    try {
        // Intentamos obtener estadísticas del backend
        const estadisticas = await articulosAPI.obtenerTodos();
        
        // Buscamos los elementos donde mostrar las estadísticas
        const productsCount = document.getElementById('products-count');
        const clientsCount = document.getElementById('clients-count');
        const ordersCount = document.getElementById('orders-count');
        
        // Si encontramos el elemento de productos, animamos el contador
        if (productsCount && estadisticas) {
            animateCountUp(productsCount, estadisticas.length || 0);
        }
        
        // Para clientes y pedidos, usamos valores por defecto si no tenemos API
        if (clientsCount) {
            animateCountUp(clientsCount, 25); // Valor ejemplo
        }
        
        if (ordersCount) {
            animateCountUp(ordersCount, 150); // Valor ejemplo
        }
        
    } catch (error) {
        // Si hay error, usamos valores por defecto
        console.error('Error cargando estadísticas:', error);
    }
}

// Función para animar el conteo de números (efecto visual)
function animateCountUp(element, target) {
    // Iniciamos desde 0
    let current = 0;
    
    // Calculamos el incremento para llegar al objetivo en ~50 pasos
    const increment = target / 50;
    
    // Función que se ejecuta repetidamente para animar
    const timer = setInterval(() => {
        // Incrementamos el valor actual
        current += increment;
        
        // Si llegamos o pasamos el objetivo
        if (current >= target) {
            // Establecemos el valor final exacto
            element.textContent = Math.floor(target);
            // Detenemos la animación
            clearInterval(timer);
        } else {
            // Actualizamos el elemento con el valor actual
            element.textContent = Math.floor(current);
        }
    }, 30); // Ejecutamos cada 30ms para suavidad
}

// ================================================================
// FUNCIONES DE BÚSQUEDA Y FILTRADO DE PRODUCTOS
// ================================================================

// Función para manejar la búsqueda de productos
async function buscarProductos(event) {
    // Prevenimos el comportamiento por defecto del formulario
    event.preventDefault();
    
    // Obtenemos el término de búsqueda del input
    const searchTerm = document.getElementById('searchInput').value.trim();
    
    // Si no hay término de búsqueda, cargamos todos los productos
    if (!searchTerm) {
        cargarProductos();
        return;
    }
    
    try {
        // Mostramos spinner de carga
        showLoading();
        
        // Buscamos productos que contengan el término
        const productos = await articulosAPI.buscarPorNombre(searchTerm);
        
        // Mostramos los resultados
        mostrarProductos(productos);
        
    } catch (error) {
        // Manejamos errores de búsqueda
        console.error('Error en búsqueda:', error);
        showAlert('Error al buscar productos', 'danger');
    } finally {
        // Ocultamos el spinner
        hideLoading();
    }
}

// Función para filtrar productos por precio
async function filtrarProductos() {
    // Obtenemos el valor del filtro de precio
    const priceRange = document.getElementById('priceFilter').value;
    
    try {
        showLoading();
        
        let productos;
        
        // Aplicamos el filtro según la selección
        switch (priceRange) {
            case 'low':
                // Productos de bajo precio (0-1000)
                productos = await articulosAPI.obtenerPorRangoPrecios(0, 1000);
                break;
            case 'medium':
                // Productos de precio medio (1000-5000)
                productos = await articulosAPI.obtenerPorRangoPrecios(1000, 5000);
                break;
            case 'high':
                // Productos de precio alto (5000+)
                productos = await articulosAPI.obtenerPorRangoPrecios(5000, 999999);
                break;
            default:
                // Sin filtro, mostrar todos
                productos = await articulosAPI.obtenerActivos();
        }
        
        // Mostramos los productos filtrados
        mostrarProductos(productos);
        
    } catch (error) {
        console.error('Error al filtrar productos:', error);
        showAlert('Error al filtrar productos', 'danger');
    } finally {
        hideLoading();
    }
}

// ================================================================
// FUNCIONES DE RENDERIZADO Y VISUALIZACIÓN
// ================================================================

// Función para mostrar una lista de productos en el HTML
function mostrarProductos(productos) {
    // Buscamos el contenedor donde mostrar los productos
    const container = document.getElementById('products-container');
    if (!container) return;
    
    // Si no hay productos, mostramos mensaje
    if (!productos || productos.length === 0) {
        container.innerHTML = '<p class="text-center">No se encontraron productos</p>';
        return;
    }
    
    // Generamos el HTML para cada producto
    const productosHTML = productos.map(producto => `
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">${producto.nombre}</h5>
                    <p class="card-text">${producto.descripcion || 'Sin descripción'}</p>
                    <p class="card-text"><strong>Precio: ${formatearMoneda(producto.precio)}</strong></p>
                    <p class="card-text">Stock: ${producto.stock}</p>
                </div>
                <div class="card-footer">
                    <button class="btn btn-primary w-100" 
                            onclick="agregarAlCarrito(${JSON.stringify(producto).replace(/"/g, '&quot;')}, 1)"
                            ${producto.stock <= 0 ? 'disabled' : ''}>
                        ${producto.stock <= 0 ? 'Sin Stock' : 'Agregar al Carrito'}
                    </button>
                </div>
            </div>
        </div>
    `).join('');
    
    // Insertamos el HTML generado en el contenedor
    container.innerHTML = productosHTML;
}

// Función para cargar todos los productos activos
async function cargarProductos() {
    try {
        showLoading();
        
        // Obtenemos todos los productos activos del backend
        const productos = await articulosAPI.obtenerActivos();
        
        // Los mostramos en la página
        mostrarProductos(productos);
        
    } catch (error) {
        console.error('Error cargando productos:', error);
        showAlert('Error al cargar productos', 'danger');
    } finally {
        hideLoading();
    }
}

// Función para renderizar el carrito en la página carrito.html
function renderizarCarrito() {
    // Buscamos el contenedor del carrito
    const container = document.getElementById('cart-items');
    if (!container) return;
    
    // Si el carrito está vacío
    if (carritoItems.length === 0) {
        container.innerHTML = '<p class="text-center">Tu carrito está vacío</p>';
        document.getElementById('cart-total').textContent = formatearMoneda(0);
        return;
    }
    
    // Generamos HTML para cada item del carrito
    const itemsHTML = carritoItems.map(item => `
        <div class="row mb-3 p-3 border rounded">
            <div class="col-md-6">
                <h5>${item.nombre}</h5>
                <p class="text-muted">${item.descripcion || ''}</p>
            </div>
            <div class="col-md-2">
                <p><strong>${formatearMoneda(item.precio)}</strong></p>
            </div>
            <div class="col-md-2">
                <input type="number" class="form-control" value="${item.cantidad}" min="1"
                       onchange="actualizarCantidadCarrito(${item.id}, this.value)">
            </div>
            <div class="col-md-2">
                <p><strong>${formatearMoneda(item.precio * item.cantidad)}</strong></p>
                <button class="btn btn-danger btn-sm" onclick="eliminarDelCarrito(${item.id})">
                    Eliminar
                </button>
            </div>
        </div>
    `).join('');
    
    // Insertamos el HTML y actualizamos el total
    container.innerHTML = itemsHTML;
    document.getElementById('cart-total').textContent = formatearMoneda(calcularTotalCarrito());
}

// ================================================================
// FUNCIONES UTILITARIAS GLOBALES
// ================================================================

// Función para formatear moneda (reutiliza la función de api.js si está disponible)
function formatearMoneda(cantidad) {
    if (typeof formatearMoneda !== 'undefined') {
        return formatearMoneda(cantidad);
    }
    // Fallback si no está disponible la función de api.js
    return new Intl.NumberFormat('es-AR', {
        style: 'currency',
        currency: 'ARS'
    }).format(cantidad);
}

// Función para mostrar alertas (reutiliza la función de api.js si está disponible)
function showAlert(message, type = 'info') {
    if (typeof showAlert !== 'undefined') {
        return showAlert(message, type);
    }
    // Fallback simple si no está disponible la función de api.js
    alert(message);
}

// Función para mostrar loading (reutiliza la función de api.js si está disponible)
function showLoading() {
    if (typeof showLoading !== 'undefined') {
        return showLoading();
    }
}

// Función para ocultar loading (reutiliza la función de api.js si está disponible)
function hideLoading() {
    if (typeof hideLoading !== 'undefined') {
        return hideLoading();
    }
}

