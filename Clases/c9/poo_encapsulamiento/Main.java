
import java.util.ArrayList;
import java.util.Scanner;

// Clase principal del programa
public class Main {
    // Lista que almacena objetos de tipo Articulo
    static ArrayList<Articulo> lista = new ArrayList<>();
    static int contadorProductos = 0; // Contador para asignar IDs únicos a los artículos
    // Scanner para entrada de datos por consola
    static Scanner sc = new Scanner(System.in);

    // Método principal
    public static void main(String[] args) {
        int opcion;
        // Bucle principal del programa con menú interactivo
        do {
            System.out.println("\n--- Menú de Artículos ---");
            System.out.println("1. Crear artículo");
            System.out.println("2. Listar artículos");
            System.out.println("3. Modificar artículo");
            System.out.println("4. Eliminar artículo");
            System.out.println("6. Nombre del Artículo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();         // Leer opción del usuario
            sc.nextLine();                 // Limpiar buffer del scanner

            // Estructura switch para manejar las opciones
            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> listarArticulos();
                case 3 -> modificarArticulo();
                case 4 -> eliminarArticulo();
                case 5 -> System.out.println("Saliendo...");
                case 6 -> devolverNombreArticulo();
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5); // Repetir hasta que el usuario elija salir
    }

    // Método para crear un nuevo artículo
    public static void crearArticulo() {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();     // Leer ID
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();            // Leer nombre
        System.out.print("Precio: ");
        double precio = sc.nextDouble();          // Leer precio

        // Crear un nuevo objeto Articulo y agregarlo a la lista
        Articulo nuevo = new Articulo(id, nombre, precio);
        lista.add(nuevo);
        System.out.println("Artículo agregado.");
    }

    // Método para mostrar todos los artículos de la lista
    public static void listarArticulos() {
        if (lista.isEmpty()) {
            System.out.println("No hay artículos cargados.");
        } else {
            for (Articulo a : lista) {
               // a.mostrar();   // Llamada polimórfica al método mostrar()
               System.out.println(a.toString());
            }
        }
    }

    // Método para modificar un artículo existente
    public static void modificarArticulo() {
        System.out.print("ID del artículo a modificar: ");
        int id = sc.nextInt();
        for (Articulo articulo : lista) {
            if (articulo.getId() == id) {
                sc.nextLine();
                System.out.print("Nuevo nombre: ");
                articulo.setNombre(sc.nextLine());       // Usar setter para cambiar nombre
                System.out.print("Nuevo precio: ");
                articulo.setPrecio(sc.nextDouble());  
                   // Usar setter para cambiar precio
                System.out.println("Artículo actualizado.");
                return;
            }
        }
        System.out.println("Artículo no encontrado.");
    }

    // Método para eliminar un artículo por ID
    public static void eliminarArticulo() {
        System.out.print("ID del artículo a eliminar: ");
        int id = sc.nextInt();
        // Usamos removeIf con expresión lambda para eliminar por ID
        lista.removeIf(a -> a.getId() == id);
        System.out.println("Artículo eliminado si existía.");
    }

    // method para devolver el nombre del artículo por su id 
     // Método para modificar un artículo existente
    public static void devolverNombreArticulo() {
        System.out.print("ID del artículo para la consulta: ");
        int id = sc.nextInt();
        for (Articulo articulo : lista) {
            if (articulo.getId() == id) {
                System.out.println("Nombre del artículo: " + articulo.getNombre());
                return;
            }
        }
        System.out.println("Artículo no encontrado.");
    }
}
