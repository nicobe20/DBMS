package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateId {
    public static int getLastId(String filename) {
        int lastId = -1; // Valor predeterminado si no se encuentra ningun ID

        Path filePath = Paths.get(filename);
        if (Files.exists(filePath)) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
                String line;
                boolean firstLine = true; // Para omitir la primera linea
                while ((line = bufferedReader.readLine()) != null) {
                    if (firstLine) {
                        firstLine = false;
                        continue; // Saltar la primera linea y continuar con la siguiente
                    }
                    String[] data = line.split(",");
                    lastId = Integer.parseInt(data[0]); // Suponiendo que el ID esta en la primera posicion
                }
            } catch (IOException e) {
                // Manejar errores de lectura de archivo
                e.printStackTrace();
            } catch (NumberFormatException e) {
                // Manejar errores de conversion a entero
                e.printStackTrace();
            }
        }
        return lastId;
    }

}
