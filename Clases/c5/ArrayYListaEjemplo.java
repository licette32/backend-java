import java.util.ArrayList;

public class ArrayYListaEjemplo {
    public static void main(String[] args) {

        // ==== EJEMPLO DE ARRAY ====
        System.out.println("=== Array de frutas ===");
        String[] frutas = {"manzana", "banana", "pera"};

        for (int i = 0; i < frutas.length; i++) {
            System.out.println("Fruta en posición " + i + ": " + frutas[i]);
        }

        // ==== EJEMPLO DE ARRAYLIST ====
        System.out.println("\n=== Lista de nombres ===");
        ArrayList<String> nombres = new ArrayList<>();

        // Agregar elementos
        nombres.add("Ana");
        nombres.add("Luis");
        nombres.add("Carlos");

        // Mostrar elementos con foreach
        for (String nombre : nombres) {
            System.out.println("Nombre: " + nombre);
        }

        // Eliminar un elemento
        nombres.remove("Luis");

        // Mostrar tamaño actual
        System.out.println("Tamaño actual de la lista: " + nombres.size());
    }
}
