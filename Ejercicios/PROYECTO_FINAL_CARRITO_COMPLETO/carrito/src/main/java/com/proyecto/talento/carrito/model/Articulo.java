// Declaramos el paquete donde se encuentra esta clase
package com.proyecto.talento.carrito.model;

// Importamos las anotaciones de JPA (Java Persistence API) para mapear la clase a la base de datos
import jakarta.persistence.*;
// Importamos las anotaciones de validación para validar los datos
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

// @Entity le dice a Spring que esta clase representa una tabla en la base de datos
@Entity
// @Table especifica el nombre de la tabla en la base de datos (si no se pone, usa el nombre de la clase)
@Table(name = "articulos")
// Declaramos la clase pública Articulo que representa un producto en nuestro carrito
public class Articulo {

    // @Id indica que este campo es la clave primaria de la tabla
    @Id
    // @GeneratedValue con IDENTITY significa que la base de datos generará automáticamente el ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Campo privado que almacena el identificador único del artículo
    private Long id;

    // @NotBlank valida que el campo no esté vacío ni sea solo espacios en blanco
    @NotBlank(message = "El nombre no puede estar vacío")
    // @Size valida que el texto tenga entre 2 y 100 caracteres
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    // Campo que almacena el nombre del artículo
    private String nombre;
    
    // @NotNull valida que el campo no sea nulo (pero puede ser 0)
    @NotNull(message = "El precio no puede estar vacío")
    // @DecimalMin valida que el precio sea mayor a 0.01 (al menos 1 centavo)
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    // Campo que almacena el precio del artículo (usamos Double para decimales)
    private Double precio;
    
    // @Column especifica el nombre de la columna en la base de datos
    @Column(name = "descripcion")
    // Campo opcional que almacena la descripción del artículo
    private String descripcion;
    
    // @Min valida que el stock no sea negativo (mínimo 0)
    @Min(value = 0, message = "El stock no puede ser negativo")
    // Campo que almacena la cantidad disponible del artículo
    private Integer stock;
    
    // Campo que indica si el artículo está activo (disponible para venta)
    @Column(name = "activo")
    private Boolean activo;

    // Constructor vacío requerido por JPA para crear instancias de la clase
    public Articulo() {
        // Inicializamos el stock en 0 por defecto
        this.stock = 0;
        // Inicializamos el artículo como activo por defecto
        this.activo = true;
    }

    // Constructor con parámetros para crear un artículo con valores específicos
    public Articulo(String nombre, Double precio, String descripcion, Integer stock) {
        // Asignamos el nombre recibido como parámetro
        this.nombre = nombre;
        // Asignamos el precio recibido como parámetro
        this.precio = precio;
        // Asignamos la descripción recibida como parámetro
        this.descripcion = descripcion;
        // Si el stock es nulo, lo inicializamos en 0, sino usamos el valor recibido
        this.stock = stock != null ? stock : 0;
        // Por defecto, el artículo se crea como activo
        this.activo = true;
    }

    // Método getter para obtener el ID del artículo
    public Long getId() { return id; }
    // Método setter para establecer el ID del artículo
    public void setId(Long id) { this.id = id; }
    
    // Método getter para obtener el nombre del artículo
    public String getNombre() { return nombre; }
    // Método setter para establecer el nombre del artículo
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    // Método getter para obtener el precio del artículo
    public Double getPrecio() { return precio; }
    // Método setter para establecer el precio del artículo
    public void setPrecio(Double precio) { this.precio = precio; }
    
    // Método getter para obtener la descripción del artículo
    public String getDescripcion() { return descripcion; }
    // Método setter para establecer la descripción del artículo
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    // Método getter para obtener el stock del artículo
    public Integer getStock() { return stock; }
    // Método setter para establecer el stock del artículo
    public void setStock(Integer stock) { this.stock = stock; }
    
    // Método getter para saber si el artículo está activo
    public Boolean getActivo() { return activo; }
    // Método setter para activar o desactivar el artículo
    public void setActivo(Boolean activo) { this.activo = activo; }
    
    // @Override indica que estamos sobrescribiendo un método de la clase padre (Object)
    @Override
    // Método toString que devuelve una representación en texto del objeto
    // Útil para debugging y logs
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", activo=" + activo +
                '}';
    }
}



