package com.talento.carrito.controller;
import org.springframework.web.bind.annotation.GetMapping;
//importamos para poder usar la anotacion @RestController
import org.springframework.web.bind.annotation.RestController;

//para que sepa que es un controlador de Spring MVC, un api rest

@RestController
public class ArticuloController {

    // ahora generamos los metodos que van a manejar las peticiones HTTP
    // por ejemplo, un metodo que maneje una peticion GET a la ruta /articulos
    // @GetMapping es una anotacion que indica que este metodo va a manejar peticiones GET
    // y que la ruta es /articulos
    // @GetMapping("/articulos")
    // public List<Articulo> getArticulos() {
    //     // aqui iría la lógica para obtener los artículos
    //     return articuloService.getArticulos();
    // }
    // aqui podemos agregar mas metodos para manejar otras peticiones HTTP
    @GetMapping("/articulos")
    public String getArticulos() {
        // aqui podemos retornar una lista de articulos
        // por ahora retornamos un string para probar que el controlador funciona
        return "Somos los mejores";
    }
}
