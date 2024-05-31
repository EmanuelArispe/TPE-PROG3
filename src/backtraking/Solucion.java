package src.backtraking;

import src.Procesador;
import src.Tarea;

import java.util.HashMap;

public class Solucion {


    private HashMap<String, Procesador> solucion;
    private int tiempoSolucion;


    public Solucion(int valor){
        solucion = new HashMap<String, Procesador>();
        setTiempoSolucion(valor);
    }

    public int getTiempoSolucion() {
        return tiempoSolucion;
    }

    private void setTiempoSolucion(int tiempoSolucioActual) {
        this.tiempoSolucion = tiempoSolucioActual;
    }

    public void addTarea(Tarea newTarea, Procesador prod){
        setTiempoSolucion(getTiempoSolucion() + newTarea.getTiempo());
        if(!solucion.containsKey(prod)){
            solucion.put(prod.getId(),prod);
        }
        solucion.get(prod.getId()).addTarea(newTarea);
    }

    public void removeTarea(Tarea oldTarea, Procesador prod){
        setTiempoSolucion(getTiempoSolucion() - oldTarea.getTiempo());
        solucion.get(prod.getId()).deleteTarea(oldTarea);
    }

    @Override
    public String toString() {
        return "Estado{" +
                ", Solucion=" + solucion +
                ", tiempoSolucion=" + getTiempoSolucion() +
                '}';
    }
}
