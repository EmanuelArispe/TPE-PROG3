package src.backtraking;

import src.Procesador;
import src.Tarea;

import java.util.Iterator;
import java.util.LinkedList;

public class Backtraking {

    private Solucion mejorSolucion;

    public Solucion backtraking(int tiempo,LinkedList<Tarea> listTareas){
        mejorSolucion = new Solucion(-1);
        backtraking(tiempo,new Estado(),listTareas);
        return mejorSolucion;
    }

    private void backtraking(int tiempo, Estado estado, LinkedList<Tarea> listTareas){
        estado.sumarEstado();
        if(listTareas.isEmpty()){
            estado.sumarEstadoFinales();
            if(esMejorSolucion(estado)){
                setMejorSolucion(estado);
            }
        }else {
            Tarea newTarea = listTareas.removeFirst();
            // VER ESTO !!!
            Iterator<Procesador> listProd = obtenerProcesadores();

            while(listProd.hasNext()){
                Procesador newProd = listProd.next();
                if(cumpleCondicion(newProd,newTarea,tiempo)){
                    estado.getSolucionActual().addTarea(newTarea,newProd);
                    backtraking(tiempo,estado,listTareas);
                    estado.getSolucionActual().removeTarea(newTarea,newProd);
                }
            }
        }
    }

    private boolean cumpleCondicion(Procesador prod,Tarea tarea, int tiempo) {
        return !(sonTareasCriticas(prod,tarea) || excedeTiempoSinRefrigeracion(prod, tarea, tiempo));
    }

    private boolean sonTareasCriticas(Procesador prod, Tarea tarea) {
        return tarea.getCritica() && !prod.getListTareas().isEmpty() && prod.getListTareas().getFirst().getCritica();
    }

    private boolean excedeTiempoSinRefrigeracion(Procesador prod, Tarea tarea, int tiempo) {
        return !prod.getRefrigerado() && tarea.getTiempo() < tiempo;
    }

    private boolean esMejorSolucion(Estado estado){
        if(mejorSolucion.getTiempoSolucion() == -1){
            return true;
        }
        return estado.getSolucionActual().getTiempoSolucion() < mejorSolucion.getTiempoSolucion();
    }

    private void setMejorSolucion(Estado estado){
        mejorSolucion = estado.getSolucionActual();
    }
}
