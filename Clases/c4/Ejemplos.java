package prueba;

public class Ejemplos {

    public static void main(String[] args) {
      /*  int edad = 32;
        String edadDeMiHno = "31";
       // boolean esMayor = edad == edadDeMiHno; // false, porque edad es int y edadDeMiHno es String
        boolean esIgual = edad == Integer.parseInt(edadDeMiHno);
        System.out.println("¿10 es igual a 20? " + esIgual); // false*/ 

        int edad = 12;
        boolean esAdmin = true;
       /* if ( edad >= 18 && esAdmin) {
            System.out.println("1-Puede ingresar al sistema");
        }else{ // cuando de false el condicional que tome este camino
            System.out.println("2-NO Puede ingresar al sistema");
        } */
       // queremos saber si es mayor de edad--> entrar al sistema
       // si no es mayor de eddad pero es admin--> entrar al sistema
       // si no es admin y no es mayor de edad--> no entra al sistema

    /*     if ( edad >= 18 || esAdmin) {
            System.out.println("1-Puede ingresar al sistema");
        }else{ // cuando de false el condicional que tome este camino
            System.out.println("2-NO Puede ingresar al sistema");
        } 

        if(edad >= 18 ){
            System.out.println("1-Puede ingresar al sistema");
        }else if(esAdmin){
            System.out.println("2-Puede ingresar al sistema");
        }else{
            System.out.println("3-NO Puede ingresar al sistema");
        }
    
        int k = 10;
        int l = 20;
        int m = (k > l) ? k : l;
        System.out.println("Condicionales: " + m);*/
       // () lo que se evalua
       // despues ? el resultado si es true
         // : el resultado si es false
       /* String mensaje = (edad >= 18) ? "Puede ingresar al sistema" : "NO Puede ingresar al sistema";
        System.out.println("Condicionales: " + mensaje);*/ 

       /*  System.out.println("1"); 
        System.out.println("1"); 
        System.out.println("1"); 
        System.out.println("1"); 
        System.out.println("1"); 
        System.out.println("1"); 
        System.out.println("1"); 
        System.out.println("1"); 
        System.out.println("1"); 
        System.out.println("1"); */
        // para optimizar las lineas de codigo anteriores, vamos a usar un bucle
        // sabemos la cantidad de veces que se tiene que ejecutar el codigo 
        // for(declaracion y asignacion de valor a la variable que se evalua en el for;
            // condicion que se tiene que cumplir para que el for siga ejecutandose;
            // incremento o decremento de la variable que se evalua en el for, pero se incrementa o decrementa al final del bloque)
        for (int vuelta=1; vuelta<=10; vuelta++){
            System.out.println(vuelta);
        }
        // tarea para el hogar, 10 al 1
        // evaluar si un numero es primo
    }

}
