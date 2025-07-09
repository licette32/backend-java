// Declaramos el paquete donde se encuentra esta clase de servicio
package com.proyecto.talento.carrito.service;
// Importamos el modelo Articulo para poder trabajar con él
import com.proyecto.talento.carrito.model.Articulo;
// Importamos el repositorio de artículos para acceder a los datos
import com.proyecto.talento.carrito.repository.ArticuloRepository;
// Importamos la anotación @Autowired para inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;
// Importamos @Service para marcar esta clase como un servicio de Spring
import org.springframework.stereotype.Service;

// Importamos List para manejar listas de artículos
import java.util.List;
// Importamos Optional para manejar valores que pueden estar presentes o no
import java.util.Optional;

// @Service marca esta clase como un servicio de Spring (capa de lógica de negocio)
@Service
// Clase que contiene la lógica de negocio para los artículos
public class ArticuloService {

    // @Autowired inyecta automáticamente el repositorio de artículos
    @Autowired
    // Repositorio que usamos para acceder a los datos de artículos en la base de datos
    private ArticuloRepository articuloRepository;

    // Método para obtener todos los artículos de la base de datos
    public List<Articulo> obtenerTodosLosArticulos() {
        // Llama al método findAll() del repositorio que devuelve todos los artículos
        return articuloRepository.findAll();
    }

    // Método para obtener solo los artículos que están marcados como activos
    public List<Articulo> obtenerArticulosActivos() {
        // Llama al método personalizado que busca artículos con activo=true
        return articuloRepository.findByActivoTrue();
    }

    // Método para obtener artículos activos que tienen stock disponible
    public List<Articulo> obtenerArticulosConStock() {
        // Llama al método que busca artículos activos con stock mayor a 0
        return articuloRepository.findByActivoTrueAndStockGreaterThan();
    }

    // Método para buscar un artículo específico por su ID
    public Optional<Articulo> obtenerArticuloPorId(Long id) {
        // findById devuelve un Optional porque el artículo puede no existir
        // Optional nos ayuda a manejar casos donde no se encuentra el artículo
        return articuloRepository.findById(id);
    }

    // Método para buscar artículos que contengan un texto específico en el nombre
    public List<Articulo> buscarArticulosPorNombre(String nombre) {
        // findByNombreContaining busca artículos cuyo nombre contenga el texto dado
        // Por ejemplo, si busco "cam", encontrará "camiseta", "pantalón de camuflaje", etc.
        return articuloRepository.findByNombreContaining(nombre);
    }

    // Método para buscar artículos dentro de un rango de precios
    public List<Articulo> buscarArticulosPorRangoPrecios(Double precioMin, Double precioMax) {
        // findByPrecioBetween busca artículos cuyo precio esté entre precioMin y precioMax
        // Por ejemplo, para buscar productos entre $10 y $50
        return articuloRepository.findByPrecioBetween(precioMin, precioMax);
    }

    // Método para obtener artículos que tienen poco stock (útil para alertas de inventario)
    public List<Articulo> obtenerArticulosConStockBajo(Integer stockMinimo) {
        // findByStockLessThan busca artículos cuyo stock sea menor al mínimo especificado
        // Por ejemplo, para encontrar productos con menos de 5 unidades
        return articuloRepository.findByStockLessThan(stockMinimo);
    }

    // Método para obtener todos los artículos ordenados por precio de menor a mayor
    public List<Articulo> obtenerArticulosOrdenadosPorPrecioAsc() {
        // Llama al método personalizado que ordena por precio ascendente
        return articuloRepository.findAllOrderByPrecioAsc();
    }

    // Método para obtener todos los artículos ordenados por precio de mayor a menor
    public List<Articulo> obtenerArticulosOrdenadosPorPrecioDesc() {
        // Llama al método personalizado que ordena por precio descendente
        return articuloRepository.findAllOrderByPrecioDesc();
    }

    // Método para crear un nuevo artículo en la base de datos
    public Articulo crearArticulo(Articulo articulo) {
        // El método save() guarda el artículo en la base de datos
        // Si el artículo es nuevo (sin ID), lo inserta; si ya existe, lo actualiza
        return articuloRepository.save(articulo);
    }

    // Método para actualizar un artículo existente
    public Articulo actualizarArticulo(Long id, Articulo articuloActualizado) {
        // Primero buscamos el artículo por ID
        return articuloRepository.findById(id)
                // Si encontramos el artículo, lo mapeamos (transformamos)
                .map(articulo -> {
                    // Actualizamos cada campo del artículo existente con los nuevos valores
                    articulo.setNombre(articuloActualizado.getNombre());
                    articulo.setPrecio(articuloActualizado.getPrecio());
                    articulo.setDescripcion(articuloActualizado.getDescripcion());
                    articulo.setStock(articuloActualizado.getStock());
                    articulo.setActivo(articuloActualizado.getActivo());
                    
                    // Guardamos el artículo actualizado en la base de datos
                    return articuloRepository.save(articulo);
                })
                // Si no encontramos el artículo, lanzamos una excepción
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + id));
    }

    // Método para eliminar un artículo de forma lógica (no lo borra, solo lo desactiva)
    public void eliminarArticulo(Long id) {
        // Buscamos el artículo por ID
        articuloRepository.findById(id)
                // Si lo encontramos, lo mapeamos
                .map(articulo -> {
                    // Marcamos el artículo como inactivo (eliminación lógica)
                    // Esto permite mantener el historial de ventas y datos
                    articulo.setActivo(false);
                    // Guardamos el cambio en la base de datos
                    return articuloRepository.save(articulo);
                })
                // Si no encontramos el artículo, lanzamos una excepción
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + id));
    }

    // Método para eliminar un artículo físicamente de la base de datos
    public void eliminarArticuloFisicamente(Long id) {
        // Verificamos si el artículo existe antes de intentar eliminarlo
        if (!articuloRepository.existsById(id)) {
            // Si no existe, lanzamos una excepción
            throw new RuntimeException("Artículo no encontrado con ID: " + id);
        }
        // Eliminamos el artículo completamente de la base de datos
        // CUIDADO: esta acción no se puede deshacer
        articuloRepository.deleteById(id);
    }

    // Método para actualizar el stock de un artículo estableciendo un nuevo valor
    public Articulo actualizarStock(Long id, Integer nuevoStock) {
        // Buscamos el artículo por ID
        return articuloRepository.findById(id)
                // Si lo encontramos, actualizamos su stock
                .map(articulo -> {
                    // Establecemos el nuevo stock
                    articulo.setStock(nuevoStock);
                    // Guardamos el cambio en la base de datos
                    return articuloRepository.save(articulo);
                })
                // Si no encontramos el artículo, lanzamos una excepción
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + id));
    }

    // Método para reducir el stock de un artículo (usado cuando se vende)
    public Articulo reducirStock(Long id, Integer cantidadAReducir) {
        // Buscamos el artículo por ID
        return articuloRepository.findById(id)
                // Si lo encontramos, procesamos la reducción
                .map(articulo -> {
                    // Obtenemos el stock actual del artículo
                    int stockActual = articulo.getStock();
                    // Verificamos si tenemos suficiente stock para la operación
                    if (stockActual < cantidadAReducir) {
                        // Si no hay suficiente stock, lanzamos una excepción
                        throw new RuntimeException("Stock insuficiente. Stock actual: " + stockActual);
                    }
                    // Calculamos el nuevo stock restando la cantidad a reducir
                    articulo.setStock(stockActual - cantidadAReducir);
                    // Guardamos el cambio en la base de datos
                    return articuloRepository.save(articulo);
                })
                // Si no encontramos el artículo, lanzamos una excepción
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + id));
    }

    // Método para aumentar el stock de un artículo (usado cuando llega mercancía)
    public Articulo aumentarStock(Long id, Integer cantidadAAumentar) {
        // Buscamos el artículo por ID
        return articuloRepository.findById(id)
                // Si lo encontramos, aumentamos su stock
                .map(articulo -> {
                    // Sumamos la cantidad a aumentar al stock actual
                    articulo.setStock(articulo.getStock() + cantidadAAumentar);
                    // Guardamos el cambio en la base de datos
                    return articuloRepository.save(articulo);
                })
                // Si no encontramos el artículo, lanzamos una excepción
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + id));
    }

    // Método para verificar si un artículo tiene stock suficiente para una operación
    public boolean tieneStockSuficiente(Long id, Integer cantidadRequerida) {
        // Buscamos el artículo por ID
        return articuloRepository.findById(id)
                // Si lo encontramos, comparamos el stock actual con la cantidad requerida
                .map(articulo -> articulo.getStock() >= cantidadRequerida)
                // Si no encontramos el artículo, devolvemos false
                .orElse(false);
    }
}

