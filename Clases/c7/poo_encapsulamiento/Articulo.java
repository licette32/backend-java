
// Clase base Articulo con encapsulamiento
public class Articulo {
    // Atributos privados para aplicar el principio de encapsulamiento
    private int id;
    private String nombre;
    private double precio;

    // Constructor para inicializar el objeto Articulo
    public Articulo(int id, String nombre, double precio) {
        this.id = id;               // 'this' refiere al atributo del objeto
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getter para el atributo id (solo lectura)
    public int getId() {
        return id;
    }
    // no tengo un setter para el id, 

    // Getter para el atributo nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para modificar el nombre
    public void setNombre(String nombreParametro) {
        this.nombre = nombreParametro;
    }

    // Getter para el atributo precio
    public double getPrecio() {
        return precio;
    }

    // Setter para modificar el precio
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método para mostrar la información del artículo
    public void mostrar() {
        // Este método puede ser sobrescrito por subclases (polimorfismo)
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Precio: $" + precio);
    }
}
