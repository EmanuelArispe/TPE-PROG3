package src;

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
    private HashMap<String, Procesador> almacenProc;
    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Servicios(String pathProcesadores, String pathTareas){
        almacenTareas = new HashMap<String, Tarea>();
        almacenProc = new HashMap<String, Procesador>();
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores,almacenProc);
        reader.readTasks(pathTareas,almacenTareas);
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     */
    public Tarea servicio1(String ID) {
        return new Tarea(   almacenTareas.get(ID).getId(),almacenTareas.get(ID).getNombre(),
                            almacenTareas.get(ID).getTiempo(),almacenTareas.get(ID).getCritica(),
                            almacenTareas.get(ID).getPrioridad());}

    /*
     * Expresar la complejidad temporal del servicio 2.
     */
    public List<Tarea> servicio2(boolean esCritica) {
        Iterator<Tarea> listaTareas = obtenerTareas();
        List<Tarea> listCritica = new LinkedList<Tarea>();

        listaTareas.forEachRemaining(newTarea -> {if(newTarea.getCritica().equals(esCritica))
                                                    listCritica.add(new Tarea(  newTarea.getId(),
                                                                                newTarea.getNombre(), newTarea.getTiempo(),
                                                                                newTarea.getCritica(),newTarea.getPrioridad()));});
        return listCritica;
    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        Iterator<Tarea> listaTareas = obtenerTareas();
        List<Tarea> listPrioridadTareas = new LinkedList<Tarea>();

        listaTareas.forEachRemaining(tarea -> {if(tarea.estoyEnRango(prioridadInferior,prioridadSuperior)){
                                                    listPrioridadTareas.add(  new Tarea(tarea.getId(),
                                                                                        tarea.getNombre(), tarea.getTiempo(),
                                                                                        tarea.getCritica(),tarea.getPrioridad()));}});
        return listPrioridadTareas;
    }



    private Iterator<Tarea> obtenerTareas(){
        return almacenTareas.values().iterator();
    }

}
