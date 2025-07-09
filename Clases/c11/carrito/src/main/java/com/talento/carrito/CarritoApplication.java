package com.talento.carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//este main va a correr una aplicacion de Spring Boot
//es una aplicacion que va a correr un servidor web embebido
//y va a exponer un API REST para que los clientes puedan interactuar con el carrito de compras
//la anotacion @SpringBootApplication es una combinacion de tres anotaciones:
// @Configuration, @EnableAutoConfiguration y @ComponentScan
// @Configuration indica que esta clase es una clase de configuracion de Spring
// @EnableAutoConfiguration indica que Spring Boot debe configurar automaticamente la aplicacion
// @ComponentScan indica que Spring debe buscar componentes, configuraciones y servicios en el paquete actual y sus subpaquetes
@SpringBootApplication
public class CarritoApplication {

	public static void main(String[] args) {
		// SpringApplication.run es un metodo estatico que arranca la aplicacion
		// recibe como parametro la clase principal de la aplicacion y los argumentos de la linea de comandos
		// en este caso, la clase principal es CarritoApplication
		// y los argumentos son los que se pasan al ejecutar la aplicacion
		
		SpringApplication.run(CarritoApplication.class, args);
	}

}
