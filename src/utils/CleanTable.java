package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CleanTable {
    public void cleanTable(String fileName) {
        File file = new File(fileName);

        if (file.exists() && !file.isDirectory()) {
            try {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.close(); // Sobreescribir el archivo con uno nuevo vacio
                System.out.println("Archivo '" + fileName + "' vaciado correctamente.");
            } catch (IOException e) {
                System.err.println("Error al vaciar el archivo '" + fileName + "': " + e.getMessage());
            }
        } else {
            System.err.println("El archivo '" + fileName + "' no existe o es un directorio.");
        }
    }
}
