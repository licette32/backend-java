# Frontend CarritoTech - Documentación

## Descripción General

Este frontend es una aplicación web completa para el sistema de carrito de compras CarritoTech. Está desarrollado con **Bootstrap 5.0**, **JavaScript vanilla** y sigue las mejores prácticas de desarrollo web moderno.

## Características Principales

### 🎨 Diseño
- **Paleta de colores**: Grises, negros y blancos
- **Framework CSS**: Bootstrap 5.0
- **Iconos**: Bootstrap Icons
- **Responsive**: Completamente adaptable a dispositivos móviles
- **Semántica HTML**: Utiliza etiquetas semánticas (header, main, footer)

### 🚀 Funcionalidades

#### Autenticación
- Inicio de sesión por email
- Registro de nuevos usuarios
- Gestión de sesiones con localStorage
- Validación de formularios

#### Gestión de Productos
- Visualización de productos en galería
- Búsqueda por nombre
- Filtrado por rango de precios
- Información detallada de stock
- Agregado al carrito

#### Carrito de Compras
- Visualización de productos agregados
- Actualización de cantidades
- Eliminación de productos
- Cálculo automático de totales
- Finalización de compra

#### Panel de Administración
- Gestión CRUD de productos
- Gestión de pedidos
- Actualización de estados
- Estadísticas del negocio

## Estructura de Archivos

```
src/main/resources/static/
├── index.html          # Página principal
├── productos.html      # Galería de productos
├── carrito.html        # Carrito de compras
├── clientes.html       # Gestión de clientes
├── admin.html          # Panel de administración
├── css/
│   └── styles.css      # Estilos personalizados
└── js/
    ├── api.js          # Manejo de API REST
    ├── app.js          # Funcionalidades principales
    └── auth.js         # Sistema de autenticación
```

## Configuración y Uso

### Requisitos Previos
1. **API Backend**: Debe estar ejecutándose en `localhost:8080`
2. **Base de datos**: MySQL configurada y funcionando
3. **Datos de prueba**: Se recomienda tener algunos productos y clientes creados

### Instalación
1. Asegúrate de que tu API Spring Boot esté ejecutándose
2. Abre tu navegador y visita: `http://localhost:8080`
3. El frontend se servirá automáticamente desde `/src/main/resources/static/`

### Navegación Principal

#### 🏠 Página Principal (index.html)
- Página de bienvenida con información de la empresa
- Estadísticas en tiempo real
- Modales de login/registro
- Navegación a todas las secciones

#### 🛍️ Productos (productos.html)
- Galería de productos con diseño tipo tarjeta
- Barra de búsqueda por nombre
- Filtros por rango de precios
- Botón "Agregar al carrito" en cada producto
- Información de stock disponible

#### 🛒 Carrito (carrito.html)
- Lista de productos agregados
- Controles para aumentar/disminuir cantidades
- Botón para eliminar productos
- Resumen del pedido con totales
- Información de envío y métodos de pago
- Botón "Finalizar Compra"

#### 👥 Clientes (clientes.html)
- Tabla completa de clientes registrados
- Funcionalidad de búsqueda
- Formulario para crear/editar clientes
- Validaciones de DNI y email
- Confirmación de eliminación

#### ⚙️ Admin (admin.html)
- **Pestaña Productos**: CRUD completo de productos
- **Pestaña Pedidos**: Gestión de pedidos y estados
- **Pestaña Estadísticas**: Métricas del negocio
- Filtros avanzados por fecha y estado

## Funcionalidades Técnicas

### 🔌 Conexión con API
- Todas las operaciones se realizan através de la API REST
- Manejo de errores con mensajes informativos
- Spinner de carga durante las peticiones
- Validación de respuestas del servidor

### 💾 Gestión de Estado
- **localStorage**: Persistencia del carrito y usuario logueado
- **Sesiones**: Manejo de autenticación sin backend de sesiones
- **Reactividad**: Actualización automática de contadores y totales

### 📱 Responsive Design
- Diseño completamente adaptable
- Optimizado para móviles, tablets y desktop
- Navegación colapsable en dispositivos móviles
- Tablas responsivas con scroll horizontal

### 🎯 Experiencia de Usuario
- Animaciones suaves con CSS transitions
- Alertas contextuales (success, danger, warning, info)
- Confirmaciones para acciones críticas
- Tooltips y ayudas visuales

## Flujo de Trabajo del Usuario

### 1. Usuario Nuevo
1. Visita la página principal
2. Explora productos sin necesidad de registrarse
3. Puede agregar productos al carrito
4. Para finalizar compra, debe registrarse o iniciar sesión

### 2. Usuario Registrado
1. Inicia sesión con su email
2. Navega y agrega productos al carrito
3. Revisa el carrito y modifica cantidades
4. Finaliza la compra (se crea un pedido en el backend)

### 3. Administrador
1. Accede al panel de administración
2. Gestiona productos (crear, editar, eliminar, actualizar stock)
3. Supervisa pedidos y actualiza estados
4. Revisa estadísticas del negocio

## Configuración de la API

El frontend está configurado para conectarse a la API en `localhost:8080`. Si necesitas cambiar esta configuración:

1. Abre `js/api.js`
2. Modifica la variable `API_BASE_URL`
3. Guarda los cambios y recarga la página

```javascript
const API_BASE_URL = 'http://localhost:8080/api';
```

## Características Avanzadas

### 🔄 Actualización Automática
- Contador del carrito se actualiza en tiempo real
- Estadísticas se cargan automáticamente
- Tablas se refrescan después de operaciones CRUD

### 🔍 Búsqueda y Filtrado
- Búsqueda en tiempo real (sin necesidad de botón)
- Filtros múltiples (precio, estado, fecha)
- Resultados instantáneos

### 💰 Cálculos Automáticos
- Subtotales por producto
- Total general del carrito
- Actualización automática al cambiar cantidades

### 🛡️ Validaciones
- Validación de formularios en tiempo real
- Verificación de stock antes de agregar al carrito
- Validación de formato de email y DNI

## Solución de Problemas

### La página no carga
- Verifica que la API esté ejecutándose en `localhost:8080`
- Revisa la consola del navegador para errores
- Asegúrate de que no haya conflictos de puertos

### Los productos no se muestran
- Verifica que hay productos en la base de datos
- Revisa que los productos estén marcados como "activos"
- Comprueba la conexión a la API

### El carrito no funciona
- Verifica que localStorage esté habilitado en el navegador
- Revisa que no haya errores de JavaScript en la consola
- Asegúrate de que el usuario esté autenticado para finalizar compras

### Los formularios no validan
- Verifica que todos los campos obligatorios estén completos
- Revisa el formato de email y DNI
- Comprueba que no haya caracteres especiales problemáticos

## Mejoras Futuras

### 🎯 Funcionalidades Planificadas
- [ ] Historial de pedidos por usuario
- [ ] Notificaciones push
- [ ] Carrito persistente entre sesiones
- [ ] Wishlist de productos favoritos
- [ ] Comentarios y calificaciones de productos
- [ ] Integración con pasarelas de pago
- [ ] Dashboard de analytics más avanzado

### 🔧 Mejoras Técnicas
- [ ] PWA (Progressive Web App)
- [ ] Service Workers para cache
- [ ] Lazy loading de imágenes
- [ ] Optimización de rendimiento
- [ ] Testing automatizado

## Soporte y Mantenimiento

Para reportar problemas o solicitar nuevas funcionalidades:

1. Revisa la consola del navegador para errores
2. Verifica que la API esté funcionando correctamente
3. Comprueba que tengas datos de prueba en la base de datos
4. Documenta los pasos para reproducir el problema

## Conclusión

Este frontend proporciona una experiencia completa de e-commerce con todas las funcionalidades necesarias para un negocio en línea. El diseño responsive, la interfaz intuitiva y la integración completa con la API REST hacen de esta aplicación una solución robusta y escalable.

¡Disfruta usando CarritoTech! 🎉 