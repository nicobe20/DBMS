package database;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import tables.Robot;

public class CSVRobotHandler {
    public void saveRobot(Robot robot, String tableName) throws IOException {
        FileWriter fileWriter = new FileWriter(tableName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Verificar si el archivo esta vacio
        File file = new File(tableName);
        if (file.length() == 0) {
            // Si el archivo esta vacio, escribir los nombres de los campos como encabezados
            bufferedWriter.write("robotId,robotType,isTurnedOn,color,robotTypeString");
            bufferedWriter.newLine();
        }

        // Escribir la informacion del robot en el archivo
        String line = robot.getRobotId() + "," +
                robot.getRobotType() + "," +
                robot.isTurnedOn() + "," +
                robot.getColor() + "," +
                robot.getRobotTypeString();

        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    public Map<Integer, List<Robot>> getRobotItems(String filename) {
        Map<Integer, List<Robot>> ListRobotItems = new HashMap<>();
        //public Robot(int robotId, int robotType, boolean isTurnedOn, String color, String robotTypeString) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows.subList(1, rows.size())) { // Skip header row
                int robotId = Integer.parseInt(row[0]);
                int robotType = Integer.parseInt(row[1]);
                boolean isTurnedOn = Boolean.parseBoolean(row[2]);
                String color = row[3];
                String robotString = row[4];
                Robot listRobot = new Robot(robotId,robotType,isTurnedOn,color,robotString);

                // Update index
                if (!ListRobotItems.containsKey(robotId)) {
                    ListRobotItems.put(robotId, new ArrayList<>());
                }
                ListRobotItems.get(robotId).add(listRobot);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return ListRobotItems;
    }


}
