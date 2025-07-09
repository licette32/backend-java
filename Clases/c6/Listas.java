import java.util.List;
import java.util.ArrayList;

public class Listas {
    /*  Se usan cuando no se conoce el tamaño fijo o necesitamos que crezca dinámicamente.
        Se recomienda trabajar con List en lugar de ArrayList directamente (principio de programar hacia una interfaz).
        Se usan mucho para recorrer, filtrar, ordenar colecciones.
    */
    public static void main(String[] args) {
        // Declaramos una lista de Strings (nombres)
        List<String> nombres = new ArrayList<>();
        List listaVariada = new ArrayList(); // Lista sin tipo específico
        System.out.println("Lista variada: " + listaVariada);
        listaVariada.add("");
        listaVariada.add(null);
        listaVariada.add(true);
        listaVariada.add("Hola");
        listaVariada.add(123);
        System.out.println("Lista variada: " + listaVariada);
        

        // Agregamos elementos a la lista
        nombres.add("Ana");
        nombres.add("Luis");
        nombres.add("Carlos");

        // Imprimimos el contenido con un for-each
      /*  for (String nombre : nombres) {
            System.out.println("Nombre: " + nombre);
        }*/ 

      /* for(Object elemento: listaVariada){
            System.out.println("Elemento: " + elemento);
            if(elemento instanceof String){
                System.out.println("Es un String: " + elemento);
            } else if (elemento instanceof Integer) {
                System.out.println("Es un Integer: " + elemento);
            } else if (elemento instanceof Boolean) {
                System.out.println("Es un Boolean: " + elemento);
            } else {
                System.out.println("Tipo desconocido:" );
            }

        }*/  

        // Consultamos el tamaño de la lista
       // System.out.println("Total de nombres: " + nombres.size());

        // Accedemos por índice
       // System.out.println("Primer nombre: " + nombres.get(0));

        // Verificamos si contiene un valor
       /* if (nombres.contains("Luis")) {
            System.out.println("Luis está en la lista.");
        }*/ 

        // Eliminamos un elemento
       /* nombres.remove("Carlos");
        System.out.println("Lista después de eliminar a Carlos: " + nombres);*/ 
    }
}
