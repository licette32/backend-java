// Declaramos el paquete donde se encuentra esta interfaz de repositorio
package com.proyecto.talento.carrito.repository;

// Importamos el modelo Cliente que vamos a manejar
import com.proyecto.talento.carrito.model.Cliente;
// Importamos JpaRepository que nos da métodos básicos para trabajar con la base de datos
import org.springframework.data.jpa.repository.JpaRepository;
// Importamos @Query para crear consultas personalizadas con JPQL
import org.springframework.data.jpa.repository.Query;
// Importamos @Param para pasar parámetros a nuestras consultas
import org.springframework.data.repository.query.Param;
// Importamos @Repository para marcar esta interfaz como un repositorio
import org.springframework.stereotype.Repository;

// Importamos Optional para manejar valores que pueden estar presentes o no
import java.util.Optional;

// @Repository marca esta interfaz como un repositorio de Spring
@Repository
// Interfaz que extiende JpaRepository para operaciones CRUD automáticas
// JpaRepository<Cliente, Long> significa que maneja entidades Cliente con ID de tipo Long
// Spring Data JPA implementa automáticamente todos los métodos de esta interfaz
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Método que busca un cliente por su dirección de email
    // Spring Data JPA genera automáticamente la consulta basándose en el nombre del método
    // Como email es único, devuelve Optional que puede contener un cliente o estar vacío
    Optional<Cliente> findByEmail(String email);
    
    // Método que busca un cliente por su número de DNI
    // Spring Data JPA genera automáticamente la consulta basándose en el nombre del método
    // Como DNI es único, devuelve Optional que puede contener un cliente o estar vacío
    Optional<Cliente> findByDni(String dni);
    
    // Método que verifica si existe un cliente con el email especificado
    // Útil para validaciones antes de crear nuevos clientes
    // Devuelve true si existe, false si no existe
    boolean existsByEmail(String email);
    
    // Método que verifica si existe un cliente con el DNI especificado
    // Útil para validaciones antes de crear nuevos clientes
    // Devuelve true si existe, false si no existe
    boolean existsByDni(String dni);
    
    // Método personalizado para buscar clientes por nombre o apellido
    // @Query define una consulta JPQL personalizada
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(c.apellido) LIKE LOWER(CONCAT('%', :texto, '%'))")
    // La consulta busca en ambos campos (nombre Y apellido) de forma insensible a mayúsculas
    // CONCAT agrega '%' antes y después del texto para búsqueda "contiene"
    java.util.List<Cliente> buscarPorNombreOApellido(@Param("texto") String texto);
    
    // Método adicional para buscar cliente por email (funcionalidad similar a findByEmail)
    // Este método usa @Query explícita en lugar de naming convention
    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    // Devuelve Optional porque puede no encontrar el cliente
    Optional<Cliente> findByEmailIgnoreCase(@Param("email") String email);
} 

