public class cadenas {
    public static void main(String[] args) {
        // verifica si la cadena esta vacia
        String cadena = "Hola Mundo";
        if (cadena.isEmpty()) {
            System.out.println("La cadena está vacía.");
        } else {
            System.out.println("La cadena no está vacía.");
        }
        // verifica si la cadena contiene un texto
        String texto = "Mundo";
        if (cadena.contains(texto)) {
            System.out.println("La cadena contiene el texto: " + texto);
        } else {
            System.out.println("La cadena no contiene el texto: " + texto);
        }
        // verifica si la cadena empieza con un texto
        String inicio = "Hola";
        if (cadena.startsWith(inicio)) {
            System.out.println("La cadena empieza con el texto: " + inicio);
        } else {
            System.out.println("La cadena no empieza con el texto: " + inicio);
        }
        // verifica si la cadena termina con un texto
        String fin = "Mundo";
        if (cadena.endsWith(fin)) {
            System.out.println("La cadena termina con el texto: " + fin);
        } else {
            System.out.println("La cadena no termina con el texto: " + fin);
        }
        // convierte la cadena a mayusculas
        String mayusculas = cadena.toUpperCase();
        System.out.println("Cadena en mayúsculas: " + mayusculas);
        // convierte la cadena a minusculas
        String minusculas = cadena.toLowerCase();
        System.out.println("Cadena en minúsculas: " + minusculas);
        // reemplaza un texto por otro
        String reemplazo = cadena.replace("Mundo", "Java");
        System.out.println("Cadena con reemplazo: " + reemplazo);
        // saca los espacios al principio y al final de la cadena
        String recortada = cadena.trim();
        System.out.println("Cadena recortada: " + recortada);
        // obtiene la longitud de la cadena
        int longitud = cadena.length();
        System.out.println("Longitud de la cadena: " + longitud);
        // obtiene el caracter en una posicion
        int posicion = 4;
        char caracter = cadena.charAt(posicion);
        System.out.println("Caracter en la posición " + posicion + ": " + caracter);
        // convierte la cadena a un array de caracteres
        char[] arrayCaracteres = cadena.toCharArray();
        System.out.println("Array de caracteres: ");
        for (char c : arrayCaracteres) {
            System.out.print(c + " ");
        }
        System.out.println();
        // convierte la cadena a un array de palabras
        String[] arrayPalabras = cadena.split(" ");
        System.out.println("Array de palabras: ");
        for (String palabra : arrayPalabras) {
            System.out.print(palabra + " ");
        }
        System.out.println();
        // verifica si la cadena es igual a otra
        String otraCadena = "Hola Mundo";
        if (cadena.equals(otraCadena)) {
            System.out.println("La cadena es igual a: " + otraCadena);
        } else {
            System.out.println("La cadena no es igual a: " + otraCadena);
        }
        // verifica si la cadena es igual a otra ignorando mayusculas y minusculas
        if (cadena.equalsIgnoreCase(otraCadena)) {
            System.out.println("La cadena es igual a: " + otraCadena + " (ignorando mayúsculas y minúsculas)");
        } else {
            System.out.println("La cadena no es igual a: " + otraCadena + " (ignorando mayúsculas y minúsculas)");
        }
        // verifica si la cadena empieza con un texto ignorando mayusculas y minusculas
        String inicioIgnorado = "hola";
        if (cadena.toLowerCase().startsWith(inicioIgnorado.toLowerCase())) {
            System.out.println("La cadena empieza con el texto: " + inicioIgnorado + " (ignorando mayúsculas y minúsculas)");
        } else {
            System.out.println("La cadena no empieza con el texto: " + inicioIgnorado + " (ignorando mayúsculas y minúsculas)");
        }
        //stringBuilder para concatenar cadenas
        StringBuilder sb = new StringBuilder();
        sb.append("Hola").append(" ").append("Mundo").append(" ").append("Java");
        String cadenaConcatenada = sb.toString();
        System.out.println("Cadena concatenada: " + cadenaConcatenada);
        //stringBuilder para invertir una cadena
        StringBuilder sbInvertido = new StringBuilder(cadena);
        sbInvertido.reverse(); 
        String cadenaInvertida = sbInvertido.toString();
        System.out.println("Cadena invertida: " + cadenaInvertida);
        //stringBuilder para eliminar un texto de una cadena
        StringBuilder sbEliminar = new StringBuilder(cadena);
        int inicioEliminar = cadena.indexOf("Mundo");
        int finEliminar = inicioEliminar + "Mundo".length();
        sbEliminar.delete(inicioEliminar, finEliminar);
        String cadenaEliminada = sbEliminar.toString();
        System.out.println("Cadena eliminada: " + cadenaEliminada);
        //stringBuilder para insertar un texto en una cadena
        StringBuilder sbInsertar = new StringBuilder(cadena);
        int posicionInsertar = 4;
        String textoInsertar = "Java ";
        sbInsertar.insert(posicionInsertar, textoInsertar);
        String cadenaInsertada = sbInsertar.toString();
        System.out.println("Cadena insertada: " + cadenaInsertada);
        //stringBuilder para reemplazar un texto en una cadena
        StringBuilder sbReemplazar = new StringBuilder(cadena);
        int inicioReemplazar = cadena.indexOf("Mundo");
        int finReemplazar = inicioReemplazar + "Mundo".length();
        String textoReemplazar = "Java";
        sbReemplazar.replace(inicioReemplazar, finReemplazar, textoReemplazar);
        String cadenaReemplazada = sbReemplazar.toString();
        System.out.println("Cadena reemplazada: " + cadenaReemplazada);
        //stringBuilder para comparar cadenas
        StringBuilder sbComparar = new StringBuilder(cadena);
        StringBuilder sbOtraCadena = new StringBuilder(otraCadena);
        if (sbComparar.toString().equals(sbOtraCadena.toString())) {
            System.out.println("Las cadenas son iguales: " + sbComparar.toString() + " y " + sbOtraCadena.toString());
        } else {
            System.out.println("Las cadenas no son iguales: " + sbComparar.toString() + " y " + sbOtraCadena.toString());
        }
        // que hace toString() en StringBuilder
        // toString() convierte el StringBuilder en un String
        // y permite usar métodos de String
        String cadenaString = sb.toString();
        System.out.println("Cadena String: " + cadenaString);

        // extraer subcadena de una cadena
        String subcadena = cadena.substring(0, 4); // "Hola"
        System.out.println("Subcadena: " + subcadena);



    }
        
}
