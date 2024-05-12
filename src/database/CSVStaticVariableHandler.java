package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import tables.StaticVariables;

public class CSVStaticVariableHandler {
    private static final String FILENAME = "staticVariables.csv";

    public void saveStaticVariable(StaticVariables variable) throws IOException {
        // Configurar el formato de la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Abrir el archivo en modo append para no sobrescribir los datos existentes
        FileWriter fileWriter = new FileWriter(FILENAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Formatear los datos de las variables estaticas en una linea de texto CSV
        String line = variable.getTimeStamp().format(formatter) + "," +
                variable.getVariables(); // Asegurate de que las variables estan en un formato adecuado para CSV

        // Escribir la linea en el archivo CSV y cierra el archivo
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
