package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvException;

import tables.LogEvent;

public class CSVEventHandler {

    private final Lock eventLock = new ReentrantLock();

    public void saveEvent(LogEvent event, String tableName) throws IOException {
        eventLock.lock();
        try {
            // Verificar si el archivo CSV existe
            File file = new File(tableName);
            if (!file.exists()) {
                // Si el archivo no existe, crear uno nuevo
                createNewCSVFile(tableName);
            } else {
                // Si el archivo existe, verificar si está vacío
                if (file.length() == 0) {
                    // Si el archivo está vacío, escribir la línea requerida en la primera línea
                    try {
                        FileWriter writer = new FileWriter(file);
                        writer.write("robotId,timestamp,avenue,street,sirens\n");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            int robotId = event.getRobotId();

            // El robotId no existe, crea una nueva entrada en el archivo CSV
            String newLine =
                    //event.getLogId() + "," +
                    robotId + "," +
                    event.getTimeStamp().format(formatter) + "," +
                    event.getAvenue() + "," +
                    event.getStreet() + "," +
                    event.getSirens();
            appendLineToFile(tableName, newLine);
        } finally {
            eventLock.unlock();
        }
    }

    public void updateEvent(LogEvent event, String tableName) throws IOException {
        eventLock.lock();
        try {
            // Configurar el formato de la fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Leer el archivo CSV y cargar los datos en un mapa para facilitar la busqueda
            Map<Integer, List<String>> dataMap = readDataFromFile(tableName);
            // Obtener el robotId del evento
            int robotId = event.getRobotId();

            if (dataMap.containsKey(robotId)) {
                // El robotId ya existe, actualiza los datos correspondientes
                List<String> rowData = dataMap.get(robotId);
                rowData.set(1, event.getTimeStamp().format(formatter)); // Update timestamp
                rowData.set(2, Integer.toString(event.getAvenue())); // Update avenue
                rowData.set(3, Integer.toString(event.getStreet())); // Update street
                rowData.set(4, Integer.toString(event.getSirens())); // Update sirens

                // Escribir la linea actualizada en el archivo CSV
                writeDataToFile(tableName, dataMap);
            }

        } finally {
            eventLock.unlock();
        }

    }

    // Metodo para escribir los datos actualizados en el archivo CSV
    private void writeDataToFile(String fileName, Map<Integer, List<String>> dataMap) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        // Escribir los encabezados
        bufferedWriter.write("robotId,timestamp,avenue,street,sirens\n");
        // Escribir los datos actualizados
        for (List<String> rowData : dataMap.values()) {
            String line = String.join(",", rowData);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    // Metodo para leer los datos del archivo CSV y cargarlos en un mapa
    private Map<Integer, List<String>> readDataFromFile(String fileName) throws IOException {
        Map<Integer, List<String>> dataMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        boolean firstLine = true; // Para omitir la primera linea (encabezados)
        while ((line = br.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue; // Saltar la primera linea (encabezados)
            }
            String[] parts = line.split(",");
            int robotId;
            try {
                robotId = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                continue; // Saltar esta linea y continuar con la siguiente
            }
            List<String> rowData = Arrays.asList(parts);
            dataMap.put(robotId, rowData);
        }
        br.close();
        return dataMap;
    }

    // Metodo para agregar una linea al archivo CSV
    private void appendLineToFile(String fileName, String line) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    // Metodo para crear un nuevo archivo en caso de que no exista
    private void createNewCSVFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write("robotId,timestamp,avenue,street,sirens\n"); // Escribir la primera linea con los encabezados
        writer.close();
    }
    /* 
    //Convertir de csv a lista
    public Map<Integer, List<LogEvent>> getEvents(String filename) {
        Map<Integer, List<LogEvent>> EventIndex = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows.subList(1, rows.size())) { // Skip header row
                int logId = Integer.parseInt(row[0]);
                int robotId = Integer.parseInt(row[1]);
                String timestampString = row[2];
                LocalDateTime timestamp = LocalDateTime.parse(timestampString, formatter);
                int avenue = Integer.parseInt(row[3]);
                int street = Integer.parseInt(row[4]);
                int sirens = Integer.parseInt(row[5]);
                LogEvent logEvent = new LogEvent(logId, robotId, timestamp, avenue, street, sirens);

                // Update index
                if (!EventIndex.containsKey(logId)) {
                    EventIndex.put(logId, new ArrayList<>());
                }
                EventIndex.get(logId).add(logEvent);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return EventIndex;
    }
    */

}
