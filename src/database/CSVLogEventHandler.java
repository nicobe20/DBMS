package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import tables.LogEvent;

public class CSVLogEventHandler {
    private static final String FILENAME = "logEvents.csv";

    public void saveEvent(LogEvent event) throws IOException {
        // Configura el formato de la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        FileWriter fileWriter = new FileWriter(FILENAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Formatea los datos del evento en una línea de texto CSV
        String line = event.getRobotId() + "," +
                event.getTimeStamp().format(formatter) + "," +
                event.getAvenue() + "," +
                event.getStreet() + "," +
                event.getSirens();

        // Escribe la línea en el archivo CSV y cierra el archivo
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
