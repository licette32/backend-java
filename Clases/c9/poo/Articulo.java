// plantilla de un artículo
public class Articulo {
    // primero van los atributos del artículo, o del objeto
    int id;
    String nombre;
    double precio;

    // constructor de la clase Articulo
    // se utiliza para crear un objeto de la clase Articulo
    // el constructor tiene el mismo nombre que la clase
    public Articulo(int idParametro, String nombreParametro, double precioParametro) {
        // LA PALABRA RESERVADA this alude al objeto actual de la clase que se esta llamando
        // que se esta creando
        // el objeto cuando se crea en memoria, se le asigna una dirección de memoria
        this.id = idParametro;
        this.nombre = nombreParametro;
        this.precio = precioParametro;
    }

    void mostrar() {
        System.out.println("ID: " + this.id + " | Nombre: " + this.nombre + " | Precio: $" + this.precio);
    }
}
