
// Clase abstracta que representa un producto general
public abstract class Producto {
    private int id;
    private String nombre;

    public Producto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Método abstracto que será implementado por las subclases
    public abstract void mostrarDetalle();
}
