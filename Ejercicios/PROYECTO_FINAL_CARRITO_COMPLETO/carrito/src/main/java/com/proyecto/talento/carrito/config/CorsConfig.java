// Declaramos el paquete donde se encuentra esta clase de configuración
package com.proyecto.talento.carrito.config;

// Importamos @Bean para crear beans de Spring
import org.springframework.context.annotation.Bean;
// Importamos @Configuration para marcar esta clase como una clase de configuración
import org.springframework.context.annotation.Configuration;
// Importamos la clase principal de configuración CORS
import org.springframework.web.cors.CorsConfiguration;
// Importamos la interfaz para el source de configuración CORS
import org.springframework.web.cors.CorsConfigurationSource;
// Importamos la implementación basada en URL para configuración CORS
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// Importamos CorsRegistry para registrar configuraciones CORS
import org.springframework.web.servlet.config.annotation.CorsRegistry;
// Importamos WebMvcConfigurer para configurar Spring MVC
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration marca esta clase como una clase de configuración de Spring
@Configuration
// Clase que configura CORS (Cross-Origin Resource Sharing)
// CORS permite que el frontend (que corre en un puerto diferente) acceda a nuestro backend
// Implementa WebMvcConfigurer para personalizar la configuración de Spring MVC
public class CorsConfig implements WebMvcConfigurer {

    // @SuppressWarnings("null") suprime las advertencias de null del IDE
    @SuppressWarnings("null")
    // @Override indica que sobrescribimos un método de la interfaz WebMvcConfigurer
    @Override
    // Método que configura los mappings CORS para diferentes rutas
    public void addCorsMappings(CorsRegistry registry) {
        // Configuramos CORS para todas las rutas que empiecen con "/api/"
        registry.addMapping("/api/**")
                // Permitir patrones de origen (dominios) - "*" significa cualquier origen
                .allowedOriginPatterns("*")
                // Especificar los métodos HTTP permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Permitir todos los headers en las peticiones
                .allowedHeaders("*")
                // Permitir credenciales (cookies, autenticación)
                .allowCredentials(true)
                // Configurar el tiempo de cache para peticiones preflight (1 hora)
                .maxAge(3600);
    }

    // @Bean indica que este método creará un bean que Spring manejará automáticamente
    @Bean
    // Método que crea un bean de configuración CORS más detallado
    public CorsConfigurationSource corsConfigurationSource() {
        // Creamos una nueva configuración CORS
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permitir todos los orígenes usando patrones (en producción especificar dominios específicos)
        // El patrón "*" permite cualquier dominio acceder a nuestro backend
        configuration.addAllowedOriginPattern("*");
        
        // Permitir métodos HTTP comunes uno por uno para mayor control
        configuration.addAllowedMethod("GET");    // Para obtener datos
        configuration.addAllowedMethod("POST");   // Para crear nuevos recursos
        configuration.addAllowedMethod("PUT");    // Para actualizar recursos
        configuration.addAllowedMethod("DELETE"); // Para eliminar recursos
        configuration.addAllowedMethod("OPTIONS"); // Para peticiones preflight
        
        // Permitir todos los headers en las peticiones
        configuration.addAllowedHeader("*");
        
        // Permitir credenciales (cookies, autenticación, etc.)
        configuration.setAllowCredentials(true);
        
        // Configurar tiempo de cache para peticiones preflight (1 hora = 3600 segundos)
        // Esto evita que el navegador haga peticiones preflight innecesarias
        configuration.setMaxAge(3600L);
        
        // Creamos un source basado en URL para aplicar la configuración
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        // Registramos la configuración solo para las rutas de API
        source.registerCorsConfiguration("/api/**", configuration);
        
        // Devolvemos el source configurado
        return source;
    }
} 

