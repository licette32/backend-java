// Declaramos el paquete donde se encuentra esta interfaz de repositorio
package com.proyecto.talento.carrito.repository;

// Importamos el modelo Articulo que vamos a manejar
import com.proyecto.talento.carrito.model.Articulo;
// Importamos JpaRepository que nos da métodos básicos para trabajar con la base de datos
import org.springframework.data.jpa.repository.JpaRepository;
// Importamos @Query para crear consultas personalizadas con JPQL
import org.springframework.data.jpa.repository.Query;
// Importamos @Param para pasar parámetros a nuestras consultas
import org.springframework.data.repository.query.Param;
// Importamos @Repository para marcar esta interfaz como un repositorio
import org.springframework.stereotype.Repository;

// Importamos List para manejar listas de artículos
import java.util.List;

// @Repository marca esta interfaz como un repositorio de Spring
@Repository
// Interfaz que extiende JpaRepository para operaciones CRUD automáticas
// JpaRepository<Articulo, Long> significa que maneja entidades Articulo con ID de tipo Long
// Spring Data JPA implementa automáticamente todos los métodos de esta interfaz
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    // Método que busca artículos marcados como activos (activo = true)
    // Spring Data JPA genera automáticamente la consulta basándose en el nombre del método
    List<Articulo> findByActivoTrue();
    
    // Método personalizado para buscar artículos por nombre que contenga un texto específico
    // @Query define una consulta JPQL personalizada
    @Query("SELECT a FROM Articulo a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    // LOWER() convierte a minúsculas para hacer búsquedas insensibles a mayúsculas/minúsculas
    // CONCAT concatena '%' antes y después del nombre para hacer una búsqueda "contiene"
    List<Articulo> findByNombreContaining(@Param("nombre") String nombre);
    
    // Método para buscar artículos dentro de un rango de precios
    @Query("SELECT a FROM Articulo a WHERE a.precio BETWEEN :precioMin AND :precioMax")
    // BETWEEN busca valores entre precioMin y precioMax (inclusive)
    List<Articulo> findByPrecioBetween(@Param("precioMin") Double precioMin, 
                                      @Param("precioMax") Double precioMax);
    
    // Método para buscar artículos que tienen stock disponible (mayor a 0)
    @Query("SELECT a FROM Articulo a WHERE a.stock > 0")
    // Solo devuelve artículos con stock positivo
    List<Articulo> findByStockGreaterThan();
    
    // Método para buscar artículos con stock bajo (menor a un valor específico)
    @Query("SELECT a FROM Articulo a WHERE a.stock < :stockMinimo")
    // Útil para alertas de inventario bajo
    List<Articulo> findByStockLessThan(@Param("stockMinimo") Integer stockMinimo);
    
    // Método que combina dos condiciones: artículos activos Y con stock disponible
    @Query("SELECT a FROM Articulo a WHERE a.activo = true AND a.stock > 0")
    // Solo devuelve artículos que están activos y tienen stock para vender
    List<Articulo> findByActivoTrueAndStockGreaterThan();
    
    // Método para obtener todos los artículos ordenados por precio de menor a mayor
    @Query("SELECT a FROM Articulo a ORDER BY a.precio ASC")
    // ORDER BY ordena los resultados, ASC = ascendente (menor a mayor)
    List<Articulo> findAllOrderByPrecioAsc();
    
    // Método para obtener todos los artículos ordenados por precio de mayor a menor
    @Query("SELECT a FROM Articulo a ORDER BY a.precio DESC")
    // DESC = descendente (mayor a menor)
    List<Articulo> findAllOrderByPrecioDesc();
}


