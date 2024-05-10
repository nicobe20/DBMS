package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import tables.LogEvent;

public class CSVLogEventHandler {

    public void saveEvent(LogEvent event, String tableName) throws IOException {
        // Configura el formato de la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        FileWriter fileWriter = new FileWriter(tableName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Formatea los datos del evento en una linea de texto CSV
        String line = event.getRobotId() + "," +
                event.getTimeStamp().format(formatter) + "," +
                event.getAvenue() + "," +
                event.getStreet() + "," +
                event.getSirens();

        // Escribe la linea en el archivo CSV y cierra el archivo
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
