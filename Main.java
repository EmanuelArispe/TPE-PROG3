import src.Servicios;

import java.util.Random;

public class Main {

    public static void main(String args[]) {
        Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");

        /*System.out.println("Imprimo Tarea");
        System.out.println(servicios.servicio1("T2").toString());
        System.out.println("Servicios con prioridad entre 50 y 100 " );
        servicios.servicio3(50,100).forEach(tarea -> System.out.println(tarea.toString()));
        System.out.println("Servicios No criticoos");
        servicios.servicio2(false).forEach(tarea -> System.out.println(tarea.toString()));

         */
        System.out.println("Backtraking");
        servicios.backtraking(20);
        /*
        for (int i = 5; i < 50; i++){
            if(i%2 == 0){System.out.println("T"+i+";Tarea"+i+";"+((Integer)(i+i)/3+1) +";true" +";"+ (i*3)/2 );}
            else{System.out.println("T"+i+";Tarea"+i+";"+((Integer)(i+i)/3+1) +";false" +";"+ (i*3)/2 );}

        }
         */
    }
}
