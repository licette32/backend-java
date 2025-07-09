
// Paquete que contiene las interfaces de acceso a datos
package com.ejemplo.articulos.repository;

import com.ejemplo.articulos.model.Articulo;
import java.util.List;
import java.util.Optional;

// Interfaz que define operaciones de acceso a datos para artículos
public interface ArticuloRepository {
    List<Articulo> findAll(); // Método para obtener todos los artículos
    Optional<Articulo> findById(Long id); // Método para obtener un artículo por ID
    Articulo save(Articulo articulo); // Método para guardar o actualizar un artículo
    void deleteById(Long id); // Método para eliminar un artículo por ID
}
