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
        System.out.println(servicios.servicioBacktraking(5).toString());

    }


}
