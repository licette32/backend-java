// Declaramos el paquete donde se encuentra esta clase
package com.proyecto.talento.carrito.model;

// Importamos JsonIgnore para evitar bucles infinitos en la serialización JSON
import com.fasterxml.jackson.annotation.JsonIgnore;
// Importamos las anotaciones de JPA para el mapeo a la base de datos
import jakarta.persistence.*;
// Importamos validaciones para campos requeridos
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// Importamos LocalDateTime para manejar fechas y horas
import java.time.LocalDateTime;
// Importamos ArrayList para manejar listas
import java.util.ArrayList;
// Importamos List para manejar listas de elementos
import java.util.List;

// @Entity marca esta clase como una entidad de base de datos
@Entity
// @Table especifica el nombre de la tabla donde se guardarán los pedidos
@Table(name = "pedidos")
// Clase que representa un pedido o orden de compra en el sistema
public class Pedido {
    
    // @Id marca este campo como la clave primaria de la tabla
    @Id
    // @GeneratedValue indica que el ID se genera automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Campo que almacena el identificador único del pedido
    private Long id;
    
    // @ManyToOne define una relación muchos-a-uno (muchos pedidos pueden tener un cliente)
    // fetch = FetchType.EAGER significa que se carga el cliente inmediatamente
    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn especifica la columna que conecta las tablas
    @JoinColumn(name = "cliente_id", nullable = false)
    // Validación para que siempre haya un cliente asociado al pedido
    @NotNull(message = "El cliente no puede estar vacío")
    // Campo que almacena la referencia al cliente que hizo el pedido
    private Cliente cliente;
    
    // @Column especifica el nombre de la columna en la base de datos
    @Column(name = "fecha_pedido")
    // Campo que almacena cuándo se creó el pedido
    private LocalDateTime fechaPedido;
    
    // Campo que almacena cuándo se debe entregar el pedido
    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;
    
    // Validación para que el estado no esté vacío
    @NotBlank(message = "El estado no puede estar vacío")
    // Campo que almacena el estado del pedido (PENDIENTE, PROCESANDO, ENTREGADO, etc.)
    private String estado;
    
    // @OneToMany define una relación uno-a-muchos (un pedido puede tener muchos artículos)
    // mappedBy indica que la relación es manejada por el campo "pedido" en PedidoArticulo
    // cascade = CascadeType.ALL significa que las operaciones se propagan a los hijos
    // fetch = FetchType.LAZY significa que se cargan solo cuando se necesitan
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @JsonIgnore evita bucles infinitos al serializar a JSON
    @JsonIgnore
    // Lista que contiene todos los artículos asociados a este pedido
    private List<PedidoArticulo> pedidoArticulos = new ArrayList<>();
    
    // Campo que almacena el total calculado del pedido
    @Column(name = "total")
    private Double total;
    
    // Campo opcional para almacenar observaciones sobre el pedido
    @Column(name = "observaciones")
    private String observaciones;
    
    // Constructor vacío necesario para JPA
    public Pedido() {
        // Establecemos la fecha actual como fecha de creación del pedido
        this.fechaPedido = LocalDateTime.now();
        // Por defecto, el pedido se crea en estado PENDIENTE
        this.estado = "PENDIENTE";
    }
    
    // Constructor con parámetros para crear un pedido con datos específicos
    public Pedido(Cliente cliente, LocalDateTime fechaEntrega, String estado, String observaciones) {
        // Asignamos el cliente que realizó el pedido
        this.cliente = cliente;
        // La fecha de pedido siempre es la fecha actual
        this.fechaPedido = LocalDateTime.now();
        // Asignamos la fecha de entrega solicitada
        this.fechaEntrega = fechaEntrega;
        // Si no se especifica estado, usamos PENDIENTE por defecto
        this.estado = estado != null ? estado : "PENDIENTE";
        // Asignamos las observaciones del pedido
        this.observaciones = observaciones;
    }
    
    // Getters y Setters - Métodos para acceder y modificar los campos
    
    // Método getter para obtener el ID único del pedido
    public Long getId() { return id; }
    // Método setter para establecer el ID del pedido
    public void setId(Long id) { this.id = id; }
    
    // Método getter para obtener el cliente que realizó el pedido
    public Cliente getCliente() { return cliente; }
    // Método setter para establecer el cliente del pedido
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    // Método getter para obtener la fecha en que se creó el pedido
    public LocalDateTime getFechaPedido() { return fechaPedido; }
    // Método setter para establecer la fecha del pedido
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }
    
    // Método getter para obtener la fecha de entrega esperada
    public LocalDateTime getFechaEntrega() { return fechaEntrega; }
    // Método setter para establecer la fecha de entrega
    public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    
    // Método getter para obtener el estado actual del pedido
    public String getEstado() { return estado; }
    // Método setter para cambiar el estado del pedido
    public void setEstado(String estado) { this.estado = estado; }
    
    // Método getter para obtener la lista de artículos del pedido
    public List<PedidoArticulo> getPedidoArticulos() { return pedidoArticulos; }
    // Método setter para establecer la lista de artículos del pedido
    public void setPedidoArticulos(List<PedidoArticulo> pedidoArticulos) { this.pedidoArticulos = pedidoArticulos; }
    
    // Método getter para obtener el total del pedido
    public Double getTotal() { return total; }
    // Método setter para establecer el total del pedido
    public void setTotal(Double total) { this.total = total; }
    
    // Método getter para obtener las observaciones del pedido
    public String getObservaciones() { return observaciones; }
    // Método setter para establecer las observaciones del pedido
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    // Método para calcular automáticamente el total del pedido
    public void calcularTotal() {
        // Verificamos si hay artículos en el pedido
        if (pedidoArticulos != null && !pedidoArticulos.isEmpty()) {
            // Usamos streams para sumar todos los subtotales de los artículos
            this.total = pedidoArticulos.stream()
                    // Filtramos solo los artículos que tienen subtotal no nulo
                    .filter(pa -> pa.getSubtotal() != null)
                    // Convertimos cada PedidoArticulo a su valor de subtotal
                    .mapToDouble(PedidoArticulo::getSubtotal)
                    // Sumamos todos los subtotales
                    .sum();
        } else {
            // Si no hay artículos, el total es 0
            this.total = 0.0;
        }
    }
    
    // Método público para recalcular el total manualmente
    public void recalcularTotal() {
        // Llama al método interno de cálculo
        calcularTotal();
    }
    
    // Método para agregar un artículo al pedido de forma práctica
    public void agregarArticulo(Articulo articulo, Integer cantidad) {
        // Creamos un nuevo PedidoArticulo con los datos proporcionados
        PedidoArticulo pedidoArticulo = new PedidoArticulo(this, articulo, cantidad, articulo.getPrecio());
        // Agregamos el artículo a la lista del pedido
        this.pedidoArticulos.add(pedidoArticulo);
        // Recalculamos el total del pedido
        calcularTotal();
    }
    
    // @PrePersist se ejecuta antes de insertar el pedido en la base de datos
    @PrePersist
    // @PreUpdate se ejecuta antes de actualizar el pedido en la base de datos
    @PreUpdate
    // Método que se ejecuta automáticamente antes de guardar o actualizar
    public void preSave() {
        // Calculamos el total antes de guardar para asegurar consistencia
        calcularTotal();
    }
}


