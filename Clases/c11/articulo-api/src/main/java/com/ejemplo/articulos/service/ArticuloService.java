
// Paquete que contiene las interfaces del servicio
package com.ejemplo.articulos.service;

import com.ejemplo.articulos.model.Articulo;
import java.util.List;
import java.util.Optional;

// Interfaz que define los métodos del servicio de artículos
public interface ArticuloService {
    List<Articulo> listarArticulos(); // Lista todos los artículos
    Optional<Articulo> obtenerArticuloPorId(Long id); // Obtiene un artículo por ID
    Articulo guardarArticulo(Articulo articulo); // Guarda un nuevo artículo
    Articulo actualizarArticulo(Long id, Articulo articulo); // Actualiza un artículo existente
    void eliminarArticulo(Long id); // Elimina un artículo
}
