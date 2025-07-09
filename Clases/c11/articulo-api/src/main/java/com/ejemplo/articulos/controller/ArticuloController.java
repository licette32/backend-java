
// Paquete del controlador
package com.ejemplo.articulos.controller;

import com.ejemplo.articulos.model.Articulo;
import com.ejemplo.articulos.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST que maneja las peticiones HTTP
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    // Inyección del servicio
    private final ArticuloService articuloService;

    // Constructor con inyección
    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    // GET /api/articulos -> lista todos los artículos
    @GetMapping
    public List<Articulo> listar() {
        return articuloService.listarArticulos();
    }

    // GET /api/articulos/{id} -> busca un artículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
                .map(ResponseEntity::ok) // Devuelve 200 si lo encuentra
                .orElse(ResponseEntity.notFound().build()); // Devuelve 404 si no
    }

    // POST /api/articulos -> crea un nuevo artículo
    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo) {
        return articuloService.guardarArticulo(articulo);
    }

    // PUT /api/articulos/{id} -> actualiza un artículo existente
    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no existe, 404
        }
        return ResponseEntity.ok(articuloService.actualizarArticulo(id, articulo)); // Si existe, actualiza y 200
    }

    // DELETE /api/articulos/{id} -> elimina un artículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no existe, 404
        }
        articuloService.eliminarArticulo(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
