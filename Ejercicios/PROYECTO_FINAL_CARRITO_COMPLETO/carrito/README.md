# 🛒 CarritoTech - Sistema de E-commerce Completo

## 📋 Descripción General

**CarritoTech** es una aplicación completa de e-commerce desarrollada con **Spring Boot** (backend) y **HTML5/CSS3/JavaScript vanilla** (frontend). Incluye un sistema de gestión de productos, clientes, carrito de compras, y panel de administración.

## 🏗️ Arquitectura del Sistema

```
┌─────────────────┐    HTTP/REST    ┌─────────────────┐    JPA/Hibernate    ┌─────────────────┐
│    Frontend     │ ──────────────► │   Spring Boot   │ ─────────────────► │      MySQL      │
│  (HTML/JS/CSS)  │                 │   Backend API   │                     │    Database     │
└─────────────────┘                 └─────────────────┘                     └─────────────────┘
```

### Tecnologías Utilizadas

**Backend:**
- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- Hibernate 6.6.18
- MySQL 8.0
- Maven 3.9+

**Frontend:**
- HTML5 semántico
- CSS3 con Bootstrap 5.0.2
- JavaScript ES6+ (Vanilla)
- Bootstrap Icons

## 🛠️ Requisitos del Sistema

### Obligatorios
- **Java 17** o superior
- **MySQL 8.0** o superior
- **Maven 3.9** o superior (incluido en el proyecto como `mvnw`)
- **Navegador web moderno** (Chrome, Firefox, Safari, Edge)

### Recomendados
- **IDE:** IntelliJ IDEA, Eclipse, VS Code
- **Cliente MySQL:** MySQL Workbench, phpMyAdmin, DBeaver
- **Herramientas:** Postman (para testing API)

## 📦 Instalación y Configuración

### Paso 1: Clonar el Repositorio

```bash
git clone <url-del-repositorio>
cd carrito
```

### Paso 2: Configurar Base de Datos MySQL

1. **Instalar MySQL 8.0** si no lo tienes:
   - Windows: Descargar desde [mysql.com](https://dev.mysql.com/downloads/mysql/)
   - macOS: `brew install mysql`
   - Linux: `sudo apt-get install mysql-server`

2. **Crear la base de datos:**
```sql
CREATE DATABASE carrito_db;
CREATE USER 'carrito_user'@'localhost' IDENTIFIED BY 'carrito_password';
GRANT ALL PRIVILEGES ON carrito_db.* TO 'carrito_user'@'localhost';
FLUSH PRIVILEGES;
```

### Paso 3: Configurar Propiedades de la Aplicación

Edita el archivo `src/main/resources/application.properties`:

```properties
# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/carrito_db
spring.datasource.username=carrito_user
spring.datasource.password=carrito_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Puerto del servidor
server.port=8080

# Configuración de encoding
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
```

## 🚀 Ejecución de la Aplicación

### Opción 1: Usando Maven Wrapper (Recomendado)

```bash
# En Windows
./mvnw.cmd spring-boot:run

# En Linux/macOS
./mvnw spring-boot:run
```

### Opción 2: Usando Maven Instalado

```bash
mvn spring-boot:run
```

### Opción 3: Ejecutar JAR Compilado

```bash
# Compilar
./mvnw clean package

# Ejecutar
java -jar target/carrito-0.0.1-SNAPSHOT.jar
```

### Verificar que la Aplicación esté Funcionando

1. **Backend API:** Abre `http://localhost:8080/api/articulos` - debe mostrar JSON
2. **Frontend:** Abre `http://localhost:8080` - debe mostrar la página principal

## 🌐 Estructura del Frontend

### Páginas Principales

| Página | URL | Descripción |
|--------|-----|-------------|
| **Inicio** | `/` | Página principal con estadísticas y navegación |
| **Productos** | `/productos.html` | Galería de productos con búsqueda y filtros |
| **Carrito** | `/carrito.html` | Gestión del carrito de compras |
| **Clientes** | `/clientes.html` | CRUD de clientes |
| **Admin** | `/admin.html` | Panel de administración completo |

### Páginas de Debug/Testing

| Página | URL | Descripción |
|--------|-----|-------------|
| **Debug Carrito** | `/debug-carrito.html` | Debug específico del carrito y compras |
| **Test Login-Compra** | `/test-login-compra.html` | Pruebas de login y checkout |
| **Test Completo** | `/test-completo.html` | Pruebas integrales del sistema |

### Sistema de Autenticación

**Nota:** El sistema usa **autenticación simulada** con localStorage:
- No requiere registro real
- Cualquier email válido puede hacer "login"
- La información se almacena en el navegador

## 🔌 API REST Endpoints

### Artículos/Productos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/articulos` | Obtener todos los artículos |
| `GET` | `/api/articulos/activos` | Obtener artículos activos |
| `GET` | `/api/articulos/{id}` | Obtener artículo por ID |
| `GET` | `/api/articulos/buscar/{nombre}` | Buscar por nombre |
| `GET` | `/api/articulos/precio?min=X&max=Y` | Filtrar por rango de precios |
| `POST` | `/api/articulos` | Crear nuevo artículo |
| `PUT` | `/api/articulos/{id}` | Actualizar artículo |
| `PUT` | `/api/articulos/{id}/stock?stock=X` | Actualizar stock |
| `DELETE` | `/api/articulos/{id}` | Eliminar artículo |

### Clientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/clientes` | Obtener todos los clientes |
| `GET` | `/api/clientes/{id}` | Obtener cliente por ID |
| `GET` | `/api/clientes/email/{email}` | Buscar por email |
| `GET` | `/api/clientes/dni/{dni}` | Buscar por DNI |
| `POST` | `/api/clientes` | Crear nuevo cliente |
| `PUT` | `/api/clientes/{id}` | Actualizar cliente |
| `DELETE` | `/api/clientes/{id}` | Eliminar cliente |

### Pedidos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/pedidos` | Obtener todos los pedidos |
| `GET` | `/api/pedidos/{id}` | Obtener pedido por ID |
| `GET` | `/api/pedidos/cliente/{clienteId}` | Pedidos por cliente |
| `GET` | `/api/pedidos/estado/{estado}` | Pedidos por estado |
| `POST` | `/api/pedidos` | Crear nuevo pedido |
| `POST` | `/api/pedidos/{pedidoId}/articulos/{articuloId}?cantidad=X` | Agregar artículo a pedido |
| `PUT` | `/api/pedidos/{id}/estado?estado=X` | Cambiar estado |
| `PUT` | `/api/pedidos/{id}/actualizar-total` | Actualizar total |
| `DELETE` | `/api/pedidos/{id}` | Eliminar pedido |

## 📱 Funcionalidades del Frontend

### 🏠 Página Principal (`index.html`)
- Hero section con call-to-action
- Estadísticas en tiempo real
- Navegación responsive
- Enlaces a todas las secciones

### 🛍️ Galería de Productos (`productos.html`)
- Vista tipo tarjetas responsive
- Búsqueda por nombre
- Filtros por rango de precios:
  - Hasta $50,000
  - $50,000 - $150,000  
  - Más de $150,000
- Indicador de stock bajo
- Botón "Agregar al Carrito"

### 🛒 Carrito de Compras (`carrito.html`)
- Visualización de productos agregados
- Modificar cantidades
- Eliminar productos
- Cálculo automático de totales
- Botón "Finalizar Compra"
- Persistencia en localStorage

### 👥 Gestión de Clientes (`clientes.html`)
- Tabla con todos los clientes
- Búsqueda por nombre, email o DNI
- Formularios de crear/editar
- Validaciones de email y DNI únicos
- Eliminación con confirmación

### ⚙️ Panel de Administración (`admin.html`)
- **Dashboard con estadísticas**
- **Gestión de Productos:**
  - CRUD completo
  - Actualización de stock
  - Activar/desactivar productos
- **Gestión de Pedidos:**
  - Lista de todos los pedidos
  - Cambio de estados
  - Filtros por fecha y estado
  - Visualización de detalles
- **Gestión de Clientes:**
  - CRUD completo integrado

## 🔧 Sistema de Debug

### Herramientas de Debugging Incluidas

**1. Debug Carrito (`debug-carrito.html`)**
- Estado en tiempo real del sistema
- Control manual de login/logout
- Gestión del carrito paso a paso
- Pruebas de finalización de compra
- Console log visible en pantalla

**2. Test Integral (`test-completo.html`)**
- Pruebas automáticas de todos los módulos
- Log de actividades detallado
- Enlaces rápidos a todas las páginas
- Creación de datos de prueba

**3. Console del Navegador**
- Logs detallados de todas las operaciones
- Mensajes de error específicos
- Estado de las peticiones HTTP

## 📂 Estructura del Proyecto

```
carrito/
├── src/
│   ├── main/
│   │   ├── java/com/proyecto/talento/carrito/
│   │   │   ├── CarritoApplication.java          # Clase principal
│   │   │   ├── config/
│   │   │   │   └── CorsConfig.java              # Configuración CORS
│   │   │   ├── controller/                      # Controladores REST
│   │   │   │   ├── ArticuloController.java
│   │   │   │   ├── ClienteController.java
│   │   │   │   └── PedidoController.java
│   │   │   ├── model/                           # Entidades JPA
│   │   │   │   ├── Articulo.java
│   │   │   │   ├── Cliente.java
│   │   │   │   ├── Pedido.java
│   │   │   │   └── PedidoArticulo.java
│   │   │   ├── repository/                      # Repositorios JPA
│   │   │   │   ├── ArticuloRepository.java
│   │   │   │   ├── ClienteRepository.java
│   │   │   │   ├── PedidoRepository.java
│   │   │   │   └── PedidoArticuloRepository.java
│   │   │   └── service/                         # Lógica de negocio
│   │   │       ├── ArticuloService.java
│   │   │       ├── ClienteService.java
│   │   │       └── PedidoService.java
│   │   └── resources/
│   │       ├── application.properties           # Configuración
│   │       └── static/                          # Frontend
│   │           ├── css/
│   │           │   └── styles.css               # Estilos personalizados
│   │           ├── js/
│   │           │   ├── api.js                   # Funciones API
│   │           │   ├── app.js                   # Lógica principal
│   │           │   └── auth.js                  # Sistema de autenticación
│   │           ├── index.html                   # Página principal
│   │           ├── productos.html               # Galería de productos
│   │           ├── carrito.html                 # Carrito de compras
│   │           ├── clientes.html                # Gestión de clientes
│   │           ├── admin.html                   # Panel de administración
│   │           ├── debug-carrito.html           # Debug del carrito
│   │           ├── test-login-compra.html       # Test de login/compra
│   │           └── test-completo.html           # Test integral
│   └── test/
│       └── java/com/proyecto/talento/carrito/
│           └── CarritoApplicationTests.java     # Tests unitarios
├── target/                                      # Archivos compilados
├── mvnw                                         # Maven wrapper (Linux/Mac)
├── mvnw.cmd                                     # Maven wrapper (Windows)
├── pom.xml                                      # Configuración Maven
└── README.md                                    # Este archivo
```

## 🎨 Diseño y UX

### Paleta de Colores
- **Primario:** Grises y negros (#212529, #495057, #6c757d)
- **Secundario:** Blancos y grises claros (#f8f9fa, #e9ecef)
- **Acentos:** Bootstrap colors (success, danger, warning, info)

### Características de Diseño
- **Totalmente responsive** (móvil, tablet, desktop)
- **Etiquetas semánticas HTML5**
- **Animaciones suaves CSS**
- **Iconografía consistente** (Bootstrap Icons)
- **Tipografía legible** (System fonts)

## 🔄 Flujo de Trabajo del Usuario

### Flujo de Compra Estándar

1. **Navegación:** Usuario entra a `/` o `/productos.html`
2. **Búsqueda:** Filtra productos por nombre o precio
3. **Selección:** Agrega productos al carrito
4. **Revisión:** Va a `/carrito.html` para revisar compra
5. **Login:** Se autentica con cualquier email válido
6. **Compra:** Hace clic en "Finalizar Compra"
7. **Confirmación:** Sistema crea pedido y confirma

### Flujo de Administración

1. **Acceso:** Va a `/admin.html`
2. **Dashboard:** Ve estadísticas generales
3. **Gestión:**
   - Productos: Crear/editar/eliminar/actualizar stock
   - Pedidos: Ver lista, cambiar estados, filtrar
   - Clientes: CRUD completo

## 🛠️ Troubleshooting

### Problemas Comunes

**1. Error "Puerto 8080 en uso"**
```bash
# Encontrar proceso
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # Linux/Mac

# Matar proceso
taskkill /PID <PID> /F        # Windows
kill -9 <PID>                # Linux/Mac
```

**2. Error de conexión a MySQL**
- Verificar que MySQL esté corriendo
- Comprobar credenciales en `application.properties`
- Verificar que la base de datos exista

**3. Errores de CORS**
- Los endpoints ya están configurados para CORS
- Si persiste, verificar `CorsConfig.java`

**4. Frontend no carga**
- Verificar que la aplicación esté en `http://localhost:8080`
- Limpiar caché del navegador
- Verificar que los archivos estén en `/static`

### Logs Útiles

**Backend:**
- Logs de Spring Boot en la consola
- Queries SQL de Hibernate
- Errores de validación

**Frontend:**
- Console del navegador (F12)
- Network tab para peticiones HTTP
- Páginas de debug incluidas

## 🧪 Testing

### Datos de Prueba

El sistema incluye funciones para crear datos de prueba automáticamente:

**Productos de ejemplo:**
- Smartphone Samsung Galaxy
- Laptop Dell Inspiron  
- Auriculares Sony
- Tablet iPad Air
- Smartwatch Apple Watch

**Clientes de ejemplo:**
- test@example.com
- admin@carrito.com
- cliente@test.com

### Páginas de Testing

1. **`/debug-carrito.html`** - Testing específico del carrito
2. **`/test-login-compra.html`** - Testing de autenticación y compra
3. **`/test-completo.html`** - Testing integral del sistema

## 📚 API Documentation

### Formato de Respuestas

**Éxito:**
```json
{
  "id": 1,
  "nombre": "Producto ejemplo",
  "precio": 50000,
  "stock": 10
}
```

**Error:**
```json
{
  "mensaje": "Error específico",
  "success": false
}
```

### Códigos de Estado HTTP

- `200` - OK
- `201` - Created
- `400` - Bad Request
- `404` - Not Found
- `500` - Internal Server Error

## 🚀 Despliegue en Producción

### Variables de Entorno Recomendadas

```bash
# Base de datos
DB_URL=jdbc:mysql://localhost:3306/carrito_db
DB_USERNAME=carrito_user
DB_PASSWORD=carrito_password

# Servidor
SERVER_PORT=8080

# Perfil de Spring
SPRING_PROFILES_ACTIVE=prod
```

### Configuración de Producción

```properties
# application-prod.properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
logging.level.org.springframework=WARN
logging.level.com.proyecto.talento.carrito=INFO
```

## 📞 Soporte

### Recursos Adicionales

- **Spring Boot Documentation:** [spring.io](https://spring.io/projects/spring-boot)
- **Bootstrap Documentation:** [getbootstrap.com](https://getbootstrap.com/)
- **MySQL Documentation:** [dev.mysql.com](https://dev.mysql.com/doc/)

### Contacto

Para reportar bugs o solicitar features, crear un issue en el repositorio del proyecto.

---

## 🎉 ¡Listo para Usar!

Con este manual deberías poder:
1. ✅ Instalar y configurar todo el entorno
2. ✅ Ejecutar la aplicación correctamente
3. ✅ Entender la arquitectura del sistema
4. ✅ Usar todas las funcionalidades
5. ✅ Hacer debugging cuando sea necesario
6. ✅ Extender la aplicación según tus necesidades

**¡Disfruta usando CarritoTech!** 🛒✨ 