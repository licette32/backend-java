
package com.ejemplo.articulos.service;

import com.ejemplo.articulos.model.Articulo;
import java.util.List;
import java.util.Optional;

// Interfaz que define el contrato del servicio
public interface ArticuloService {
    List<Articulo> listarArticulos();
    Optional<Articulo> obtenerArticuloPorId(Long id);
    Articulo guardarArticulo(Articulo articulo);
    Articulo actualizarArticulo(Long id, Articulo articulo);
    void eliminarArticulo(Long id);
}
