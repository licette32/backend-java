import java.util.Scanner;

public class SumaNumeros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numero;
        int suma = 0;

        System.out.println("Ingrese números para sumar. Escriba 0 para finalizar.");

        // Bucle while que continúa mientras el número ingresado sea distinto de 0
        while (true) {
            System.out.print("Ingrese un número: ");
            numero = scanner.nextInt();

            if (numero == 0) {
                break; // rompe el bucle si se ingresa 0
            }

            suma += numero; // acumula la suma
        }

        System.out.println("La suma total es: " + suma);
        scanner.close(); // cerramos el Scanner
    }
}
