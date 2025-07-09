
// Subclase de Articulo que añade atributo marca
public class ProductoTecnologico extends Articulo {
    private String marca;

    public ProductoTecnologico(int id, String nombre, double precio, String marca) {
        super(id, nombre, precio);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Sobrescribimos el método para mostrar detalles con más información
    @Override
    public void mostrarDetalle() {
        System.out.println("ID: " + getId() + " | Nombre: " + getNombre() +
                           " | Precio: $" + getPrecio() + " | Marca: " + marca);
    }
}
