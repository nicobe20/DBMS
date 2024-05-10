package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import tables.ProgramStatus;

public class CSVProgramStatusHandler {
    private static final String FILENAME = "programStatus.csv";

    public void saveProgramStatus(ProgramStatus programStatus) throws IOException {
        // Configura el formato de la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Abre el archivo en modo append para no sobrescribir los datos existentes
        FileWriter fileWriter = new FileWriter(FILENAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Formatea los datos del estado del programa en una línea de texto CSV
        String line = programStatus.getTimeStamp().format(formatter) + "," +
                programStatus.getState();

        // Escribe la línea en el archivo CSV y cierra el archivo
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
