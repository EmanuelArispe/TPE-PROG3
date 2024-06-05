package src;

import org.jetbrains.annotations.NotNull;
import src.Tarea;
import src.backtraking.Backtraking;
import src.backtraking.Estado;
import src.greedy.Greedy;
import src.utils.CSVReader;

import java.util.*;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
    private HashMap<String, Tarea> almacenTareas;
    private LinkedList<Procesador> almacenProcesadores;
    private LinkedList<Tarea> tareasCriticas;
    private LinkedList<Tarea> tareasNoCriticas;
    private LinkedList<Tarea> listTareas;

    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Servicios(String pathProcesadores, String pathTareas) {
        almacenTareas = new HashMap<String, Tarea>();
        tareasCriticas = new LinkedList<Tarea>();
        tareasNoCriticas = new LinkedList<Tarea>();
        listTareas = new LinkedList<Tarea>();
        CSVReader reader = new CSVReader();
        almacenProcesadores = reader.readProcessors(pathProcesadores);
        reader.readTasks(pathTareas, almacenTareas, tareasCriticas, tareasNoCriticas, listTareas);
    }

    private LinkedList<Tarea> getTareasCriticas() {
        return tareasCriticas;
    }

    private LinkedList<Tarea> getTareasNoCriticas() {
        return tareasNoCriticas;
    }

    private LinkedList<Procesador> getAlmacenProcesadores() {
        return almacenProcesadores;
    }

    private HashMap<String, Tarea> getAlmacenTareas() {
        return almacenTareas;
    }

    private LinkedList<Tarea> getListTareas() {
        return listTareas;
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     */
    public Tarea servicio1(String ID) {
        return new Tarea(getAlmacenTareas().get(ID).getId(), getAlmacenTareas().get(ID).getNombre(),
                getAlmacenTareas().get(ID).getTiempo(), getAlmacenTareas().get(ID).getCritica(),
                getAlmacenTareas().get(ID).getPrioridad());
    }

    /*
     * La complejidad computacional de la función servicio2 es O(1).
     * Esto se debe a que la operación Collections.unmodifiableList simplemente crea una vista inmutable de la lista original
     * sin copiar sus elementos, y esta operación es de tiempo constante.
     */
    public List<Tarea> servicio2(boolean esCritica) {
        return esCritica ? getTareasCriticas() : getTareasNoCriticas();
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
        List<Tarea> listPrioridadTareas = new LinkedList<Tarea>();

        getListTareas().iterator().forEachRemaining(nextTarea -> {
            if (nextTarea.estoyEnRango(prioridadInferior, prioridadSuperior)) {
                listPrioridadTareas.add(new Tarea(nextTarea.getId(),
                        nextTarea.getNombre(), nextTarea.getTiempo(),
                        nextTarea.getCritica(), nextTarea.getPrioridad()));
            }
        });
        return listPrioridadTareas;
    }


    public Estado servicioBacktraking(int tiempo) {
        return new Backtraking().backtraking(tiempo, getTareasCriticas(), getTareasNoCriticas(), getAlmacenProcesadores());
    }

    public Estado servicioGreedy(int tiempo){
        return new Greedy().greedy(tiempo,getTareasCriticas(),getTareasNoCriticas(),getAlmacenProcesadores());
    }

}
