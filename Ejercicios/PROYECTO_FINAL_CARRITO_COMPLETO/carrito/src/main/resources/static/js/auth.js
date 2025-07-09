// ================================================================
// ARCHIVO: auth.js
// DESCRIPCIÓN: Manejo de autenticación y sesiones de usuario
// Este archivo contiene la clase AuthManager que gestiona login, registro y sesiones
// ================================================================

// ================================================================
// CLASE PRINCIPAL PARA MANEJO DE AUTENTICACIÓN
// ================================================================

// Clase que maneja toda la autenticación y sesiones de usuario
class AuthManager {
    // Constructor que se ejecuta al crear una nueva instancia
    constructor() {
        // Variable para almacenar los datos del usuario actual
        this.currentUser = null;
        
        // Inicializamos el sistema de autenticación
        this.init();
    }

    // ================================================================
    // MÉTODOS DE INICIALIZACIÓN
    // ================================================================

    // Método que inicializa el sistema de autenticación
    init() {
        // Intentamos cargar usuario desde el almacenamiento local
        this.loadUserFromStorage();
        
        // Configuramos todos los event listeners
        this.setupEventListeners();
        
        // Actualizamos la interfaz según el estado de autenticación
        this.updateUI();
    }

    // Método para cargar datos del usuario desde localStorage
    loadUserFromStorage() {
        // Intentamos obtener datos del usuario guardados previamente
        const userData = localStorage.getItem('currentUser');
        
        // Si existen datos guardados
        if (userData) {
            // Los parseamos de JSON y los asignamos al usuario actual
            this.currentUser = JSON.parse(userData);
        }
    }

    // ================================================================
    // CONFIGURACIÓN DE EVENT LISTENERS
    // ================================================================

    // Método que configura todos los event listeners necesarios
    setupEventListeners() {
        // Configuramos el botón de login
        const loginBtn = document.getElementById('loginBtn');
        if (loginBtn) {
            // Cuando se hace clic, ejecutamos el manejador de login
            loginBtn.addEventListener('click', () => this.handleLogin());
        }
        
        // Configuramos el botón de registro
        const registerBtn = document.getElementById('registerBtn');
        if (registerBtn) {
            // Cuando se hace clic, ejecutamos el manejador de registro
            registerBtn.addEventListener('click', () => this.handleRegister());
        }
        
        // Configuramos el botón de logout
        const logoutBtn = document.getElementById('logout-btn');
        if (logoutBtn) {
            // Cuando se hace clic, ejecutamos el logout
            logoutBtn.addEventListener('click', () => this.logout());
        }

        // Configuramos el modal de login para enfocar automáticamente el email
        const loginModal = document.getElementById('loginModal');
        if (loginModal) {
            // Cuando el modal se muestra completamente
            loginModal.addEventListener('shown.bs.modal', () => {
                // Enfocamos el campo de email para mejor UX
                const emailInput = document.getElementById('loginEmail');
                if (emailInput) emailInput.focus();
            });
        }

        // Configuramos el modal de registro para enfocar automáticamente el nombre
        const registerModal = document.getElementById('registerModal');
        if (registerModal) {
            // Cuando el modal se muestra completamente
            registerModal.addEventListener('shown.bs.modal', () => {
                // Enfocamos el campo de nombre para mejor UX
                const nombreInput = document.getElementById('registerNombre');
                if (nombreInput) nombreInput.focus();
            });
        }

        // Configuramos el formulario de login para responder a la tecla Enter
        const loginForm = document.getElementById('loginForm');
        if (loginForm) {
            loginForm.addEventListener('keypress', (e) => {
                // Si se presiona Enter
                if (e.key === 'Enter') {
                    // Prevenimos el comportamiento por defecto
                    e.preventDefault();
                    // Ejecutamos el login
                    this.handleLogin();
                }
            });
        }

        // Configuramos el formulario de registro para responder a la tecla Enter
        const registerForm = document.getElementById('registerForm');
        if (registerForm) {
            registerForm.addEventListener('keypress', (e) => {
                // Si se presiona Enter
                if (e.key === 'Enter') {
                    // Prevenimos el comportamiento por defecto
                    e.preventDefault();
                    // Ejecutamos el registro
                    this.handleRegister();
                }
            });
        }
    }

    // ================================================================
    // MANEJADORES DE EVENTOS DE FORMULARIOS
    // ================================================================

    // Manejador del formulario de login
    async handleLogin() {
        // Obtenemos el campo de email
        const emailInput = document.getElementById('loginEmail');
        if (!emailInput) return;
        
        // Obtenemos y limpiamos el valor del email
        const email = emailInput.value.trim();
        
        // Validamos que se haya ingresado un email
        if (!email) {
            showAlert('Por favor ingresa tu email', 'warning');
            return;
        }
        
        try {
            // Intentamos hacer login
            await this.login(email);
        } catch (error) {
            // Los errores ya se manejan en la función login
            console.error('Error en handleLogin:', error);
        }
    }

    // Manejador del formulario de registro
    async handleRegister() {
        // Obtenemos todos los valores del formulario de registro
        const nombre = document.getElementById('registerNombre')?.value?.trim();
        const apellido = document.getElementById('registerApellido')?.value?.trim();
        const email = document.getElementById('registerEmail')?.value?.trim();
        const dni = document.getElementById('registerDni')?.value?.trim();
        const telefono = document.getElementById('registerTelefono')?.value?.trim();
        const direccion = document.getElementById('registerDireccion')?.value?.trim();
        
        // Validamos que los campos obligatorios estén completos
        if (!nombre || !apellido || !email || !dni) {
            showAlert('Por favor completa todos los campos obligatorios', 'warning');
            return;
        }
        
        // Creamos el objeto con los datos del usuario
        const userData = {
            nombre,
            apellido,
            email,
            dni,
            telefono,
            direccion
        };
        
        try {
            // Intentamos registrar al usuario
            await this.register(userData);
        } catch (error) {
            // Los errores ya se manejan en la función register
            console.error('Error en handleRegister:', error);
        }
    }

    // ================================================================
    // MÉTODOS DE VALIDACIÓN
    // ================================================================

    // Método para validar formato de email
    validateEmail(email) {
        // Expresión regular para validar emails
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }

    // Método para validar formato de DNI argentino
    validateDNI(dni) {
        // Expresión regular para DNI de 7 u 8 dígitos
        const re = /^\d{7,8}$/;
        return re.test(dni);
    }

    // Método para validar formato de teléfono
    validatePhone(phone) {
        // Expresión regular flexible para números de teléfono
        const re = /^[\d\s\-\+\(\)]{10,}$/;
        return re.test(phone);
    }

    // ================================================================
    // MÉTODOS DE AUTENTICACIÓN PRINCIPALES
    // ================================================================

    // Método para iniciar sesión con email
    async login(email) {
        try {
            // Validamos que el email sea válido
            if (!email || !this.validateEmail(email)) {
                throw new Error('Por favor ingresa un email válido');
            }

            // Mostramos spinner de carga
            showLoading();
            
            // Buscamos el cliente en el backend por email
            const cliente = await clientesAPI.buscarPorEmail(email);
            
            // Si no se encuentra el cliente
            if (!cliente) {
                throw new Error('No se encontró un cliente con ese email. Por favor regístrate primero.');
            }

            // Guardamos el usuario en memoria
            this.currentUser = cliente;
            
            // Guardamos el usuario en localStorage para persistencia
            localStorage.setItem('currentUser', JSON.stringify(cliente));
            
            // Actualizamos la interfaz de usuario
            this.updateUI();
            
            // Cerramos el modal de login
            const modal = bootstrap.Modal.getInstance(document.getElementById('loginModal'));
            if (modal) {
                modal.hide();
            }
            
            // Limpiamos el formulario de login
            const loginForm = document.getElementById('loginForm');
            if (loginForm) {
                loginForm.reset();
            }
            
            // Mostramos mensaje de bienvenida
            showAlert(`¡Bienvenido/a ${cliente.nombre}!`, 'success');
            
            // Devolvemos los datos del cliente
            return cliente;
            
        } catch (error) {
            // Registramos el error en consola
            console.error('Error en login:', error);
            
            // Mostramos el error al usuario
            showAlert(error.message, 'danger');
            
            // Re-lanzamos el error para manejo externo si es necesario
            throw error;
        } finally {
            // Siempre ocultamos el spinner al final
            hideLoading();
        }
    }

    // Método para registrar un nuevo usuario
    async register(userData) {
        try {
            // Validamos el email
            if (!this.validateEmail(userData.email)) {
                throw new Error('Por favor ingresa un email válido');
            }
            
            // Validamos el DNI
            if (!this.validateDNI(userData.dni)) {
                throw new Error('Por favor ingresa un DNI válido (7-8 dígitos)');
            }
            
            // Validamos el teléfono si se proporcionó
            if (userData.telefono && !this.validatePhone(userData.telefono)) {
                throw new Error('Por favor ingresa un teléfono válido');
            }

            // Mostramos spinner de carga
            showLoading();
            
            // Intentamos crear el cliente en el backend
            const nuevoCliente = await clientesAPI.crear(userData);
            
            // Si se creó exitosamente, hacemos login automático
            this.currentUser = nuevoCliente;
            localStorage.setItem('currentUser', JSON.stringify(nuevoCliente));
            
            // Actualizamos la interfaz
            this.updateUI();
            
            // Cerramos el modal de registro
            const modal = bootstrap.Modal.getInstance(document.getElementById('registerModal'));
            if (modal) {
                modal.hide();
            }
            
            // Limpiamos el formulario de registro
            const registerForm = document.getElementById('registerForm');
            if (registerForm) {
                registerForm.reset();
            }
            
            // Mostramos mensaje de éxito
            showAlert(`¡Bienvenido/a ${nuevoCliente.nombre}! Tu cuenta ha sido creada exitosamente.`, 'success');
            
            return nuevoCliente;
            
        } catch (error) {
            console.error('Error en registro:', error);
            showAlert(error.message, 'danger');
            throw error;
        } finally {
            hideLoading();
        }
    }

    // Método para cerrar sesión
    logout() {
        // Limpiamos el usuario actual de memoria
        this.currentUser = null;
        
        // Removemos los datos del localStorage
        localStorage.removeItem('currentUser');
        
        // Actualizamos la interfaz
        this.updateUI();
        
        // Mostramos mensaje de confirmación
        showAlert('Sesión cerrada exitosamente', 'success');
        
        // Opcional: redirigir a la página principal
        if (window.location.pathname !== '/index.html' && window.location.pathname !== '/') {
            window.location.href = 'index.html';
        }
    }

    // ================================================================
    // MÉTODOS DE ESTADO Y VERIFICACIÓN
    // ================================================================

    // Método para verificar si el usuario está autenticado
    isAuthenticated() {
        // Devolvemos true si hay un usuario actual
        return this.currentUser !== null;
    }

    // Método para obtener los datos del usuario actual
    getCurrentUser() {
        // Devolvemos una copia de los datos del usuario para evitar modificaciones
        return this.currentUser ? { ...this.currentUser } : null;
    }

    // Método para verificar si el usuario actual es administrador
    isAdmin() {
        // Por simplicidad, asumimos que solo ciertos emails son admin
        // En un sistema real, esto vendría del backend
        const adminEmails = ['admin@carrito.com', 'administrador@talento.tech'];
        return this.currentUser && adminEmails.includes(this.currentUser.email);
    }

    // ================================================================
    // MÉTODOS DE ACTUALIZACIÓN DE INTERFAZ
    // ================================================================

    // Método para actualizar la interfaz según el estado de autenticación
    updateUI() {
        // Elementos que se muestran cuando NO está autenticado
        const loginElements = document.querySelectorAll('.auth-login');
        // Elementos que se muestran cuando SÍ está autenticado
        const loggedElements = document.querySelectorAll('.auth-logged');
        // Elemento que muestra el nombre del usuario
        const userNameElement = document.getElementById('user-name');

        if (this.isAuthenticated()) {
            // Usuario autenticado: ocultamos elementos de login
            loginElements.forEach(el => el.style.display = 'none');
            // Mostramos elementos de usuario logueado
            loggedElements.forEach(el => el.style.display = 'block');
            
            // Actualizamos el nombre del usuario si existe el elemento
            if (userNameElement) {
                userNameElement.textContent = this.currentUser.nombre;
            }
        } else {
            // Usuario NO autenticado: mostramos elementos de login
            loginElements.forEach(el => el.style.display = 'block');
            // Ocultamos elementos de usuario logueado
            loggedElements.forEach(el => el.style.display = 'none');
            
            // Limpiamos el nombre del usuario
            if (userNameElement) {
                userNameElement.textContent = '';
            }
        }
    }

    // ================================================================
    // MÉTODOS DE UTILIDAD PARA OTRAS PARTES DE LA APLICACIÓN
    // ================================================================

    // Método para requerir autenticación antes de ejecutar una acción
    requireAuth(callback, errorMessage = 'Debes iniciar sesión para realizar esta acción') {
        if (this.isAuthenticated()) {
            // Si está autenticado, ejecutamos el callback
            callback();
        } else {
            // Si no está autenticado, mostramos mensaje de error
            showAlert(errorMessage, 'warning');
            
            // Opcional: mostrar modal de login automáticamente
            const loginModal = document.getElementById('loginModal');
            if (loginModal) {
                const modal = new bootstrap.Modal(loginModal);
                modal.show();
            }
        }
    }

    // Método para requerir permisos de administrador
    requireAdmin(callback, errorMessage = 'No tienes permisos de administrador') {
        this.requireAuth(() => {
            if (this.isAdmin()) {
                callback();
            } else {
                showAlert(errorMessage, 'danger');
            }
        });
    }
}

// ================================================================
// FUNCIONES UTILITARIAS AUXILIARES
// ================================================================

// Función auxiliar para showAlert (fallback si no está disponible desde api.js)
function showAlert(message, type = 'info') {
    // Intentamos usar la función global si está disponible
    if (typeof window.showAlert === 'function') {
        return window.showAlert(message, type);
    }
    
    // Fallback simple con alert del navegador
    alert(message);
}

// Función auxiliar para showLoading (fallback si no está disponible desde api.js)
function showLoading() {
    if (typeof window.showLoading === 'function') {
        return window.showLoading();
    }
}

// Función auxiliar para hideLoading (fallback si no está disponible desde api.js)
function hideLoading() {
    if (typeof window.hideLoading === 'function') {
        return window.hideLoading();
    }
}

// ================================================================
// INICIALIZACIÓN AUTOMÁTICA
// ================================================================

// Variable global para acceder al AuthManager desde cualquier parte
let authManager;

// Inicializamos AuthManager cuando se carga el DOM
document.addEventListener('DOMContentLoaded', function() {
    // Solo inicializamos si no existe ya una instancia
    if (!authManager) {
        authManager = new AuthManager();
    }
});

// ================================================================
// EXPORTACIÓN PARA OTROS MÓDULOS (si se usa)
// ================================================================

// Si estamos en un entorno que soporta módulos ES6, exportamos la clase
if (typeof module !== 'undefined' && module.exports) {
    module.exports = AuthManager;
} 

