//package inicio;
//import java.util.Arrays;

public class Manipulacionstringarrays {
    public static void main(String[] args) {
        // Cadena original con espacios y mayúsculas mezcladas
        String cadenaOriginal = " té CHAi ";
        
        // Limpieza y formato: trim + toLowerCase + capitalize manual
        String cadenaLimpia = cadenaOriginal.trim().toLowerCase(); // "té chai"
        // split() para separar las palabras por espacios
        String[] palabras = cadenaLimpia.split(" ");
        // que es StringBuilder para construir la cadena formateada
        // StringBuilder es más eficiente para concatenar cadenas
        StringBuilder resultado = new StringBuilder();
       
        for (String palabra : palabras) {
            // Convertir la primera letra a mayúscula y el resto a minúscula
            // Que es isEmpty() para evitar errores con palabras vacías
            // y evitar IndexOutOfBoundsException

            if (!palabra.isEmpty()) {
                // charAt(0) para obtener la primera letra
                // y substring(1) para obtener el resto de la palabra
                // append() para concatenar
                resultado.append(Character.toUpperCase(palabra.charAt(0)))
                         .append(palabra.substring(1))
                         .append(" ");
            }
        }
        // Convertir StringBuilder a String y eliminar el espacio final
        // que es trim() para eliminar espacios al principio y al final
        String cadenaFormateada = resultado.toString().trim(); // "Té Chai"
        System.out.println("Cadena formateada: " + cadenaFormateada);

        // Crear un array de 5 productos
        String[] productos = new String[5];
        productos[0] = "Té Verde";
        productos[1] = "Té Negro";
        productos[2] = "Té Rojo";
        productos[3] = "Té Chai";
        productos[4] = "Té Blanco";

        System.out.println("\nProductos disponibles:");
        for (String producto : productos) {
            System.out.println("- " + producto);
        }

        // Intentar agregar un sexto producto (provoca error si se descomenta)
        // productos[5] = "Té Azul"; // Esto lanza ArrayIndexOutOfBoundsException

        // Mostrar longitud del array
        System.out.println("\nCantidad de productos: " + productos.length);

        // Mostrar primera letra del string formateado
        System.out.println("Primera letra de la cadena formateada: " + cadenaFormateada.charAt(0));

        // Verificar si contiene "Chai"
        boolean contieneChai = cadenaFormateada.contains("Chai");
        System.out.println("¿Contiene 'Chai'? " + contieneChai);
    }
}
