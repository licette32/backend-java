//package inicio;
import java.util.Scanner; 

public class Inicio {
    public static void main(String[] args) {
       
        // ingrese su nombre
      /* System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        //mostrar por pantalla un saludo
        System.out.println("Hola " + nombre + ", bienvenido al programa.");
        sc.close();*/   
       /* System.out.println("=== Bucle for clásico ===");
        for (int i = 0; i < 3; i++) {
            System.out.println("Hola " + i);
        }*/ 
       /* System.out.println("Pedro");
        System.out.println("Pedro1");
        System.out.println("Pedro2");
        System.out.println("Pedro3");
        System.out.println("Pedro4");
        System.out.println("Pedro5");
        System.out.println("Pedro6");*/ 
       // int i = 0;
      /* for ( int i = 10; i >= 0; i--) {
            System.out.println(i);
           // i++;
        }*/
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== Bucle while (entrada hasta 'salir') ===");
        String input;
        while (true) { // mientras sea verdadero, se ejecuta el bucle--> esto siempre es verdadero, entonces tenemos que controlar desde dentro del while cuando se tiene que cortar el bucle, con un break
            System.out.print("Ingresá algo ('salir' para terminar): ");
            input = sc.nextLine();
            // este method de string compara el valor de la cadena, y si es igual a "salir" (sin importar mayusculas o minusculas)
            if (input.equalsIgnoreCase("salir")) {
                break;
            }
            System.out.println("Ingresaste: " + input);
        }
        sc.close();
       
    }
}
