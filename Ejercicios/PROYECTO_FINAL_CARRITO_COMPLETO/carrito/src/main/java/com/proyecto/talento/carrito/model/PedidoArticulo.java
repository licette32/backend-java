// Declaramos el paquete donde se encuentra esta clase
package com.proyecto.talento.carrito.model;

// Importamos JsonIgnore para evitar bucles infinitos en la serialización JSON
import com.fasterxml.jackson.annotation.JsonIgnore;
// Importamos las anotaciones de JPA para el mapeo a la base de datos
import jakarta.persistence.*;
// Importamos validaciones para valores mínimos
import jakarta.validation.constraints.Min;
// Importamos validaciones para campos requeridos
import jakarta.validation.constraints.NotNull;

// @Entity marca esta clase como una entidad de base de datos
@Entity
// @Table especifica el nombre de la tabla intermedia que relaciona pedidos y artículos
@Table(name = "pedido_articulos")
// Clase que representa la relación entre un pedido y un artículo específico
// Esta es una tabla intermedia que almacena cantidad y precio de cada artículo en cada pedido
public class PedidoArticulo {
    
    // @Id marca este campo como la clave primaria de la tabla
    @Id
    // @GeneratedValue indica que el ID se genera automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Campo que almacena el identificador único de esta relación pedido-artículo
    private Long id;
    
    // @ManyToOne define relación muchos-a-uno (muchos PedidoArticulo pueden tener un Pedido)
    // fetch = FetchType.EAGER significa que se carga el pedido inmediatamente
    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn especifica la columna que conecta con la tabla pedidos
    @JoinColumn(name = "pedido_id", nullable = false)
    // @JsonIgnore evita bucles infinitos al serializar a JSON
    @JsonIgnore
    // Campo que almacena la referencia al pedido al que pertenece este artículo
    private Pedido pedido;
    
    // @ManyToOne define relación muchos-a-uno (muchos PedidoArticulo pueden tener un Articulo)
    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn especifica la columna que conecta con la tabla articulos
    @JoinColumn(name = "articulo_id", nullable = false)
    // Campo que almacena la referencia al artículo que se está comprando
    private Articulo articulo;
    
    // Validación para que la cantidad no sea nula
    @NotNull(message = "La cantidad no puede estar vacía")
    // Validación para que la cantidad sea al menos 1
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    // Campo que almacena cuántas unidades de este artículo se compraron
    private Integer cantidad;
    
    // Campo que almacena el precio unitario del artículo al momento de la compra
    // Es importante guardarlo porque el precio puede cambiar en el futuro
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    
    // Campo que almacena el subtotal (cantidad * precio unitario)
    @Column(name = "subtotal")
    private Double subtotal;
    
    // Constructor vacío necesario para JPA
    public PedidoArticulo() {}
    
    // Constructor con parámetros para crear una relación pedido-artículo
    public PedidoArticulo(Pedido pedido, Articulo articulo, Integer cantidad, Double precioUnitario) {
        // Asignamos el pedido al que pertenece este artículo
        this.pedido = pedido;
        // Asignamos el artículo que se está comprando
        this.articulo = articulo;
        // Asignamos la cantidad de unidades
        this.cantidad = cantidad;
        // Asignamos el precio unitario al momento de la compra
        this.precioUnitario = precioUnitario;
        // Calculamos automáticamente el subtotal
        this.subtotal = cantidad * precioUnitario;
    }
    
    // Getters y Setters - Métodos para acceder y modificar los campos
    
    // Método getter para obtener el ID único de esta relación
    public Long getId() { return id; }
    // Método setter para establecer el ID
    public void setId(Long id) { this.id = id; }
    
    // Método getter para obtener el pedido al que pertenece este artículo
    public Pedido getPedido() { return pedido; }
    // Método setter para establecer el pedido
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    
    // Método getter para obtener el artículo que se está comprando
    public Articulo getArticulo() { return articulo; }
    // Método setter para establecer el artículo
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }
    
    // Método getter para obtener la cantidad de unidades
    public Integer getCantidad() { return cantidad; }
    // Método setter para establecer la cantidad
    public void setCantidad(Integer cantidad) { 
        // Asignamos la nueva cantidad
        this.cantidad = cantidad; 
        // Recalculamos automáticamente el subtotal
        calcularSubtotal();
    }
    
    // Método getter para obtener el precio unitario
    public Double getPrecioUnitario() { return precioUnitario; }
    // Método setter para establecer el precio unitario
    public void setPrecioUnitario(Double precioUnitario) { 
        // Asignamos el nuevo precio
        this.precioUnitario = precioUnitario; 
        // Recalculamos automáticamente el subtotal
        calcularSubtotal();
    }
    
    // Método getter para obtener el subtotal calculado
    public Double getSubtotal() { return subtotal; }
    // Método setter para establecer el subtotal manualmente
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    
    // Método privado para calcular el subtotal automáticamente
    private void calcularSubtotal() {
        // Verificamos que tanto cantidad como precio unitario no sean nulos
        if (cantidad != null && precioUnitario != null) {
            // Calculamos el subtotal multiplicando cantidad por precio unitario
            this.subtotal = cantidad * precioUnitario;
        } else {
            // Si algún valor es nulo, el subtotal es 0
            this.subtotal = 0.0;
        }
    }
    
    // @PrePersist se ejecuta antes de insertar el registro en la base de datos
    @PrePersist
    // @PreUpdate se ejecuta antes de actualizar el registro en la base de datos
    @PreUpdate
    // Método que se ejecuta automáticamente antes de guardar o actualizar
    public void preSave() {
        // Calculamos el subtotal antes de guardar para asegurar consistencia
        calcularSubtotal();
    }
    
    // Método público para recalcular subtotal manualmente
    public void recalcularSubtotal() {
        // Llama al método privado de cálculo
        calcularSubtotal();
    }
} 

