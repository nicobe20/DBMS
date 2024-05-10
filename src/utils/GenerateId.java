package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateId {
    public static int getLastId(String filename) {
        int lastId = -1; // Default value if no ID found

        Path filePath = Paths.get(filename);
        if (Files.exists(filePath)) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] data = line.split(",");
                    lastId = Integer.parseInt(data[0]); // Assuming the ID is in the first position
                }
            } catch (IOException e) {
                // Handle any file read errors
                e.printStackTrace();
            } catch (NumberFormatException e) {
                // Handle conversion errors to integer
                e.printStackTrace();
            }
        }

        return lastId;
    }
}
