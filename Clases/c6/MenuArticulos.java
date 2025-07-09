import java.util.Scanner;

public class MenuArticulos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n----- Menú de Artículos -----");
            System.out.println("1. Crear un artículo nuevo");
            System.out.println("2. Consultar un artículo");
            System.out.println("3. Listar artículos");
            System.out.println("4. Modificar un artículo");
            System.out.println("5. Borrar un artículo");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");
            
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Opción Crear un artículo nuevo seleccionada.");
                    break;
                case 2:
                    System.out.println("Opción Consultar un artículo seleccionada.");
                    break;
                case 3:
                    System.out.println("Opción Listar artículos seleccionada.");
                    break;
                case 4:
                    System.out.println("Opción Modificar un artículo seleccionada.");
                    break;
                case 5:
                    System.out.println("Opción Borrar un artículo seleccionada.");
                    break;
                case 6:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}
