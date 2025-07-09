
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
    // public: se puede acceder desde cualquier parte del programa
    //int es el tipo de dato que devuelve el método

    public int getId() {
        return this.id; // retorna el id del articulo desde donde es llamado este method
    }
    // no tengo un setter para el id, 

    // Getter para el atributo nombre
    public String getNombre() {
        return this.nombre;
    }

    // Setter para modificar el nombre
    public void setNombre(String nombreParametro) {
        this.nombre = nombreParametro;
       
    }

    // Getter para el atributo precio
    public double getPrecio() {
        return this.precio;
    }

    // Setter para modificar el precio
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método para mostrar la información del artículo
    public void mostrar() {
        // Este método puede ser sobrescrito por subclases (polimorfismo)
        System.out.println("ID: " + this.id + " | Nombre: " + this.nombre + " | Precio: $" + this.precio);
    }
    // sobreescribo el método toString para que imprima el id, nombre y precio
    @Override
    public String toString() {
        return "me encantan las clases de java de gise";
    }
}
