public class Funciones {
    // Método simple que no recibe parámetros y no devuelve valor
    public static void saludar() {
        System.out.println("¡Hola desde un método!");
    }
     // Método que recibe un parámetro y no devuelve valor
     public static void saludar(String nombre) {
        System.out.println("¡Hola, " + nombre + "!");
    }
     // Método que recibe un parámetro y devuelve un valor
     public static int sumar(int a, int b) {
        return a + b; // Devuelve la suma de 'a' y 'b'
    }
    // paso de argumentos por valor
    public static void cambiarValor(int numero) {
        numero = 100; // Solo se cambia dentro del método
    }
    public static void modificarArray(int[] arr) {
        arr[0] = 999; // Modifica el contenido del array
    }
    // sobrecarga del method sumar 
    public static double sumar(double a, double b) {
        return a + b; // Devuelve la suma de 'a' y 'b'
    }
    // modularizar funciones en pequeñas funcionalidades
    // Método que pide un número ficticio de un "usuario"
      public static int obtenerNumero() {
        return 10; // En lugar de Scanner, retornamos un número para simplificar
    }

    // Método que realiza un cálculo
    public static int calcularDoble(int numero) {
        return numero * 2;
    }
     // Método que muestra el resultado
     public static void mostrarResultado(int resultado) {
        System.out.println("El doble es: " + resultado);
    }
   
    public static void main(String[] args) {
        // Llamamos al método 'saludar'
        saludar(); // Esto imprime un mensaje por consola
         // Llamamos al método 'saludar' con un argumento
        saludar("Juan"); // Esto imprime "¡Hola, Juan!"
         // Llamamos al método 'sumar' y almacenamos el resultado
        int resultado = sumar(5, 3); // Esto almacena 8 en 'resultado'
        System.out.println("La suma es: " + resultado); // Imprime "La suma es: 8"

        int x = 5;
        cambiarValor(x);
        System.out.println(x); // Imprime 5 → no cambió, porque Java pasa por valor

        int[] numeros = {1, 2, 3};
        modificarArray(numeros);
        System.out.println(numeros[0]); // Imprime 999 → sí se modificó

        // llamado al method sumar, pero con valores double
        System.out.println(sumar(4.5, 2.5));  // Llama al método double → 7.0

        // modularizar
        int num = obtenerNumero();
        int doble = calcularDoble(num);
        mostrarResultado(doble); // Esto modulariza cada paso del proceso
    }
}



