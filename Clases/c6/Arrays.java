public class Arrays {
    /*Se usan cuando sabemos cuántos elementos tendremos.

     Son de tamaño fijo y eficientes en rendimiento.

     Útiles cuando se trabaja con tipos primitivos o estructuras de datos simples. */
     public static void main(String[] args) {
        // Declaramos un array de enteros con 5 posiciones
        int[] numeros = new int[5];

        // Asignamos valores manualmente
        numeros[0] = 10;
        numeros[1] = 20;
        numeros[2] = 30;
        numeros[3] = 40;
        numeros[4] = 50;
        System.out.println(numeros);

        // Recorremos el array usando un for
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Elemento en posición " + i + ": " + numeros[i]);
        }
        // Recorremos el array usando un for-each
        for (int numero : numeros) {
            System.out.println("Elemento: " + numero);
        }
        // Declaramos un array de cadenas (String) y lo inicializamos directamente
        String[] nombres = {"Juan", "María", "Pedro", "Ana"};
        // Recorremos el array de cadenas usando un for-each
     
        for (String nombre : nombres) {
            System.out.println("Nombre: " + nombre);
          
        }
        // Declaramos un array de booleanos y lo inicializamos directamente
        boolean[] verdades = {true, false, true, false};
        // Recorremos el array de booleanos usando un for-each
        for (boolean verdad : verdades) {
            System.out.println("Valor booleano: " + verdad);
        }
    }
}
