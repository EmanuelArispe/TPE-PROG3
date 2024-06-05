package src.utils;

import src.Procesador;
import src.Tarea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class CSVReader {

    public CSVReader() {
    }

    public void readTasks(String taskPath, HashMap<String, Tarea> almacenTareas, LinkedList<Tarea> tareasCriticas, LinkedList<Tarea> tareasNoCriticas, LinkedList<Tarea> listTareas) {

        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent(taskPath);

        for (String[] line : lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            Tarea newTarea = createTarea(line);
            // Aca instanciar lo que necesiten en base a los datos leidos
            if (!almacenTareas.containsKey(newTarea.getId())) {
                almacenTareas.put(newTarea.getId(), newTarea);
                listTareas.add(newTarea);
                if (newTarea.getCritica()) {
                    tareasCriticas.add(newTarea);
                } else {
                    tareasNoCriticas.add(newTarea);

                }
            }
        }
    }

    private Tarea createTarea(String[] line) {
        return new Tarea(line[0].trim(), line[1].trim(), Integer.parseInt(line[2].trim()), Boolean.parseBoolean(line[3].trim()), Integer.parseInt(line[4].trim()));
    }

    public LinkedList<Procesador> readProcessors(String processorPath) {

        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent(processorPath);
        LinkedList<Procesador> listProd = new LinkedList<Procesador>();
        for (String[] line : lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            Procesador newProcesador = createProcesador(line);
            listProd.add(newProcesador);
            // Aca instanciar lo que necesiten en base a los datos leidos
        }
        return listProd;
    }

    private Procesador createProcesador(String[] line) {
        return new Procesador(line[0].trim(), line[1].trim(), Boolean.parseBoolean(line[2].trim()), Integer.parseInt(line[3].trim()));
    }

    private ArrayList<String[]> readContent(String path) {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        return lines;
    }

}
