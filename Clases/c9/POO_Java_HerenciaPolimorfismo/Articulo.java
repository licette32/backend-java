
// Articulo hereda de Producto e implementa Vendible
public class Articulo extends Producto implements Vendible {
    private double precio;

    public Articulo(int id, String nombre, double precio) {
        super(id, nombre);  // Llamada al constructor de la superclase Producto
        this.precio = precio;
    }

    // getters y setters
    public double getPrecio() {
        return precio;
    }
   public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Implementación del método abstracto de la clase padre
    @Override
    public void mostrarDetalle() {
        System.out.println("ID: " + this.getId() + " | Nombre: " + this.getNombre() + " | Precio: $" + this.precio + " | Descripcion: " + this.descripcion);
    }
    // pódemos sobreescribir el método toString() para mostrar la información del artículo
   @Override
    public String toString() {
        return "Articulo{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", precio=" + precio +
                '}';
    } 
}
