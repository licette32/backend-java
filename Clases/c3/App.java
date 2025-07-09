package com.ar.codoacodo;

import java.util.Arrays;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        // java es key sensitive, distigue entre mayusculas y minusculas
        // variables en JAVA tipo nombre = valor;
        byte numeroUno;
        numeroUno = 100; // de -128 a 127
        short numeroDos = 2;// de -32768 a 32767
        int numeroTres = 3;// de -2147483648 a 2147483647
        long numeroCuatro = 4;// de -9223372036854775808 a 9223372036854775807
        float numeroCinco = 5.5f;// de 1.4e-045 a 3.4e+038
        double numeroSeis = 6.6;// de 4.9e-324 a 1.8e+308
        char letra = 'a'; // de 0 a 65535
        boolean verdadero = true;// true o false
        String cadena = "Hola Mundo";// cadena de texto
        Number numero = 7;// clase padre de los numeros
        Integer numeroOcho = 8;
        System.out.println(numeroUno);
        System.out.println(numeroDos);
        System.out.println(numeroTres);
        System.out.println(numeroCuatro);
        System.out.println(numeroCinco);
        System.out.println(numeroSeis);
        System.out.println(letra);
        System.out.println(verdadero);
        System.out.println(cadena);
        System.out.println(numero);
        System.out.println(numeroOcho);

        // diferencia entre float y double

        // float es de 32 bits y double es de 64 bits
        // double es mas preciso que float
        // float es mas rapido que double
        // float es menos preciso que double
        // float es menos costoso que double
        // double es mas costoso que float

        // diferencia entre long y Number, que beneficios tiene Number

        // long es un tipo de dato primitivo
        // Number es una clase
        // long es un tipo de dato numerico
        // Number es una clase padre de los numeros
        // long es un tipo de dato entero
        // Number es una clase abstracta
        // long es un tipo de dato de 64 bits
        // Number es una clase que permite la conversion de tipos de datos numericos

        /*

        Tipos Primitivos
            Pros:
            Eficiencia: Los tipos primitivos son más eficientes en términos de memoria y velocidad de acceso.
            Simplicidad: Son más simples y directos de usar para operaciones aritméticas y lógicas.
            Menor Sobrecarga: No hay sobrecarga adicional de almacenamiento de objetos.
            Contras:
            Limitaciones en Colecciones: No pueden ser utilizados directamente en colecciones genéricas de Java (List, Map, etc.).
            Falta de Métodos Utilitarios: No proporcionan métodos para operaciones adicionales o utilitarias.
            No Pueden Ser Nulos: Siempre tienen un valor, lo que puede ser limitante si se necesita representar la ausencia de un valor (no-nullable).

        Clases Envolventes
            Pros:
            Flexibilidad en Colecciones: Pueden ser utilizados en colecciones genéricas de Java.
            Métodos Utilitarios: Proporcionan métodos adicionales para manipulación y operaciones (como conversión, comparación, etc.).
            Valores Nulos: Pueden ser nulos, lo que es útil para representar la ausencia de un valor.
            Contras:
            Sobrecarga de Memoria: Consumen más memoria debido a la naturaleza de objeto y el almacenamiento adicional.
            Rendimiento Inferior: Menor eficiencia en términos de velocidad de acceso y procesamiento en comparación con los tipos primitivos.
            Complejidad de Gestión de Nulos: Se debe manejar la posibilidad de valores nulos, lo cual puede añadir complejidad al código.
         
         */

        // diferencia entre int e Integer, que beneficios tiene Integer
        /* 
          int
            Tipo primitivo: int es uno de los tipos de datos primitivos en Java.
            Almacenamiento: Se almacena directamente en la pila (stack) de memoria, lo que significa que es muy eficiente en términos de tiempo de acceso.
            Tamaño: Ocupa 32 bits (4 bytes) de memoria.
            Valor por defecto: Si es declarado como una variable de instancia en una clase sin ser inicializado explícitamente, su valor por defecto es 0.
            Operaciones: Soporta todas las operaciones aritméticas y lógicas directamente.
        Integer
            Clase envolvente (Wrapper Class): Integer es una clase envolvente proporcionada por el paquete java.lang. Se utiliza para envolver un valor de tipo int en un objeto.
            Almacenamiento: Los objetos Integer se almacenan en el montón (heap) de memoria.
            Tamaño: Además de los 32 bits para el valor entero, hay un overhead adicional debido a la naturaleza de objeto de Integer.
            Valor por defecto: Si se declara sin inicializar, su valor por defecto es null.
            Operaciones: No puede realizar operaciones aritméticas directamente; primero necesita desempaquetarse (unboxing).
            Integer a = 5;
            Integer b = 10;
            Integer c = a + b; // Esto realiza un unboxing automático

            Boxing y Unboxing: Integer permite el autoboxing y unboxing, lo que significa que Java puede convertir automáticamente entre int e Integer.
            Integer obj = 10;  // autoboxing
            int num = obj;     // unboxing

            Usos en Colecciones: Las colecciones de Java (como ArrayList, HashMap, etc.) no pueden utilizar tipos primitivos, por lo que es necesario utilizar tipos envolventes como Integer.
            Métodos Utilitarios: La clase Integer proporciona varios métodos utilitarios, como parseInt, valueOf, compare, etc., que no están disponibles para los tipos primitivos.
            int num = Integer.parseInt("123");
            Integer maxVal = Integer.max(10, 20);
            Valores Nulos: Un Integer puede ser null, lo cual es útil para representar la ausencia de un valor. Un int siempre tiene un valor (incluso si es el valor por defecto 0).
            Integer obj = null;
            int num = 0;

            Clases envolventes:
            byte -> Byte
            short -> Short
            int -> Integer
            long -> Long
            float -> Float
            double -> Double
            char -> Character
            boolean -> Boolean

            Ejemplos:
            Byte byteObject = 10;
            byte b = byteObject; // unboxing

            Short shortObject = 1000;
            short s = shortObject; // unboxing

            Integer intObject = 10000;
            int i = intObject; // unboxing

            Long longObject = 100000L;
            long l = longObject; // unboxing

            Float floatObject = 10.5f;
            float f = floatObject; // unboxing


            Double doubleObject = 100.5;
            double d = doubleObject; // unboxing

            Double
            Double.parseDouble(String s): Convierte una cadena a un doble.
            Double.isNaN(double v): Verifica si el valor es NaN.

            Character charObject = 'A';
            char c = charObject; // unboxing

            Boolean
            Boolean.parseBoolean(String s): Convierte una cadena a un booleano.
            Boolean.valueOf(boolean b): Devuelve una instancia de Boolean representando el valor 

            Comparacion de enteros:
            int result = Integer.compare(10, 20);

            Verificación de NaN en Doble
            double value = 0.0 / 0.0;
            boolean isNaN = Double.isNaN(value);

            Conversión de Cadena a Booleano
            String str = "true";
            boolean bool = Boolean.parseBoolean(str);
        */

        // definir una constante en JAVA
        final int CONSTANTE = 10;
        System.out.println(CONSTANTE);

        // operadores aritmeticos
        int a = 10;
        int b = 20;
        int suma = a + b;
        int resta = a - b;
        int multiplicacion = a * b;
        int division = a / b;
        int modulo = a % b;
        System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);
        System.out.println("Multiplicacion: " + multiplicacion);
        System.out.println("Division: " + division);
        System.out.println("Modulo: " + modulo);

        // convertir string a numero
        String numeroString = "10";
        int numeroInt = Integer.parseInt(numeroString);
        System.out.println(numeroInt);
        // convertir string a float o double
        String numeroStringFloat = "10.5";
        float numeroFloat = Float.parseFloat(numeroStringFloat);
        System.out.println(numeroFloat);
        // convertir string a char
        String letraString = "a";
        char letraChar = letraString.charAt(0);
        System.out.println(letraChar);

        // operadores de comparacion
        int c = 10;
        int d = 20;
        boolean igual = c == d;
        boolean distinto = c != d;
        boolean mayor = c > d;
        boolean menor = c < d;
        boolean mayorIgual = c >= d;
        boolean menorIgual = c <= d;
        System.out.println("Igual: " + igual);
        System.out.println("Distinto: " + distinto);
        System.out.println("Mayor: " + mayor);
        System.out.println("Menor: " + menor);
        System.out.println("MayorIgual: " + mayorIgual);
        System.out.println("MenorIgual: " + menorIgual);

        // operadores logicos
        boolean e = true;
        boolean f = false;
        boolean and = e && f;
        boolean or = e || f;
        boolean not = !e;
        System.out.println("And: " + and);
        System.out.println("Or: " + or);
        System.out.println("Not: " + not);

        // operadores de asignacion
        int g = 10;
        int h = 20;
        g += h;
        System.out.println("Asignacion: " + g);

        // operadores de incremento y decremento
        int i = 10;
        i++;
        System.out.println("Incremento: " + i);
        int j = 10;
        j--;
        System.out.println("Decremento: " + j);

        // operadores condicionales
        int k = 10;
        int l = 20;
        int m = (k > l) ? k : l;
        System.out.println("Condicionales: " + m);

        // operadores de bits
        int n = 10;
        int o = 20;
        int p = n & o;
        int q = n | o;
        int r = n ^ o;
        int s = ~n;
        int t = n << 2;
        int u = n >> 2;
        int v = n >>> 2;
        System.out.println("And: " + p);
        System.out.println("Or: " + q);
        System.out.println("Xor: " + r);
        System.out.println("Not: " + s);
        System.out.println("DesplazamientoIzquierda: " + t);
        System.out.println("DesplazamientoDerecha: " + u);
        System.out.println("DesplazamientoDerechaSinSigno: " + v);

        // operadores de instancia
        String w = "Hola Mundo";
        boolean instancia = w instanceof String;
        System.out.println("Instancia: " + instancia);

        
        Integer x = 10;
        boolean tipo = x instanceof Integer;
        System.out.println("Tipo: " + tipo);

       // arrays y arraysList
     /*  Arrays
        Descripción
        Un array es una estructura de datos que almacena una colección fija de elementos del mismo tipo.

        Pros:
        Eficiencia: Los arrays son muy eficientes en términos de acceso y modificación de elementos.
        Simplicidad: Su sintaxis es simple y directa.
        Memoria Contigua: Almacenan los elementos en memoria contigua, lo que mejora la localización y el acceso en algunos casos.

        Contras:
        Tamaño Fijo: El tamaño del array se debe especificar en el momento de su creación y no puede cambiarse.
        Funcionalidad Limitada: No tienen métodos para operaciones avanzadas como búsqueda, adición o eliminación de elementos.
        No pueden almacenar tipos genéricos: Aunque pueden almacenar cualquier tipo de objeto, no pueden trabajar con tipos genéricos como ArrayList.

        Declaración e inicialización de un array de enteros
        int[] array = new int[5];
        array[0] = 10;
        int value = array[0];


        ArrayList
        Descripción
        ArrayList es una clase del framework de colecciones de Java que implementa la interfaz List. Es una lista que puede cambiar de tamaño dinámicamente.

        Pros:
        Tamaño Dinámico: Puede crecer y reducirse dinámicamente según sea necesario.
        Métodos Utilitarios: Proporciona métodos para operaciones comunes como agregar, eliminar, buscar y ordenar elementos.
        Tipo Genérico: Puede manejar tipos genéricos, permitiendo el uso con cualquier tipo de objeto.
        Integración con el Framework de Colecciones: Se integra bien con otros componentes del framework de colecciones de Java.
        Contras:
        Rendimiento: Es menos eficiente en términos de acceso y modificación de elementos comparado con los arrays debido a la sobrecarga adicional.
        Sobrecarga de Memoria: Tiene una mayor sobrecarga de memoria debido a la estructura adicional y la capacidad dinámica.
        Autoboxing/Unboxing: En el caso de tipos primitivos, se requiere autoboxing/unboxing, lo que puede afectar el rendimiento.

        Declaración e inicialización de un ArrayList de enteros
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(10);
        int value = arrayList.get(0);

        Cuándo usar cada uno
        Uso de Arrays
        Cuando el tamaño de la colección es fijo y conocido de antemano.
        Para operaciones que requieren alta eficiencia en términos de acceso y modificación.
        Cuando se trabaja con datos de tipo primitivo y se necesita minimizar la sobrecarga de memoria.
         
        Uso de ArrayList
        Cuando el tamaño de la colección puede cambiar dinámicamente.
        Para operaciones que requieren métodos utilitarios como agregar, eliminar y buscar elementos.
        Cuando se necesita trabajar con tipos genéricos y aprovechar el framework de colecciones de Java.


        */

        // condicionales 
        int edad = 18;
        if(edad >= 18){
            System.out.println("Es mayor de edad");
        }else{
            System.out.println("Es menor de edad");
        }
        // condicional anidado
        int hora = 10;
        if(hora < 12){
            System.out.println("Buenos dias");
        }else{
            if(hora < 20){
                System.out.println("Buenas tardes");
            }else{
                System.out.println("Buenas noches");
            }
        }



        // condicionales multiples
        int dia = 1;
        switch(dia){
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miercoles");
                break;
            case 4:
                System.out.println("Jueves");
                break;
            case 5:
                System.out.println("Viernes");
                break;
            case 6:
                System.out.println("Sabado");
                break;
            case 7:
                System.out.println("Domingo");
                break;
            default:
                System.out.println("Dia no valido");
                break;
        }
        // bucles
        // bucle for
        for(int z = 0; z < 10; z++){
            System.out.println(z);
        }
        // bucle while
        int y = 0;
        while(y < 10){
            System.out.println(y);
            y++;
        }
        // bucle do while
        int z = 0;
        do{
            System.out.println(z);
            z++;
        }while(z < 10);
        
        // bucle for each
        int[] numeros = {1,2,3,4,5};
        for(int cadaNumero : numeros){
            System.out.println(cadaNumero);
        }
        //blucle for each con arraylist tengo que importar import java.util.ArrayList;
        ArrayList<Integer> numerosArrayList = new ArrayList<>();

        numerosArrayList.add(1);
        numerosArrayList.add(2);
        numerosArrayList.add(3);
        numerosArrayList.add(4);
        numerosArrayList.add(5);
        for(int cadaNumero : numerosArrayList){
            System.out.println(cadaNumero);
        }

         // Métodos de ArrayList
         ArrayList<Integer> numerosArrayList2 = new ArrayList<>();
        
         // Añade el número 1 al ArrayList
         numerosArrayList2.add(1);
         // Añade el número 2 al ArrayList
         numerosArrayList2.add(2);
         // Añade el número 3 al ArrayList
         numerosArrayList2.add(3);
         // Añade el número 4 al ArrayList
         numerosArrayList2.add(4);
         // Añade el número 5 al ArrayList
         numerosArrayList2.add(5);
         
         // Imprime el contenido del ArrayList. Resultado: [1, 2, 3, 4, 5]
         System.out.println(numerosArrayList2);
         
         // Elimina el elemento en el índice 2 (número 3)
         numerosArrayList2.remove(2);
         // Imprime el contenido del ArrayList después de la eliminación. Resultado: [1, 2, 4, 5]
         System.out.println(numerosArrayList2);
         
         // Establece el valor en el índice 2 a 10 (reemplaza el número 4)
         numerosArrayList2.set(2, 10);
         // Imprime el contenido del ArrayList después de la modificación. Resultado: [1, 2, 10, 5]
         System.out.println(numerosArrayList2);
         
         // Imprime el valor en el índice 2. Resultado: 10
         System.out.println(numerosArrayList2.get(2));
         
         // Imprime el tamaño del ArrayList. Resultado: 4
         System.out.println(numerosArrayList2.size());
         
         // Verifica si el ArrayList contiene el número 10. Resultado: true
         System.out.println(numerosArrayList2.contains(10));
         
         // Devuelve el índice de la primera aparición del número 10 en el ArrayList. Resultado: 2
         System.out.println(numerosArrayList2.indexOf(10));
         
         // Elimina todos los elementos del ArrayList
         numerosArrayList2.clear();

         // Métodos de Arrays
         int[] numerosArray = {1, 2, 3, 4, 5};

         // Imprimir el array como cadena
         // Arrays.toString convierte el array a una cadena de caracteres para una fácil visualización
         System.out.println("Array original: " + Arrays.toString(numerosArray));
 
         // Ordenar el array
         // Arrays.sort ordena los elementos del array en orden ascendente
         Arrays.sort(numerosArray);
         System.out.println("Array ordenado: " + Arrays.toString(numerosArray));
 
         // Buscar un elemento en el array (debe estar ordenado previamente)
         // Arrays.binarySearch busca un elemento en el array ordenado y devuelve su índice
         int index = Arrays.binarySearch(numerosArray, 3);
         System.out.println("Índice del elemento 3: " + index);
 
         // Llenar el array con un valor específico
         // Arrays.fill llena todo el array con el valor especificado
         Arrays.fill(numerosArray, 10);
         System.out.println("Array después de Arrays.fill: " + Arrays.toString(numerosArray));
 
         // Copiar el array a uno nuevo
         // Arrays.copyOf copia el array original a uno nuevo con la longitud especificada
         int[] copiaArray = Arrays.copyOf(numerosArray, numerosArray.length);
         System.out.println("Copia del array: " + Arrays.toString(copiaArray));
 
         // Comparar dos arrays
         // Arrays.equals compara dos arrays para ver si son iguales (mismos elementos en el mismo orden)
         boolean iguales = Arrays.equals(numerosArray, copiaArray);
         System.out.println("Los arrays son iguales: " + iguales);
 
         // Crear un subarray
         // Arrays.copyOfRange copia un rango del array original a un nuevo array
         // El índice 'from' es inclusivo y 'to' es exclusivo
         int[] subArray = Arrays.copyOfRange(numerosArray, 1, 4);
         System.out.println("Subarray (índices 1 a 3): " + Arrays.toString(subArray));


    }
}
