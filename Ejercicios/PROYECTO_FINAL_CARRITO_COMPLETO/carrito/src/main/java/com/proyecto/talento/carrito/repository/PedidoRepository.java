package com.proyecto.talento.carrito.repository;

import com.proyecto.talento.carrito.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos por cliente ID
    List<Pedido> findByClienteId(Long clienteId);
    
    // Buscar pedidos por email del cliente
    @Query("SELECT p FROM Pedido p WHERE p.cliente.email = :email")
    List<Pedido> findByClienteEmail(@Param("email") String email);
    
    // Buscar pedidos por estado
    List<Pedido> findByEstado(String estado);
    
    // Buscar pedidos por cliente y estado
    List<Pedido> findByClienteIdAndEstado(Long clienteId, String estado);
    
    // Buscar pedidos entre fechas
    @Query("SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN :fechaInicio AND :fechaFin")
    List<Pedido> findByFechaPedidoBetween(@Param("fechaInicio") LocalDateTime fechaInicio, 
                                         @Param("fechaFin") LocalDateTime fechaFin);
    
    // Buscar pedidos por cliente entre fechas
    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId AND p.fechaPedido BETWEEN :fechaInicio AND :fechaFin")
    List<Pedido> findByClienteIdAndFechaPedidoBetween(@Param("clienteId") Long clienteId,
                                                     @Param("fechaInicio") LocalDateTime fechaInicio,
                                                     @Param("fechaFin") LocalDateTime fechaFin);
    
    // Contar pedidos por cliente
    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.cliente.id = :clienteId")
    Long countByClienteId(@Param("clienteId") Long clienteId);
    
    // Buscar pedidos ordenados por fecha descendente
    @Query("SELECT p FROM Pedido p ORDER BY p.fechaPedido DESC")
    List<Pedido> findAllOrderByFechaPedidoDesc();
}


