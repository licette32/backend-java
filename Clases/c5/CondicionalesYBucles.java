//package inicio;

import java.util.Scanner;

public class CondicionalesYBucles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //  Ingresar cantidad de productos
        System.out.print("Ingrese la cantidad de productos que desea comprar: ");
        //int cantidad = scanner.nextInt();

        /* Condicional: si compra más de 100, aplica descuento
        if (cantidad > 100) {
            System.out.println("¡Aplica un descuento especial por comprar más de 100 unidades!");
        } else {
            System.out.println("No aplica descuento especial.");
        }*/ 

        /*  Bucle for: imprimir del 1 hasta la cantidad ingresada
        System.out.println("\nUsando un bucle FOR para imprimir del 1 al " + cantidad + ":");
        for (int i = 1; i <= cantidad; i++) {
            System.out.print(i + " ");
        }*/ 

        /* Bucle while: hacer lo mismo con otro enfoque
        System.out.println("\n\nUsando un bucle WHILE para imprimir del 1 al " + cantidad + ":");
        int j = 1;
        while (j <= cantidad) {
            System.out.print(j + " ");
            j++;
        } */

        /* bucle do while para pedir un numero hasta que sea par
        System.out.println("\n\nIngrese un número par: ");
        int numero;
        do {
            numero = scanner.nextInt();
            if (numero % 2 != 0) {
                System.out.println("El número ingresado no es par. Intente nuevamente: ");
            }
        } while (numero % 2 != 0); */

        scanner.close();
    }
}
