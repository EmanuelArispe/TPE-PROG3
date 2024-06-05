package src.greedy;

import src.Procesador;
import src.Tarea;
import src.backtraking.Estado;

import java.util.Iterator;
import java.util.LinkedList;

public class Greedy {

    private Estado solucion;

    public Greedy() {
        this.solucion = new Estado(0);
    }

    public Estado getSolucion() {
        return solucion;
    }

    public Estado greedy(int tiempo, LinkedList<Tarea> criticas, LinkedList<Tarea> noCritica, LinkedList<Procesador> listProd){
        if(criticas.size()/2 > listProd.size()) return getSolucion();
        criticas.addAll(noCritica);
        greedySolucion(tiempo,criticas,listProd);
        return getSolucion();
    }

    private void greedySolucion(int tiempo, LinkedList<Tarea> listTareas, LinkedList<Procesador> listProd){
        Tarea nextTarea = obtenerTarea(listTareas);
        while(nextTarea != null){
            Procesador newProd = obtenerProcesadorFactible(tiempo, listProd, nextTarea);
            if(newProd == null){
             return;
             // en caso de que  no haya solucion que hagoooo aca ??? como controlo para que no retorne una solucion parcial
            }
            getSolucion().addTarea(nextTarea,newProd);
            listTareas.remove(nextTarea);
            listProd.remove(newProd);
            nextTarea = obtenerTarea(listTareas);
        }
    }

    private Procesador obtenerProcesadorFactible(int tiempo, LinkedList<Procesador> listProd, Tarea nextTarea){
        Iterator<Procesador> list = listProd.iterator();
        Procesador prodFactible = null;
        while (list.hasNext()){
            Procesador newProd = list.next();
            if((prodFactible == null)||(cumpleCondicion(newProd,nextTarea,tiempo) && (newProd.compareTo(prodFactible) < 0))){
                prodFactible = newProd;
            }
        }
        return prodFactible;
    }

    private Tarea obtenerTarea(LinkedList<Tarea> listTareas){
        Iterator<Tarea> list = listTareas.iterator();
        Tarea tareaMasPesada = null;
        while(list.hasNext()){
            Tarea newTarea = list.next();
            if(( tareaMasPesada == null) ||(newTarea.compareTo(tareaMasPesada) > 0)){
                tareaMasPesada = newTarea;
            }
        }
        return tareaMasPesada;
    }

    private boolean cumpleCondicion(Procesador prod, Tarea tarea, int tiempo) {
        return !superaLimiteTareasCriticas(prod, tarea) && !excedeTiempoSinRefrigeracion(prod, tarea, tiempo);
    }

    private boolean superaLimiteTareasCriticas(Procesador prod, Tarea tarea) {
        return tarea.getCritica() && prod.limiteCriticas();
    }
    // Si da true esta OK

    private boolean excedeTiempoSinRefrigeracion(Procesador prod, Tarea tarea, Integer tiempo) {
        return !prod.getRefrigerado() && tarea.getTiempo() > tiempo;
    }
}
