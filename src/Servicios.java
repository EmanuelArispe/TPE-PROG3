package src;

import org.jetbrains.annotations.NotNull;
import src.Tarea;
import src.utils.CSVReader;

import java.util.*;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
    private HashMap<String,Tarea> almacenTareas;
    private HashMap<String,Procesador> almacenProcesadores;
    private LinkedList<Tarea> tareasCriticas;
    private LinkedList<Tarea> tareasNoCriticas;
    private LinkedList<Tarea> listTareas;
    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Servicios(String pathProcesadores, String pathTareas){
        almacenTareas = new HashMap<String, Tarea>();
        almacenProcesadores = new HashMap<String, Procesador>();
        tareasCriticas = new LinkedList<>();
        tareasNoCriticas = new LinkedList<>();
        listTareas = new LinkedList<>();
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores,almacenProcesadores);
        reader.readTasks(pathTareas,almacenTareas,tareasCriticas,tareasNoCriticas, listTareas);
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     */
    public Tarea servicio1(String ID) {
        return new Tarea(   almacenTareas.get(ID).getId(),almacenTareas.get(ID).getNombre(),
                            almacenTareas.get(ID).getTiempo(),almacenTareas.get(ID).getCritica(),
                            almacenTareas.get(ID).getPrioridad());}

    /*
     * La complejidad computacional de la función servicio2 es O(1).
     * Esto se debe a que la operación Collections.unmodifiableList simplemente crea una vista inmutable de la lista original
     * sin copiar sus elementos, y esta operación es de tiempo constante.
     */
    public List<Tarea> servicio2(boolean esCritica) {
        return esCritica ? Collections.unmodifiableList(this.tareasCriticas) : Collections.unmodifiableList(this.tareasNoCriticas);
    }

    /*
     * La complejidad computacional de la función servicio3 es O(n).
     * Esto se debe a que la función itera sobre todos los elementos del iterador una vez (O(n)) y para cada elemento que cumple la condición,
     * realiza una operación de copia que tiene una complejidad constante (O(1)).
     * Por lo tanto, la complejidad total de la función es lineal respecto al número de tareas en el iterador (O(n)).
     * Cabe aclarar que se opto por esta implementancion porque siempre va a ser O(n) y la implementacion del codigo y la estructura no son complejas
     * pero existen otras opticiones como crear un arbol binario donde se ordena por la prioridad aunque en el peor de los casos seria O(n) aunque
     * tendria casos que se asemejarian a (Log2 n).
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        Iterator<Tarea> listaTareas = obtenerTareas();
        List<Tarea> listPrioridadTareas = new LinkedList<Tarea>();

        listaTareas.forEachRemaining(nextTarea -> {if(nextTarea.estoyEnRango(prioridadInferior,prioridadSuperior)){
                                                    listPrioridadTareas.add(  new Tarea(nextTarea.getId(),
                                                                                        nextTarea.getNombre(), nextTarea.getTiempo(),
                                                                                        nextTarea.getCritica(),nextTarea.getPrioridad()));}});
        return listPrioridadTareas;
    }



    private @NotNull Iterator<Tarea> obtenerTareas(){
        return almacenTareas.values().iterator();
    }

    private @NotNull Iterator<Procesador> obtenerProcesadores(){return almacenProcesadores.values().iterator();}


}
