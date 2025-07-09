package com.proyecto.talento.carrito.repository;

import com.proyecto.talento.carrito.model.PedidoArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoArticuloRepository extends JpaRepository<PedidoArticulo, Long> {
    
    // Buscar artículos de un pedido específico
    List<PedidoArticulo> findByPedidoId(Long pedidoId);
    
    // Buscar pedidos que contengan un artículo específico
    List<PedidoArticulo> findByArticuloId(Long articuloId);
    
    // Eliminar artículos de un pedido específico
    void deleteByPedidoId(Long pedidoId);
    
    // Calcular el total de un pedido
    @Query("SELECT SUM(pa.subtotal) FROM PedidoArticulo pa WHERE pa.pedido.id = :pedidoId")
    Double calcularTotalPedido(@Param("pedidoId") Long pedidoId);
} 

