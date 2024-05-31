package src;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Procesador {

    private String id;
    private String codigo;
    private Boolean refrigerado;
    private Integer anio;
    private LinkedList<Tarea> listTareas;
    private Integer tiempoProcesamiento;


    public Procesador(String id, String codigo, Boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
        this.tiempoProcesamiento = 0;
        listTareas = new LinkedList<>();

    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Boolean getRefrigerado() {
        return refrigerado;
    }

    public Integer getAnio() {
        return anio;
    }

    public Integer getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    public List<Tarea> getListTareas() {
        return Collections.unmodifiableList(listTareas);
    }

    private void setTiempoProcesamiento(Integer tiempoProcesamiento) {
        this.tiempoProcesamiento = tiempoProcesamiento;
    }

    public void  addTarea(Tarea newTarea){
        setTiempoProcesamiento(getTiempoProcesamiento() + newTarea.getTiempo());
        listTareas.addFirst(newTarea);
    }

    public void deleteTarea(Tarea oldTarea){
        setTiempoProcesamiento(getTiempoProcesamiento() - oldTarea.getTiempo());
        listTareas.remove(oldTarea);
    }





    @Override
    public String toString() {
        return "Procesador{" +
                "id='" + id + '\'' +
                ", refrigerado= " + refrigerado +
                ", Lista de Tareas= " + listTareas.toString() +
                '}';
    }
}
