package database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
                bufferedWriter.write("robotId,timeStamp,avenue,street,sirens");
                bufferedWriter.newLine();
            }

            // Formatear los datos del evento en una linea de texto CSV
            String line = event.getRobotId() + "," +
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
}
