package com.proyecto.talento.carrito.controller;

import com.proyecto.talento.carrito.model.Articulo;
import com.proyecto.talento.carrito.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    // Obtener todos los artículos
    @GetMapping
    public ResponseEntity<List<Articulo>> obtenerTodosLosArticulos() {
        try {
            List<Articulo> articulos = articuloService.obtenerTodosLosArticulos();
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener artículos activos
    @GetMapping("/activos")
    public ResponseEntity<List<Articulo>> obtenerArticulosActivos() {
        try {
            List<Articulo> articulos = articuloService.obtenerArticulosActivos();
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener artículos con stock disponible
    @GetMapping("/disponibles")
    public ResponseEntity<List<Articulo>> obtenerArticulosConStock() {
        try {
            List<Articulo> articulos = articuloService.obtenerArticulosConStock();
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener artículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerArticuloPorId(@PathVariable Long id) {
        try {
            Optional<Articulo> articulo = articuloService.obtenerArticuloPorId(id);
            return articulo.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar artículos por nombre
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Articulo>> buscarArticulosPorNombre(@PathVariable String nombre) {
        try {
            List<Articulo> articulos = articuloService.buscarArticulosPorNombre(nombre);
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar artículos por rango de precios
    @GetMapping("/precio")
    public ResponseEntity<List<Articulo>> buscarArticulosPorRangoPrecios(
            @RequestParam Double min, @RequestParam Double max) {
        try {
            List<Articulo> articulos = articuloService.buscarArticulosPorRangoPrecios(min, max);
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener artículos con stock bajo
    @GetMapping("/stock-bajo/{stockMinimo}")
    public ResponseEntity<List<Articulo>> obtenerArticulosConStockBajo(@PathVariable Integer stockMinimo) {
        try {
            List<Articulo> articulos = articuloService.obtenerArticulosConStockBajo(stockMinimo);
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener artículos ordenados por precio ascendente
    @GetMapping("/ordenar/precio-asc")
    public ResponseEntity<List<Articulo>> obtenerArticulosOrdenadosPorPrecioAsc() {
        try {
            List<Articulo> articulos = articuloService.obtenerArticulosOrdenadosPorPrecioAsc();
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener artículos ordenados por precio descendente
    @GetMapping("/ordenar/precio-desc")
    public ResponseEntity<List<Articulo>> obtenerArticulosOrdenadosPorPrecioDesc() {
        try {
            List<Articulo> articulos = articuloService.obtenerArticulosOrdenadosPorPrecioDesc();
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear un nuevo artículo
    @PostMapping
    public ResponseEntity<Articulo> crearArticulo(@Valid @RequestBody Articulo articulo) {
        try {
            Articulo nuevoArticulo = articuloService.crearArticulo(articulo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArticulo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar un artículo existente
    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizarArticulo(@PathVariable Long id, 
                                                     @Valid @RequestBody Articulo articulo) {
        try {
            Articulo articuloActualizado = articuloService.actualizarArticulo(id, articulo);
            return ResponseEntity.ok(articuloActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eliminar un artículo (eliminación lógica)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArticulo(@PathVariable Long id) {
        try {
            articuloService.eliminarArticulo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eliminar un artículo físicamente
    @DeleteMapping("/{id}/fisico")
    public ResponseEntity<Void> eliminarArticuloFisicamente(@PathVariable Long id) {
        try {
            articuloService.eliminarArticuloFisicamente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar stock de un artículo
    @PutMapping("/{id}/stock")
    public ResponseEntity<Articulo> actualizarStock(@PathVariable Long id, 
                                                   @RequestParam Integer stock) {
        try {
            Articulo articuloActualizado = articuloService.actualizarStock(id, stock);
            return ResponseEntity.ok(articuloActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Reducir stock de un artículo
    @PutMapping("/{id}/stock/reducir")
    public ResponseEntity<Articulo> reducirStock(@PathVariable Long id, 
                                               @RequestParam Integer cantidad) {
        try {
            Articulo articuloActualizado = articuloService.reducirStock(id, cantidad);
            return ResponseEntity.ok(articuloActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Aumentar stock de un artículo
    @PutMapping("/{id}/stock/aumentar")
    public ResponseEntity<Articulo> aumentarStock(@PathVariable Long id, 
                                                @RequestParam Integer cantidad) {
        try {
            Articulo articuloActualizado = articuloService.aumentarStock(id, cantidad);
            return ResponseEntity.ok(articuloActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Verificar stock suficiente
    @GetMapping("/{id}/stock/verificar")
    public ResponseEntity<Boolean> verificarStockSuficiente(@PathVariable Long id, 
                                                          @RequestParam Integer cantidad) {
        try {
            boolean suficiente = articuloService.tieneStockSuficiente(id, cantidad);
            return ResponseEntity.ok(suficiente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 

