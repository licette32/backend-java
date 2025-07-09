// ================================================================
// ARCHIVO: api.js
// DESCRIPCIÓN: Archivo principal que maneja todas las conexiones con el backend
// Este archivo contiene todas las funciones para comunicarse con la API REST
// ================================================================

// Configuración base de la API - URL donde está corriendo nuestro backend
const API_BASE_URL = '/api';

// ================================================================
// FUNCIONES UTILITARIAS PARA MANEJO DE RESPUESTAS Y ERRORES
// ================================================================

// Función para manejar las respuestas HTTP de la API
const handleResponse = async (response) => {
    // Verificamos si la respuesta HTTP fue exitosa (códigos 200-299)
    if (!response.ok) {
        // Si hubo error, intentamos obtener el mensaje de error del servidor
        const errorData = await response.json().catch(() => ({}));
        // Lanzamos una excepción con el mensaje de error
        throw new Error(errorData.message || `Error: ${response.status}`);
    }
    // Si todo está bien, convertimos la respuesta a JSON y la devolvemos
    return response.json();
};

// Función para manejar errores de forma centralizada
const handleError = (error) => {
    // Registramos el error en la consola para debugging
    console.error('Error en la API:', error);
    // Mostramos una alerta visual al usuario
    showAlert('Error: ' + error.message, 'danger');
    // Re-lanzamos el error para que pueda ser manejado por quien llamó la función
    throw error;
};

// ================================================================
// FUNCIONES PARA MOSTRAR ALERTAS Y FEEDBACK AL USUARIO
// ================================================================

// Función para mostrar alertas (mensajes) al usuario
const showAlert = (message, type = 'info') => {
    // Buscamos un contenedor específico para alertas, si no existe usamos el body
    const alertContainer = document.getElementById('alert-container') || document.body;
    
    // Creamos un elemento div para la alerta
    const alert = document.createElement('div');
    // Agregamos las clases CSS de Bootstrap para estilos
    alert.className = `alert alert-${type} alert-dismissible fade show`;
    // Definimos el contenido HTML de la alerta con botón de cerrar
    alert.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    // Agregamos la alerta al contenedor
    alertContainer.appendChild(alert);
    
    // Removemos automáticamente la alerta después de 5 segundos
    setTimeout(() => {
        alert.remove();
    }, 5000);
};

// Función para mostrar un spinner de carga durante operaciones
const showLoading = () => {
    // Creamos un overlay (capa superpuesta) para el loading
    const loadingOverlay = document.createElement('div');
    loadingOverlay.id = 'loading-overlay';
    loadingOverlay.className = 'loading-overlay';
    // Agregamos un spinner de Bootstrap
    loadingOverlay.innerHTML = '<div class="spinner-border text-dark" role="status"></div>';
    // Agregamos el overlay al body
    document.body.appendChild(loadingOverlay);
};

// Función para ocultar el spinner de carga
const hideLoading = () => {
    // Buscamos el overlay de loading
    const loadingOverlay = document.getElementById('loading-overlay');
    // Si existe, lo removemos
    if (loadingOverlay) {
        loadingOverlay.remove();
    }
};

// ================================================================
// API DE CLIENTES - Todas las operaciones relacionadas con clientes
// ================================================================

const clientesAPI = {
    // Función para obtener todos los clientes del sistema
    obtenerTodos: async () => {
        try {
            // Hacemos una petición GET al endpoint de clientes
            const response = await fetch(`${API_BASE_URL}/clientes`);
            // Procesamos la respuesta y la devolvemos
            return await handleResponse(response);
        } catch (error) {
            // Si hay error, lo manejamos de forma centralizada
            handleError(error);
        }
    },

    // Función para obtener un cliente específico por su ID
    obtenerPorId: async (id) => {
        try {
            // Hacemos una petición GET al endpoint específico del cliente
            const response = await fetch(`${API_BASE_URL}/clientes/${id}`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para buscar un cliente por su email
    buscarPorEmail: async (email) => {
        try {
            // Hacemos una petición GET al endpoint de búsqueda por email
            const response = await fetch(`${API_BASE_URL}/clientes/email/${email}`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para buscar un cliente por su DNI
    buscarPorDni: async (dni) => {
        try {
            // Hacemos una petición GET al endpoint de búsqueda por DNI
            const response = await fetch(`${API_BASE_URL}/clientes/dni/${dni}`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para crear un nuevo cliente
    crear: async (cliente) => {
        try {
            // Hacemos una petición POST al endpoint de clientes
            const response = await fetch(`${API_BASE_URL}/clientes`, {
                method: 'POST', // Método HTTP para crear recursos
                headers: {
                    'Content-Type': 'application/json', // Indicamos que enviamos JSON
                },
                body: JSON.stringify(cliente) // Convertimos el objeto cliente a JSON
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para actualizar un cliente existente
    actualizar: async (id, cliente) => {
        try {
            // Hacemos una petición PUT al endpoint específico del cliente
            const response = await fetch(`${API_BASE_URL}/clientes/${id}`, {
                method: 'PUT', // Método HTTP para actualizar recursos
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cliente) // Enviamos los datos actualizados
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para eliminar un cliente
    eliminar: async (id) => {
        try {
            // Hacemos una petición DELETE al endpoint específico del cliente
            const response = await fetch(`${API_BASE_URL}/clientes/${id}`, {
                method: 'DELETE' // Método HTTP para eliminar recursos
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    }
};

// ================================================================
// API DE ARTÍCULOS - Todas las operaciones relacionadas con productos
// ================================================================

const articulosAPI = {
    // Función para obtener todos los artículos del sistema
    obtenerTodos: async () => {
        try {
            // Hacemos una petición GET al endpoint de artículos
            const response = await fetch(`${API_BASE_URL}/articulos`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para obtener solo los artículos activos (disponibles para venta)
    obtenerActivos: async () => {
        try {
            // Hacemos una petición GET al endpoint de artículos activos
            const response = await fetch(`${API_BASE_URL}/articulos/activos`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para obtener un artículo específico por su ID
    obtenerPorId: async (id) => {
        try {
            // Hacemos una petición GET al endpoint específico del artículo
            const response = await fetch(`${API_BASE_URL}/articulos/${id}`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para buscar artículos por nombre (búsqueda parcial)
    buscarPorNombre: async (nombre) => {
        try {
            // Hacemos una petición GET al endpoint de búsqueda por nombre
            const response = await fetch(`${API_BASE_URL}/articulos/buscar/${nombre}`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para obtener artículos dentro de un rango de precios
    obtenerPorRangoPrecios: async (precioMin, precioMax) => {
        try {
            // Hacemos una petición GET con parámetros de consulta para el rango de precios
            const response = await fetch(`${API_BASE_URL}/articulos/precio?min=${precioMin}&max=${precioMax}`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para crear un nuevo artículo
    crear: async (articulo) => {
        try {
            // Hacemos una petición POST al endpoint de artículos
            const response = await fetch(`${API_BASE_URL}/articulos`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(articulo)
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para actualizar un artículo existente
    actualizar: async (id, articulo) => {
        try {
            // Hacemos una petición PUT al endpoint específico del artículo
            const response = await fetch(`${API_BASE_URL}/articulos/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(articulo)
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para eliminar un artículo (eliminación lógica, se marca como inactivo)
    eliminar: async (id) => {
        try {
            // Hacemos una petición DELETE al endpoint específico del artículo
            const response = await fetch(`${API_BASE_URL}/articulos/${id}`, {
                method: 'DELETE'
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para actualizar el stock de un artículo
    actualizarStock: async (id, nuevoStock) => {
        try {
            // Hacemos una petición PUT al endpoint de actualización de stock
            const response = await fetch(`${API_BASE_URL}/articulos/${id}/stock?stock=${nuevoStock}`, {
                method: 'PUT'
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    }
};

// ================================================================
// API DE PEDIDOS - Todas las operaciones relacionadas con órdenes de compra
// ================================================================

const pedidosAPI = {
    // Función para obtener todos los pedidos del sistema
    obtenerTodos: async () => {
        try {
            // Hacemos una petición GET al endpoint de pedidos
            const response = await fetch(`${API_BASE_URL}/pedidos`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para obtener un pedido específico por su ID
    obtenerPorId: async (id) => {
        try {
            // Hacemos una petición GET al endpoint específico del pedido
            const response = await fetch(`${API_BASE_URL}/pedidos/${id}`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para obtener todos los pedidos de un cliente específico
    obtenerPorCliente: async (clienteId) => {
        try {
            // Hacemos una petición GET al endpoint de pedidos por cliente
            const response = await fetch(`${API_BASE_URL}/pedidos/cliente/${clienteId}`);
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para crear un nuevo pedido
    crear: async (pedido) => {
        try {
            // Hacemos una petición POST al endpoint de pedidos
            const response = await fetch(`${API_BASE_URL}/pedidos`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(pedido)
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para actualizar el estado de un pedido
    actualizarEstado: async (id, nuevoEstado) => {
        try {
            // Hacemos una petición PUT al endpoint de actualización de estado
            const response = await fetch(`${API_BASE_URL}/pedidos/${id}/estado?estado=${nuevoEstado}`, {
                method: 'PUT'
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    },

    // Función para eliminar un pedido
    eliminar: async (id) => {
        try {
            // Hacemos una petición DELETE al endpoint específico del pedido
            const response = await fetch(`${API_BASE_URL}/pedidos/${id}`, {
                method: 'DELETE'
            });
            return await handleResponse(response);
        } catch (error) {
            handleError(error);
        }
    }
};

// ================================================================
// FUNCIONES UTILITARIAS PARA FORMATEO Y PRESENTACIÓN DE DATOS
// ================================================================

// Función para formatear cantidades monetarias en formato de moneda
const formatearMoneda = (cantidad) => {
    // Usamos Intl.NumberFormat para formatear como moneda argentina
    return new Intl.NumberFormat('es-AR', {
        style: 'currency',
        currency: 'ARS'
    }).format(cantidad);
};

// Función para formatear fechas en formato legible para Argentina
const formatearFecha = (fecha) => {
    // Convertimos la fecha a un objeto Date si es string
    const fechaObj = new Date(fecha);
    // Usamos Intl.DateTimeFormat para formatear la fecha
    return new Intl.DateTimeFormat('es-AR', {
        year: 'numeric',   // Año completo (ej: 2024)
        month: '2-digit',  // Mes con 2 dígitos (ej: 03)
        day: '2-digit'     // Día con 2 dígitos (ej: 15)
    }).format(fechaObj);
};

// Función para validar email usando una expresión regular
const validarEmail = (email) => {
    // Expresión regular para validar formato de email
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
};

// Función para validar que un string no esté vacío
const validarNoVacio = (valor) => {
    // Verificamos que el valor existe y no sea solo espacios en blanco
    return valor && valor.trim().length > 0;
};

// ================================================================
// EXPORTAMOS TODAS LAS APIs Y FUNCIONES PARA USO EN OTROS ARCHIVOS
// ================================================================

// Si estamos en un entorno que soporta módulos ES6, exportamos todo
if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        clientesAPI,
        articulosAPI,
        pedidosAPI,
        showAlert,
        showLoading,
        hideLoading,
        formatearMoneda,
        formatearFecha,
        validarEmail,
        validarNoVacio
    };
} 

