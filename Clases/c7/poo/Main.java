
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // que significa static
    // static significa que la variable pertenece a la clase y no a una instancia de la clase
    // por lo tanto, se puede acceder a ella sin crear un objeto de la clase
    // en este caso, la lista de artículos es compartida por todas las instancias de la clase Main
    // y se puede acceder a ella directamente desde los métodos estáticos
    // que significa void
    // void significa que el método no devuelve ningún valor
    // en este caso, los métodos no devuelven nada porque solo realizan acciones
    // como crear, listar, modificar o eliminar artículos
// que significa public 
    // public significa que el método es accesible desde cualquier parte del programa
    // en este caso, los métodos son públicos para que puedan ser llamados desde el método main
    // que significa static void main
    // static void main es el punto de entrada del programa
    // es el primer método que se ejecuta cuando se inicia el programa
    // en este caso, el método main es estático porque no se necesita crear un objeto de la clase Main
    // para ejecutarlo
    static ArrayList<Articulo> lista = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Artículos ---");
            System.out.println("1. Crear artículo");
            System.out.println("2. Listar artículos");
            System.out.println("3. Modificar artículo");
            System.out.println("4. Eliminar artículo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> listarArticulos();
                case 3 -> modificarArticulo();
                case 4 -> eliminarArticulo();
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }

    public static void crearArticulo() {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        //con los datos que le pedimos al usuario, creamos un nuevo objeto de la clase Articulo
        // y lo agregamos a la lista
        // tipo de dato nombre = new constructorConcNombreDeLaClase(parametros)
        // este objeto NO deja de ser una variable, solo que no es una variable primitiva, sino una referencia a un espacio en la memoria
        // estamos creando una instancia, es decir instanciado un objeto de la clase Articulo
        // estamos creando un objeto una referencia a un espacio en la memoria
        // ANTES DE CREAR EL ARTICULO, DEBERIAMOS HACER VALIDACIONES PARA LA PRE ENTREGA
        // para verificar que el id no exista, que el nombre no sea vacio, que el precio sea mayor a 0
        Articulo nuevo = new Articulo(id, nombre, precio);
        lista.add(nuevo);
        System.out.println("Artículo agregado.");
    }

    public static void listarArticulos() {
        if (lista.isEmpty()) {
            System.out.println("No hay artículos cargados.");
        } else {
            for (Articulo articulo : lista) {
                articulo.mostrar();
            }
        }
    }

    public static void modificarArticulo() {
        System.out.print("ID del artículo a modificar: ");
        int id = sc.nextInt();
        for (Articulo a : lista) {
            if (a.id == id) {
                sc.nextLine();
                System.out.print("Nuevo nombre: ");
                a.nombre = sc.nextLine();
                System.out.print("Nuevo precio: ");
                a.precio = sc.nextDouble();
                System.out.println("Artículo actualizado.");
                return;
            }
        }
        System.out.println("Artículo no encontrado.");
    }

    public static void eliminarArticulo() {
        System.out.print("ID del artículo a eliminar: ");
        int idAEliminar = sc.nextInt();
       // funcion flecha o lambda, estructura de la funcion
       // parametros -> cuerpo
        // PARA LA PRE ENTREGA QUE MUESTRE UN MSJ DE ARTICULO NO ENCONTRADO SI NO LOGRA ELIMINAR NINGUN ARTICULO
        lista.removeIf(articuloLista -> articuloLista.id == idAEliminar);
        System.out.println("Artículo eliminado si existía.");
    }
    // METHOD QUE CONSULTE UN ARTICULO POR SU ID 
    // close para cerrar el scanner
    // sc.close();
    // no es necesario cerrar el scanner en este caso porque el programa termina
}
