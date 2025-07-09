//package inicio;

import java.util.Scanner;

public class Ejercicioclaseuno {

     public static void main(String[] args) {
        // Crear Scanner para leer datos del usuario
        Scanner scanner = new Scanner(System.in);

        // Precio fijo por unidad
        double precioPorUnidad = 150.0;

        // Pedir nombre
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        // Pedir cantidad
        System.out.print("Hola " + nombre + ", ¿cuántos productos quiere comprar? ");
        int cantidad = scanner.nextInt();

        // Calcular el costo total
        double costoTotal = precioPorUnidad * cantidad;

        // Mostrar mensaje personalizado
        System.out.println(nombre + ", el total a pagar por " + cantidad + " productos es: $" + costoTotal);

        // Cerrar el Scanner
        scanner.close();
    }

}
