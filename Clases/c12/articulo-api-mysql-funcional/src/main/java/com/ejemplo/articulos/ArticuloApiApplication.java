
package com.ejemplo.articulos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marca esta clase como el punto de entrada de Spring Boot
@SpringBootApplication
public class ArticuloApiApplication {
    public static void main(String[] args) {
        // Lanza la aplicación
        SpringApplication.run(ArticuloApiApplication.class, args);
    }
}
