package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import tables.StaticVariables;

public class CSVStaticVariableHandler {
    private static final String FILENAME = "DataTables/staticVariables.csv";

    public void saveStaticVariable(StaticVariables variable) throws IOException {
        // Configura el formato de la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        File file = new File(FILENAME);
        file.getParentFile().mkdirs();

        //FileWriter set true para no sobreescribir los archivos.
        FileWriter fileWriter = new FileWriter(FILENAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Formatea los datos de las variables estáticas en una línea de texto CSV
        String line = variable.getTimeStamp().format(formatter) + "," +
                variable.getVariables(); // Asegúrate de que las variables están en un formato adecuado para CSV

        // Escribe la línea en el archivo CSV y cierra el archivo
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
