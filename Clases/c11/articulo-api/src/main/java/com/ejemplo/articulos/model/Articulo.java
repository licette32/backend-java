
// Paquete que contiene la clase de modelo
package com.ejemplo.articulos.model;

// Clase que representa un artículo
public class Articulo {

    // Atributo que representa el ID único del artículo
    private Long id;

    // Atributo que representa el nombre del artículo
    private String nombre;

    // Atributo que representa el precio del artículo
    private Double precio;

    // Constructor vacío requerido por frameworks como Spring y Jackson
    public Articulo() {}

    // Constructor con parámetros para facilitar la creación de objetos
    public Articulo(Long id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Métodos getter y setter para acceder y modificar los atributos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
