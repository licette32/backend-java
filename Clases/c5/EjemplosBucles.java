import java.util.Scanner;

public class EjemplosBucles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bucle for clásico ===");
        for (int i = 0; i < 3; i++) {
            System.out.println("Hola " + i);
        }

        System.out.println("\n=== Bucle while (entrada hasta 'salir') ===");
        String input;
        while (true) {
            System.out.print("Ingresá algo ('salir' para terminar): ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("salir")) {
                break;
            }
            System.out.println("Ingresaste: " + input);
        }

        System.out.println("\n=== Bucle do-while (validación de contraseña) ===");
        String password;
        do {
            System.out.print("Ingresá la contraseña (clave: 1234): ");
            password = scanner.nextLine();
        } while (!password.equals("1234"));
        System.out.println("Contraseña correcta.");

        System.out.println("\n=== Bucle foreach (recorrer arreglo de frutas) ===");
        String[] frutas = {"manzana", "banana", "pera"};
        for (String fruta : frutas) {
            System.out.println("Fruta: " + fruta);
        }

        System.out.println("\n=== Bucle infinito controlado con break (menú simple) ===");
        int opcion;
        while (true) {
            System.out.println("\n1. Saludar");
            System.out.println("2. Salir");
            System.out.print("Elegí una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresá un número válido.");
                continue;
            }

            if (opcion == 2) {
                System.out.println("Adiós.");
                break;
            } else if (opcion == 1) {
                System.out.println("¡Hola!");
            } else {
                System.out.println("Opción inválida.");
            }
        }

        System.out.println("\n=== Bucle for con decremento ===");
        for (int i = 5; i > 0; i--) {
            System.out.println("Contando: " + i);
        }

        System.out.println("\n=== Bucle for sin condiciones explícitas (avanzado) ===");
        int i = 0;
        for ( ; ; ) {
            if (i == 3) break;
            System.out.println("i vale: " + i);
            i++;
        }

        scanner.close();
    }
}
