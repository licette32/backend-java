
// Articulo hereda de Producto e implementa Vendible
public class Articulo extends Producto implements Vendible {
    private double precio;

    public Articulo(int id, String nombre, double precio) {
        super(id, nombre);  // Llamada al constructor de la superclase
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Implementación del método abstracto
    @Override
    public void mostrarDetalle() {
        System.out.println("ID: " + getId() + " | Nombre: " + getNombre() + " | Precio: $" + precio);
    }
}
