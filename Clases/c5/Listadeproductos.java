
//package inicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Listadeproductos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 1 Leer entrada del usuario
        ArrayList<String> productos = new ArrayList<>(); // 2 Crear lista vacía

        System.out.println("¿Cuántos productos desea ingresar?");
        int cantidad = scanner.nextInt(); // 3 Leer cantidad de productos
        scanner.nextLine(); // 4 Consumir salto de línea pendiente

        // 5 Cargar productos
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese el nombre del producto #" + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            productos.add(nombre);
        }

        // 6 Eliminar producto por nombre
        System.out.print("Ingrese el nombre exacto de un producto a eliminar: ");
        String aEliminar = scanner.nextLine();
        boolean eliminado = productos.remove(aEliminar);
        System.out.println(eliminado ? "Producto eliminado." : "Producto no encontrado.");

        // 7 Verificar existencia de un producto
        System.out.print("Ingrese un producto para verificar si existe: ");
        String aBuscar = scanner.nextLine();
        boolean existe = productos.contains(aBuscar);
        System.out.println(existe ? "El producto existe." : "El producto no existe.");

        // 8 Mostrar lista original (como fue cargada)
        System.out.println("\nLista original:");
        for (String producto : productos) {
            System.out.println("- " + producto);
        }

        // 9 Mostrar lista formateada (sin usar métodos)
        System.out.println("\nLista formateada:");
        List<String> productosFormateados = new ArrayList<>();

        for (String producto : productos) {
            // Eliminar espacios y convertir a minúsculas
            String limpio = producto.trim().toLowerCase();

            // Separar por palabras
            String[] palabras = limpio.split(" ");
            String resultado = "";

            // Capitalizar cada palabra
            for (String palabra : palabras) {
                if (!palabra.isEmpty()) {
                    resultado += Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1) + " ";
                }
            }

            // Quitar el espacio final y agregar a la lista nueva
            productosFormateados.add(resultado.trim());
        }

        // Imprimir lista formateada
        for (String producto : productosFormateados) {
            System.out.println("- " + producto);
        }

        scanner.close(); // 🔚 Cerrar scanner
    }
}
