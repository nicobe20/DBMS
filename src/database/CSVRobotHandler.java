package database;

import java.io.*;
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

}
