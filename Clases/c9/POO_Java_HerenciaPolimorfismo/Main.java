
import java.util.ArrayList;
import java.util.Scanner;

// Clase principal que usa polimorfismo y herencia
public class Main {
    static ArrayList<Producto> lista = new ArrayList<>(); // tengo un arrayst de productos, que es la clase padre abstracta
    // articulo hereda de producto
    // producto tecnologico hereda de articulo
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Productos ---");
            System.out.println("1. Crear artículo");
            System.out.println("2. Crear producto tecnológico");
            System.out.println("3. Listar productos");
            System.out.println("4. Modificar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> crearProductoTecnologico();
                case 3 -> listarProductos();
                case 4 -> modificarProducto();
                case 5 -> eliminarProducto();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 6);
    }

    public static void crearArticulo() {
        System.out.print("ID: ");
        // Se utiliza sc.nextInt() para leer un número entero
        // y sc.nextLine() para leer una línea de texto
        // después de leer el número entero.
        // Esto es necesario porque después de leer un número entero,
        // el buffer de entrada todavía contiene el salto de línea
        // que se genera al presionar Enter.
        // Al llamar a sc.nextLine() después de sc.nextInt(),
        // se consume ese salto de línea y se prepara el buffer
        // para leer la siguiente línea de texto correctamente.
        // Si no se hace esto, el siguiente sc.nextLine() leería
        // el salto de línea restante en lugar de esperar a que el usuario ingrese un nuevo texto.
        // Esto puede causar problemas al leer cadenas de texto después de leer números.
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        lista.add(new Articulo(id, nombre, precio));
        System.out.println("Artículo creado.");
    }

    public static void crearProductoTecnologico() {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble(); sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();

        Articulo produ = new ProductoTecnologico(id, nombre, precio, marca);
        lista.add(produ);
        produ.toString();
        System.out.println("Producto tecnológico creado.");
    }

    public static void listarProductos() {
        if (lista.isEmpty()) {
            System.out.println("No hay productos.");
        } else {
            for (Producto p : lista) {
                // porque es polimorfismo
                // se puede llamar al método mostrarDetalle() de la clase padre
                // y se ejecutará el método correspondiente de la subclase
                // gracias a la sobreescritura del método mostrarDetalle()
                // en la clase Articulo y ProductoTecnologico
                // Esto es un ejemplo de polimorfismo en Java
                // donde el mismo método se comporta de manera diferente
                // dependiendo del objeto que lo invoque.
                // En este caso, el método mostrarDetalle() se comporta
                // de manera diferente para los objetos de tipo Articulo
                // y ProductoTecnologico, aunque ambos son tratados
                // como objetos de tipo Producto.
                // Esto es posible gracias a la herencia y la sobreescritura
                // de métodos en Java.
               // p.mostrarDetalle(); // Polimorfismo
               System.out.println( p.toString());
            }
        }
    }

    public static void modificarProducto() {
        System.out.print("ID del producto a modificar: ");
        int id = sc.nextInt(); sc.nextLine();
        // Recorremos todos los productos almacenados en la lista
        for (Producto p : lista) {

            // Verificamos si el ID del producto actual coincide con el buscado
            // y además si el producto implementa la interfaz Vendible (o sea, tiene precio)
            if (p.getId() == id && p instanceof Vendible) {

                // Pedimos al usuario el nuevo nombre del producto
                System.out.print("Nuevo nombre: ");
                p.setNombre(sc.nextLine());  // Usamos el setter para cambiar el nombre

                // Pedimos el nuevo precio
                System.out.print("Nuevo precio: ");
                // Como el objeto es de tipo Producto (clase abstracta) y el método setPrecio
                // está definido en la interfaz Vendible, lo "casteamos" (convertimos) temporalmente
                // a Vendible para poder usar el método
                ((Vendible) p).setPrecio(sc.nextDouble());

                // Confirmamos al usuario que el producto fue modificado
                System.out.println("Producto modificado.");

                // Salimos del método porque ya encontramos y modificamos el producto
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }

    public static void eliminarProducto() {
        System.out.print("ID del producto a eliminar: ");
        int id = sc.nextInt();
        lista.removeIf(p -> p.getId() == id);
        System.out.println("Producto eliminado si existía.");
    }
}
