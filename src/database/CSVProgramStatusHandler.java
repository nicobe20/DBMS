package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import tables.ProgramStatus;

public class CSVProgramStatusHandler {

    public void saveProgramStatus(ProgramStatus programStatus, String tableName) throws IOException {
        // Configurar el formato de la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Abrir el archivo en modo append para no sobrescribir los datos existentes
        FileWriter fileWriter = new FileWriter(tableName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Formatear los datos del estado del programa en una linea de texto CSV
        String line = programStatus.getTimeStamp().format(formatter) + "," +
                programStatus.getState();

        // Escribir la linea en el archivo CSV y cierra el archivo
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
