// Declaramos el paquete raíz de nuestra aplicación
package com.proyecto.talento.carrito;

// Importamos SpringApplication para ejecutar la aplicación Spring Boot
import org.springframework.boot.SpringApplication;
// Importamos @SpringBootApplication que es la anotación principal de Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Importamos @EnableTransactionManagement para habilitar el manejo de transacciones
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @SpringBootApplication es una anotación que combina tres anotaciones importantes:
// - @Configuration: marca la clase como fuente de configuración
// - @EnableAutoConfiguration: habilita la auto-configuración de Spring Boot
// - @ComponentScan: escanea automáticamente los componentes en este paquete y subpaquetes
@SpringBootApplication
// @EnableTransactionManagement habilita el manejo automático de transacciones
// Esto es importante para operaciones de base de datos que necesitan ser atómicas
@EnableTransactionManagement
// Clase principal que inicia toda la aplicación Spring Boot del carrito de compras
public class CarritoApplication {

	// Método main que es el punto de entrada de la aplicación Java
	public static void main(String[] args) {
		// Mensaje informativo con emoji que se muestra al iniciar la aplicación
		System.out.println("🚀 Iniciando API Carrito de Compras - Talento Tech...");
		
		// SpringApplication.run() inicia la aplicación Spring Boot
		// Toma la clase principal (CarritoApplication.class) y los argumentos de línea de comandos
		SpringApplication.run(CarritoApplication.class, args);
		
		// Mensaje que se muestra cuando la aplicación se inicia correctamente
		System.out.println("✅ API Carrito iniciada correctamente!");
		
		// Mensaje informativo sobre dónde está disponible la aplicación
		System.out.println("📍 Disponible en: http://localhost:8080");
		
		// Información sobre los endpoints principales de la API
		System.out.println("📊 Endpoints principales:");
		System.out.println("   • Clientes: http://localhost:8080/api/clientes");
		System.out.println("   • Artículos: http://localhost:8080/api/articulos");
		System.out.println("   • Pedidos: http://localhost:8080/api/pedidos");
	}

}


