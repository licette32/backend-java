package com.proyecto.talento.carrito.service;

import com.proyecto.talento.carrito.model.Articulo;
import com.proyecto.talento.carrito.model.Cliente;
import com.proyecto.talento.carrito.model.Pedido;
import com.proyecto.talento.carrito.model.PedidoArticulo;
import com.proyecto.talento.carrito.repository.PedidoRepository;
import com.proyecto.talento.carrito.repository.PedidoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoArticuloRepository pedidoArticuloRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ArticuloService articuloService;

    // Obtener todos los pedidos
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAllOrderByFechaPedidoDesc();
    }

    // Obtener pedido por ID
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // Buscar pedidos por cliente email
    public List<Pedido> buscarPedidosPorClienteEmail(String email) {
        return pedidoRepository.findByClienteEmail(email);
    }

    // Buscar pedidos por cliente ID
    public List<Pedido> buscarPedidosPorClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    // Buscar pedidos por estado
    public List<Pedido> buscarPedidosPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    // Buscar pedidos entre fechas
    public List<Pedido> buscarPedidosEntreFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin);
    }

    // Crear un nuevo pedido
    @Transactional
    public Pedido crearPedido(String emailCliente, LocalDateTime fechaEntrega, String observaciones) {
        // Buscar o crear cliente
        Cliente cliente = clienteService.buscarClientePorEmail(emailCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con email: " + emailCliente));

        // Crear el pedido
        Pedido pedido = new Pedido(cliente, fechaEntrega, "PENDIENTE", observaciones);
        
        return pedidoRepository.save(pedido);
    }

    // Crear pedido con artículos
    @Transactional
    public Pedido crearPedidoConArticulos(String emailCliente, LocalDateTime fechaEntrega, 
                                         String observaciones, List<PedidoArticuloDTO> articulosDto) {
        // Crear el pedido base
        Pedido pedido = crearPedido(emailCliente, fechaEntrega, observaciones);

        // Agregar artículos al pedido
        if (articulosDto != null && !articulosDto.isEmpty()) {
            for (PedidoArticuloDTO articuloDto : articulosDto) {
                agregarArticuloAPedido(pedido.getId(), articuloDto.getArticuloId(), articuloDto.getCantidad());
            }
            
            // Actualizar el total final
            pedido = actualizarTotalPedido(pedido.getId());
        }

        return pedido;
    }

    // Agregar artículo a un pedido existente
    @Transactional
    public PedidoArticulo agregarArticuloAPedido(Long pedidoId, Long articuloId, Integer cantidad) {
        // Obtener el pedido
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        // Obtener el artículo
        Articulo articulo = articuloService.obtenerArticuloPorId(articuloId)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + articuloId));

        // Verificar stock suficiente
        if (!articuloService.tieneStockSuficiente(articuloId, cantidad)) {
            throw new RuntimeException("Stock insuficiente para el artículo: " + articulo.getNombre());
        }

        // Crear la relación pedido-artículo
        PedidoArticulo pedidoArticulo = new PedidoArticulo(pedido, articulo, cantidad, articulo.getPrecio());
        
        // Guardar el PedidoArticulo primero
        pedidoArticulo = pedidoArticuloRepository.save(pedidoArticulo);

        // Reducir stock del artículo
        articuloService.reducirStock(articuloId, cantidad);

        // Agregar a la lista del pedido y recalcular total
        pedido.getPedidoArticulos().add(pedidoArticulo);
        pedido.recalcularTotal();
        
        // Guardar el pedido con el total actualizado
        pedidoRepository.save(pedido);

        return pedidoArticulo;
    }

    // Actualizar un pedido existente
    @Transactional
    public Pedido actualizarPedido(Long id, String estado, LocalDateTime fechaEntrega, String observaciones) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    if (estado != null) {
                        pedido.setEstado(estado);
                    }
                    if (fechaEntrega != null) {
                        pedido.setFechaEntrega(fechaEntrega);
                    }
                    if (observaciones != null) {
                        pedido.setObservaciones(observaciones);
                    }
                    
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    // Cambiar estado de un pedido
    @Transactional
    public Pedido cambiarEstadoPedido(Long id, String nuevoEstado) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    String estadoAnterior = pedido.getEstado();
                    pedido.setEstado(nuevoEstado);
                    
                    // Si se cancela el pedido, devolver stock
                    if ("CANCELADO".equals(nuevoEstado) && !"CANCELADO".equals(estadoAnterior)) {
                        devolverStockPedido(pedido);
                    }
                    
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    // Eliminar un pedido
    @Transactional
    public void eliminarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));

        // Devolver stock antes de eliminar
        devolverStockPedido(pedido);

        // Eliminar relaciones pedido-artículo
        pedidoArticuloRepository.deleteByPedidoId(id);

        // Eliminar el pedido
        pedidoRepository.deleteById(id);
    }

    // Devolver stock de un pedido
    private void devolverStockPedido(Pedido pedido) {
        for (PedidoArticulo pedidoArticulo : pedido.getPedidoArticulos()) {
            articuloService.aumentarStock(pedidoArticulo.getArticulo().getId(), 
                                        pedidoArticulo.getCantidad());
        }
    }

    // Obtener artículos de un pedido
    public List<PedidoArticulo> obtenerArticulosDePedido(Long pedidoId) {
        return pedidoArticuloRepository.findByPedidoId(pedidoId);
    }

    // Contar pedidos por cliente
    public Long contarPedidosPorCliente(Long clienteId) {
        return pedidoRepository.countByClienteId(clienteId);
    }

    // Calcular total de un pedido
    public Double calcularTotalPedido(Long pedidoId) {
        return pedidoArticuloRepository.calcularTotalPedido(pedidoId);
    }

    // Actualizar total del pedido manualmente
    @Transactional
    public Pedido actualizarTotalPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));
        
        // Recalcular total
        pedido.recalcularTotal();
        
        // Guardar pedido actualizado
        return pedidoRepository.save(pedido);
    }

    // Clase DTO para transferir datos de artículos del pedido
    public static class PedidoArticuloDTO {
        private Long articuloId;
        private Integer cantidad;

        public PedidoArticuloDTO() {}

        public PedidoArticuloDTO(Long articuloId, Integer cantidad) {
            this.articuloId = articuloId;
            this.cantidad = cantidad;
        }

        public Long getArticuloId() { return articuloId; }
        public void setArticuloId(Long articuloId) { this.articuloId = articuloId; }
        
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    }
} 

