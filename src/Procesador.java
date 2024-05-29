package src;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class Procesador {

    private String id;
    private String codigo;
    private Boolean refrigerado;
    private Integer anio;
    private LinkedList<Tarea> listTareas;


    public Procesador(String id, String codigo, Boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
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

    public void  addTarea(Tarea newTarea){
        listTareas.addFirst(newTarea);
    }

    public void deleteTarea(Tarea oldTarea){
        listTareas.remove(oldTarea);
    }

    public boolean cumpleCondicion(Tarea tarea, int tiempo) {
        return !(sonTareasCriticas(tarea) || excedeTiempoSinRefrigeracion(tarea, tiempo));
    }

    private boolean sonTareasCriticas(Tarea tarea) {
        return tarea.getCritica() && !listTareas.isEmpty() && listTareas.getFirst().getCritica();
    }

    private boolean excedeTiempoSinRefrigeracion(Tarea tarea, int tiempo) {
        return !getRefrigerado() && tarea.getTiempo() < tiempo;
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
