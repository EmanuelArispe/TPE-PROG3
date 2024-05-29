package src.backtraking;

import src.Procesador;
import src.Tarea;

import java.util.HashMap;
import java.util.Iterator;

public class Estado {
    private int cantEstado;

    private HashMap<String, Procesador> solucionActual;
    private int tiempoSolucionActual;
    private HashMap<String, Procesador> mejorSolucion;
    private int tiempoMejorSolucion;

    public Estado() {

        solucionActual = new HashMap<String, Procesador>();
        mejorSolucion = new HashMap<String, Procesador>();
        setTiempoSolucionActual(0);
        setTiempoMejorSolucion(-1);
        setCantEstado(0);
    }

    public int getTiempoSolucionActual() {
        return tiempoSolucionActual;
    }
    public int getTiempoMejorSolucion() {
        return tiempoMejorSolucion;
    }
    public int getCantEstado() {
        return cantEstado;
    }
    private void setTiempoSolucionActual(int tiempoSolucioActual) {
        this.tiempoSolucionActual = tiempoSolucioActual;
    }
    private void setTiempoMejorSolucion(int tiempoMejorSolucion) {
        this.tiempoMejorSolucion = tiempoMejorSolucion;
    }

    private void setCantEstado(int cantEstado) {
        this.cantEstado = cantEstado;
    }

    public void addTarea(Tarea newTarea, Procesador prod){
        setTiempoSolucionActual(getTiempoSolucionActual() + newTarea.getTiempo());
        if(!solucionActual.containsKey(prod)){
            solucionActual.put(prod.getId(),prod);
        }
        solucionActual.get(prod.getId()).addTarea(newTarea);
    }

    public void removeTarea(Tarea oldTarea, Procesador prod){
        setTiempoSolucionActual(getTiempoSolucionActual() - oldTarea.getTiempo());
        solucionActual.get(prod.getId()).deleteTarea(oldTarea);
    }


    public void sumarEstado(){setCantEstado(getCantEstado() + 1);}

    public void setMejorSolucion (){
        setTiempoMejorSolucion(getTiempoSolucionActual());
        mejorSolucion = solucionActual; // HACER LA COPIA DE UN HASH A OTRO
    }

    public boolean esMejorSolucion(){
        if(getTiempoMejorSolucion() == -1){
            return true;
        }
        return getTiempoSolucionActual() < getTiempoMejorSolucion();
    }



    @Override
    public String toString() {
        return "Estado{" +
                "cantEstado=" + cantEstado +
                ", mejorSolucion=" + mejorSolucion +
                ", tiempoMejorSolucion=" + tiempoMejorSolucion +
                '}';
    }
}
