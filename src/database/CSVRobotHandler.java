package database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import tables.Robot;

public class CSVRobotHandler {
    private static final String FILENAME = "robots.csv";

    public void saveRobot(Robot robot) throws IOException {
        FileWriter fileWriter = new FileWriter(FILENAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String line = robot.getRobotId() + "," + robot.getRobotType() + "," + robot.isTurnedOn();
        bufferedWriter.write(line);
        bufferedWriter.newLine();

        bufferedWriter.close();
    }

    public List<Robot> loadRobots() throws IOException {
        List<Robot> robots = new ArrayList<>();
        FileReader fileReader = new FileReader(FILENAME);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            int robotId = Integer.parseInt(data[0]);
            int robotType = Integer.parseInt(data[1]);
            boolean isTurnedOn = Boolean.parseBoolean(data[2]);
            Robot robot = new Robot(robotId, robotType, isTurnedOn);
            robots.add(robot);
        }
        bufferedReader.close();
        return robots;
    }
}
