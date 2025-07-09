
// Paquete del servicio de implementación
package com.ejemplo.articulos.service;

import com.ejemplo.articulos.model.Articulo;
import com.ejemplo.articulos.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Anotación que indica que esta clase es un servicio de Spring
@Service
public class ArticuloServiceImpl implements ArticuloService {

    // Inyección del repositorio que maneja los datos
    private final ArticuloRepository articuloRepository;

    // Constructor con @Autowired para inyectar dependencias
    @Autowired
    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    // Implementación para listar artículos
    @Override
    public List<Articulo> listarArticulos() {
        return articuloRepository.findAll();
    }

    // Implementación para buscar artículo por ID
    @Override
    public Optional<Articulo> obtenerArticuloPorId(Long id) {
        return articuloRepository.findById(id);
    }

    // Implementación para guardar un nuevo artículo
    @Override
    public Articulo guardarArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    // Implementación para actualizar artículo
    @Override
    public Articulo actualizarArticulo(Long id, Articulo articulo) {
        articulo.setId(id); // Setea el ID existente
        return articuloRepository.save(articulo);
    }

    // Implementación para eliminar artículo
    @Override
    public void eliminarArticulo(Long id) {
        articuloRepository.deleteById(id);
    }
}
