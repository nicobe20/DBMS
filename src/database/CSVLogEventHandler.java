package database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import tables.LogEvent;

public class CSVLogEventHandler {

    private final Lock logEventLock = new ReentrantLock();

    public void saveLogEvent(LogEvent event, String tableName) throws IOException {
        logEventLock.lock();
        try {
            // Configurar el formato de la fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            FileWriter fileWriter = new FileWriter(tableName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Verificar si el archivo esta vacio
            File file = new File(tableName);
            if (file.length() == 0) {
                // Si el archivo esta vacio, escribir los nombres de los campos como encabezados
                bufferedWriter.write("LogId,robotId,timeStamp,avenue,street,sirens");
                bufferedWriter.newLine();
            }

            // Formatear los datos del evento en una linea de texto CSV
            String line = event.getLogId() + "," +
                    event.getRobotId() + "," +
                    event.getTimeStamp().format(formatter) + "," +
                    event.getAvenue() + "," +
                    event.getStreet() + "," +
                    event.getSirens();

            // Escribir la linea en el archivo CSV y cierra el archivo
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } finally {
            logEventLock.unlock();
        }
    }

    //Convertir de csv a lista
    public Map<Integer, List<LogEvent>> getLogEvents(String filename) {
        Map<Integer, List<LogEvent>> logEventIndex = new HashMap<>();

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
                if (!logEventIndex.containsKey(logId)) {
                    logEventIndex.put(logId, new ArrayList<>());
                }
                logEventIndex.get(logId).add(logEvent);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return logEventIndex;
    }

}
