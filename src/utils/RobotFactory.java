package utils;

import org.json.JSONObject;

import constants.Constants;
import tables.Robot;

public class RobotFactory {
    public static Robot createRobotFromJson(JSONObject data) {
        int robotId = GenerateId.getLastId(Constants.DEFAULT_ROBOT_TABLE_NAME) + 1;
        int robotType = data.getInt("robotType");
        boolean isTurnedOn = data.getBoolean("isTurnedOn");
        String color = data.getString("color");
        String robotTypeString = data.getString("robotTypeString");

        return new Robot(robotId, robotType, isTurnedOn, color, robotTypeString);
    }
}
