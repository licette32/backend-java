// Declaramos el paquete donde se encuentra esta clase de configuración
package com.proyecto.talento.carrito.config;

// Importamos @EntityScan para especificar dónde buscar entidades JPA
import org.springframework.boot.autoconfigure.domain.EntityScan;
// Importamos @Configuration para marcar esta clase como una clase de configuración
import org.springframework.context.annotation.Configuration;
// Importamos @EnableJpaRepositories para habilitar los repositorios JPA
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @Configuration marca esta clase como una clase de configuración de Spring
@Configuration
// @EnableJpaRepositories habilita los repositorios JPA y especifica dónde buscarlos
// basePackages indica el paquete donde están nuestros repositorios
@EnableJpaRepositories(basePackages = "com.proyecto.talento.carrito.repository")
// @EntityScan especifica dónde buscar las entidades JPA (nuestros modelos)
@EntityScan(basePackages = "com.proyecto.talento.carrito.model")
// Clase que configura la conexión y comportamiento de la base de datos
// Esta configuración le dice a Spring dónde encontrar nuestras entidades y repositorios
public class DatabaseConfig {
    // Configuración adicional de base de datos si es necesaria
    // Por ejemplo, se podrían agregar beans para DataSource, TransactionManager, etc.
} 

