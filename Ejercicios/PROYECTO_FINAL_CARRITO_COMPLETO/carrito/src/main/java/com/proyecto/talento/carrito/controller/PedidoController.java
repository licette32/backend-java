package com.proyecto.talento.carrito.controller;

import com.proyecto.talento.carrito.model.Pedido;
import com.proyecto.talento.carrito.model.PedidoArticulo;
import com.proyecto.talento.carrito.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        try {
            Optional<Pedido> pedido = pedidoService.obtenerPedidoPorId(id);
            return pedido.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar pedidos por email del cliente
    @GetMapping("/cliente/email/{email}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorClienteEmail(@PathVariable String email) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPedidosPorClienteEmail(email);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar pedidos por cliente ID
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorClienteId(@PathVariable Long clienteId) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPedidosPorClienteId(clienteId);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar pedidos por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorEstado(@PathVariable String estado) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPedidosPorEstado(estado);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar pedidos entre fechas
    @GetMapping("/fecha")
    public ResponseEntity<List<Pedido>> buscarPedidosEntreFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPedidosEntreFechas(fechaInicio, fechaFin);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear un nuevo pedido básico
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody CrearPedidoRequest request) {
        try {
            Pedido nuevoPedido = pedidoService.crearPedido(
                request.getEmailCliente(), 
                request.getFechaEntrega(), 
                request.getObservaciones()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear pedido con artículos
    @PostMapping("/con-articulos")
    public ResponseEntity<Pedido> crearPedidoConArticulos(@Valid @RequestBody CrearPedidoConArticulosRequest request) {
        try {
            Pedido nuevoPedido = pedidoService.crearPedidoConArticulos(
                request.getEmailCliente(),
                request.getFechaEntrega(),
                request.getObservaciones(),
                request.getArticulos()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Agregar artículo a pedido existente
    @PostMapping("/{pedidoId}/articulos")
    public ResponseEntity<PedidoArticulo> agregarArticuloAPedido(
            @PathVariable Long pedidoId,
            @Valid @RequestBody AgregarArticuloRequest request) {
        try {
            PedidoArticulo pedidoArticulo = pedidoService.agregarArticuloAPedido(
                pedidoId, 
                request.getArticuloId(), 
                request.getCantidad()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoArticulo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar un pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarPedidoRequest request) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarPedido(
                id, 
                request.getEstado(), 
                request.getFechaEntrega(), 
                request.getObservaciones()
            );
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Cambiar estado de un pedido
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstadoPedido(
            @PathVariable Long id,
            @RequestParam String estado) {
        try {
            Pedido pedidoActualizado = pedidoService.cambiarEstadoPedido(id, estado);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        try {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener artículos de un pedido
    @GetMapping("/{id}/articulos")
    public ResponseEntity<List<ArticuloPedidoDTO>> obtenerArticulosDePedido(@PathVariable Long id) {
        try {
            List<PedidoArticulo> pedidoArticulos = pedidoService.obtenerArticulosDePedido(id);
            List<ArticuloPedidoDTO> articulosDTO = pedidoArticulos.stream()
                .map(pa -> new ArticuloPedidoDTO(
                    pa.getId(),
                    pa.getArticulo().getId(),
                    pa.getArticulo().getNombre(),
                    pa.getArticulo().getDescripcion(),
                    pa.getCantidad(),
                    pa.getPrecioUnitario(),
                    pa.getSubtotal()
                ))
                .toList();
            return ResponseEntity.ok(articulosDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener pedido con detalles completos
    @GetMapping("/{id}/detalles")
    public ResponseEntity<Map<String, Object>> obtenerPedidoConDetalles(@PathVariable Long id) {
        try {
            Optional<Pedido> pedidoOpt = pedidoService.obtenerPedidoPorId(id);
            if (pedidoOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Pedido pedido = pedidoOpt.get();
            List<PedidoArticulo> articulos = pedidoService.obtenerArticulosDePedido(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("pedido", pedido);
            response.put("articulos", articulos.stream()
                .map(pa -> new ArticuloPedidoDTO(
                    pa.getId(),
                    pa.getArticulo().getId(),
                    pa.getArticulo().getNombre(),
                    pa.getArticulo().getDescripcion(),
                    pa.getCantidad(),
                    pa.getPrecioUnitario(),
                    pa.getSubtotal()
                ))
                .toList());
            response.put("cantidadArticulos", articulos.size());
            response.put("totalCalculado", pedidoService.calcularTotalPedido(id));
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Contar pedidos por cliente
    @GetMapping("/cliente/{clienteId}/count")
    public ResponseEntity<Long> contarPedidosPorCliente(@PathVariable Long clienteId) {
        try {
            Long count = pedidoService.contarPedidosPorCliente(clienteId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Calcular total de un pedido
    @GetMapping("/{id}/total")
    public ResponseEntity<Double> calcularTotalPedido(@PathVariable Long id) {
        try {
            Double total = pedidoService.calcularTotalPedido(id);
            return ResponseEntity.ok(total != null ? total : 0.0);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar total del pedido
    @PutMapping("/{id}/actualizar-total")
    public ResponseEntity<Pedido> actualizarTotalPedido(@PathVariable Long id) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarTotalPedido(id);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Agregar artículo con parámetros en URL
    @PostMapping("/{pedidoId}/articulos/{articuloId}")
    public ResponseEntity<Map<String, Object>> agregarArticuloAPedidoConParams(
            @PathVariable Long pedidoId,
            @PathVariable Long articuloId,
            @RequestParam Integer cantidad) {
        try {
            PedidoArticulo pedidoArticulo = pedidoService.agregarArticuloAPedido(pedidoId, articuloId, cantidad);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("pedidoArticulo", pedidoArticulo);
            response.put("message", "Artículo agregado al pedido exitosamente");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint de debug para desarrollo
    @GetMapping("/{id}/debug")
    public ResponseEntity<Map<String, Object>> debugPedido(@PathVariable Long id) {
        try {
            Optional<Pedido> pedidoOpt = pedidoService.obtenerPedidoPorId(id);
            if (pedidoOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Pedido pedido = pedidoOpt.get();
            List<PedidoArticulo> articulos = pedidoService.obtenerArticulosDePedido(id);
            
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("pedidoId", pedido.getId());
            debugInfo.put("clienteId", pedido.getCliente().getId());
            debugInfo.put("clienteEmail", pedido.getCliente().getEmail());
            debugInfo.put("estado", pedido.getEstado());
            debugInfo.put("fechaPedido", pedido.getFechaPedido());
            debugInfo.put("fechaEntrega", pedido.getFechaEntrega());
            debugInfo.put("totalPedido", pedido.getTotal());
            debugInfo.put("cantidadArticulos", articulos.size());
            debugInfo.put("articulosDetalle", articulos.stream()
                .map(pa -> {
                    Map<String, Object> articuloInfo = new HashMap<>();
                    articuloInfo.put("id", pa.getId());
                    articuloInfo.put("articuloId", pa.getArticulo().getId());
                    articuloInfo.put("articuloNombre", pa.getArticulo().getNombre());
                    articuloInfo.put("cantidad", pa.getCantidad());
                    articuloInfo.put("precioUnitario", pa.getPrecioUnitario());
                    articuloInfo.put("subtotal", pa.getSubtotal());
                    return articuloInfo;
                })
                .toList());
            debugInfo.put("totalCalculadoManualmente", articulos.stream()
                .mapToDouble(pa -> pa.getSubtotal() != null ? pa.getSubtotal() : 0.0)
                .sum());
                
            return ResponseEntity.ok(debugInfo);
        } catch (Exception e) {
            Map<String, Object> errorInfo = new HashMap<>();
            errorInfo.put("error", e.getMessage());
            errorInfo.put("stackTrace", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
        }
    }

    // Clases DTO para las respuestas y requests
    public static class ArticuloPedidoDTO {
        private Long id;
        private Long articuloId;
        private String articuloNombre;
        private String articuloDescripcion;
        private Integer cantidad;
        private Double precioUnitario;
        private Double subtotal;

        public ArticuloPedidoDTO(Long id, Long articuloId, String articuloNombre, String articuloDescripcion, 
                                Integer cantidad, Double precioUnitario, Double subtotal) {
            this.id = id;
            this.articuloId = articuloId;
            this.articuloNombre = articuloNombre;
            this.articuloDescripcion = articuloDescripcion;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.subtotal = subtotal;
        }

        // Getters y Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Long getArticuloId() { return articuloId; }
        public void setArticuloId(Long articuloId) { this.articuloId = articuloId; }

        public String getArticuloNombre() { return articuloNombre; }
        public void setArticuloNombre(String articuloNombre) { this.articuloNombre = articuloNombre; }

        public String getArticuloDescripcion() { return articuloDescripcion; }
        public void setArticuloDescripcion(String articuloDescripcion) { this.articuloDescripcion = articuloDescripcion; }

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

        public Double getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

        public Double getSubtotal() { return subtotal; }
        public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    }

    // Clase DTO para crear pedido básico
    public static class CrearPedidoRequest {
        private String emailCliente;
        private LocalDateTime fechaEntrega;
        private String observaciones;

        public String getEmailCliente() { return emailCliente; }
        public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }

        public LocalDateTime getFechaEntrega() { return fechaEntrega; }
        public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }

        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    }

    // Clase DTO para crear pedido con artículos
    public static class CrearPedidoConArticulosRequest {
        private String emailCliente;
        private LocalDateTime fechaEntrega;
        private String observaciones;
        private List<PedidoService.PedidoArticuloDTO> articulos;

        public String getEmailCliente() { return emailCliente; }
        public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }

        public LocalDateTime getFechaEntrega() { return fechaEntrega; }
        public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }

        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

        public List<PedidoService.PedidoArticuloDTO> getArticulos() { return articulos; }
        public void setArticulos(List<PedidoService.PedidoArticuloDTO> articulos) { this.articulos = articulos; }
    }

    // Clase DTO para agregar artículo
    public static class AgregarArticuloRequest {
        private Long articuloId;
        private Integer cantidad;

        public Long getArticuloId() { return articuloId; }
        public void setArticuloId(Long articuloId) { this.articuloId = articuloId; }

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    }

    // Clase DTO para actualizar pedido
    public static class ActualizarPedidoRequest {
        private String estado;
        private LocalDateTime fechaEntrega;
        private String observaciones;

        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }

        public LocalDateTime getFechaEntrega() { return fechaEntrega; }
        public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }

        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    }
} 