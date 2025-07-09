// Declaramos el paquete donde se encuentra esta clase
package com.proyecto.talento.carrito.model;

// Importamos las anotaciones de JPA para el mapeo a la base de datos
import jakarta.persistence.*;
// Importamos las validaciones para el email
import jakarta.validation.constraints.Email;
// Importamos validaciones para campos requeridos
import jakarta.validation.constraints.NotBlank;
// Importamos validaciones para el tamaño de los campos
import jakarta.validation.constraints.Size;

// @Entity marca esta clase como una entidad de base de datos
@Entity
// @Table especifica el nombre de la tabla donde se guardarán los clientes
@Table(name = "clientes")
// Clase que representa a un cliente del sistema de carrito de compras
public class Cliente {
    
    // @Id marca este campo como la clave primaria de la tabla
    @Id
    // @GeneratedValue indica que el ID se genera automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Campo que almacena el identificador único del cliente
    private Long id;
    
    // @NotBlank valida que el nombre no esté vacío
    @NotBlank(message = "El nombre no puede estar vacío")
    // @Size valida que el nombre tenga entre 2 y 100 caracteres
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    // Campo que almacena el primer nombre del cliente
    private String nombre;
    
    // Validación para que el apellido no esté vacío
    @NotBlank(message = "El apellido no puede estar vacío")
    // Validación del tamaño del apellido
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    // Campo que almacena el apellido del cliente
    private String apellido;
    
    // @Email valida que el campo tenga formato de correo electrónico válido
    @Email(message = "El email debe tener un formato válido")
    // Validación para que el email no esté vacío
    @NotBlank(message = "El email no puede estar vacío")
    // @Column con unique=true significa que no puede haber dos clientes con el mismo email
    @Column(unique = true)
    // Campo que almacena el correo electrónico del cliente (único en el sistema)
    private String email;
    
    // Validación para que el DNI no esté vacío
    @NotBlank(message = "El DNI no puede estar vacío")
    // Validación del tamaño del DNI (7 a 10 caracteres)
    @Size(min = 7, max = 10, message = "El DNI debe tener entre 7 y 10 caracteres")
    // Campo único para el documento de identidad
    @Column(unique = true)
    // Campo que almacena el DNI del cliente (único en el sistema)
    private String dni;
    
    // Campo opcional para el teléfono del cliente (sin validaciones estrictas)
    private String telefono;
    
    // Campo opcional para la dirección del cliente
    private String direccion;
    
    // Constructor por defecto
    // Constructor vacío necesario para JPA (para crear instancias desde la base de datos)
    public Cliente() {}
    
    // Constructor con todos los parámetros para crear un nuevo cliente
    public Cliente(String nombre, String apellido, String email, String dni, String telefono, String direccion) {
        // Asignar el nombre del cliente
        this.nombre = nombre;
        // Asignar el apellido del cliente
        this.apellido = apellido;
        // Asignar el email del cliente
        this.email = email;
        // Asignar el DNI del cliente
        this.dni = dni;
        // Asignar el teléfono del cliente
        this.telefono = telefono;
        // Asignar la dirección del cliente
        this.direccion = direccion;
    }
    
    // Getters y Setters
    // Método getter para obtener el ID único del cliente
    public Long getId() { return id; }
    // Método setter para establecer el ID del cliente
    public void setId(Long id) { this.id = id; }
    
    // Método getter para obtener el nombre del cliente
    public String getNombre() { return nombre; }
    // Método setter para establecer el nombre del cliente
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    // Método getter para obtener el apellido del cliente
    public String getApellido() { return apellido; }
    // Método setter para establecer el apellido del cliente
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    // Método getter para obtener el email del cliente
    public String getEmail() { return email; }
    // Método setter para establecer el email del cliente
    public void setEmail(String email) { this.email = email; }
    
    // Método getter para obtener el DNI del cliente
    public String getDni() { return dni; }
    // Método setter para establecer el DNI del cliente
    public void setDni(String dni) { this.dni = dni; }
    
    // Método getter para obtener el teléfono del cliente
    public String getTelefono() { return telefono; }
    // Método setter para establecer el teléfono del cliente
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    // Método getter para obtener la dirección del cliente
    public String getDireccion() { return direccion; }
    // Método setter para establecer la dirección del cliente
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    // @Override indica que sobreescribimos el método toString de la clase Object
    @Override
    // Método toString que devuelve una representación en texto del cliente
    // Útil para mostrar información del cliente en logs y debugging
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
} 

