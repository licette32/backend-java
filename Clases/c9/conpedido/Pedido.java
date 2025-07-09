
// Clase que representa un Pedido
import java.util.ArrayList;

public class Pedido {
    private static int contadorPedidos = 1; // Variable de clase para numerar pedidos
    private int numeroPedido;
    private ArrayList<Producto> productos;

    public Pedido() {
        this.numeroPedido = contadorPedidos++;
        this.productos = new ArrayList<>();
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void mostrarPedido() {
        System.out.println("Pedido Nro: " + numeroPedido);
        if (productos.isEmpty()) {
            System.out.println("El pedido está vacío.");
        } else {
            for (Producto p : productos) {
                p.mostrarDetalle(); // Polimorfismo aplicado
            }
        }
    }
}
