package requestHandlers;

import java.io.IOException;

import org.json.JSONObject;

import constants.Constants;
import database.CSVEventHandler;
import database.CSVLogEventHandler;
import database.CSVRobotHandler;
import tables.LogEvent;
import tables.Robot;
import utils.LogEventFactory;
import utils.RobotFactory;

public class RequestHandlers {
    public void handleGet(JSONObject jsonString) {

    }

    public void handlePost(JSONObject jsonObject) {
        String tableName = jsonObject.getString("tableName");
        JSONObject data = jsonObject.getJSONObject("data");

        if (tableName.equals(Constants.DEFAULT_ROBOT_TABLE_NAME)) {
            Robot robot = RobotFactory.createRobotFromJson(data);
            CSVRobotHandler CSVRobotHandler = new CSVRobotHandler();

            try {
                CSVRobotHandler.saveRobot(robot, tableName);
                // System.out.println("Robot successfully saved to CSV file.");
            } catch (IOException e) {
                System.out.println("Error saving robot: " + e.getMessage());
            }
        } else if (tableName.equals(Constants.DEFAULT_LOG_EVENT_TABLE_NAME)) {
            LogEvent logEvent = LogEventFactory.createRobotFromJson(data);
            CSVLogEventHandler CSVLogEventHandler = new CSVLogEventHandler();

            try {
                CSVLogEventHandler.saveLogEvent(logEvent, tableName);
                // System.out.println("Robot successfully saved to CSV file.");
            } catch (IOException e) {
                System.out.println("Error saving robot: " + e.getMessage());
            }
        } else if (tableName.equals(Constants.DEFAULT_EVENT_TABLE_NAME)) {
            LogEvent event = LogEventFactory.createRobotFromJson(data);
            CSVEventHandler CSVEventHandler = new CSVEventHandler();

            try {
                CSVEventHandler.saveEvent(event, tableName);
                // System.out.println("Robot successfully saved to CSV file.");
            } catch (IOException e) {
                System.out.println("Error saving robot: " + e.getMessage());
            }
        }
    }

    public void handlePut(JSONObject jsonObject) {
        String tableName = jsonObject.getString("tableName");
        JSONObject data = jsonObject.getJSONObject("data");


        if (tableName.equals(Constants.DEFAULT_EVENT_TABLE_NAME)) {
            LogEvent event = LogEventFactory.createRobotFromJson(data);
            CSVEventHandler CSVEventHandler = new CSVEventHandler();

            try {
                CSVEventHandler.updateEvent(event, tableName);
                // System.out.println("Robot successfully saved to CSV file.");
            } catch (IOException e) {
                System.out.println("Error saving robot: " + e.getMessage());
            }
        }
    }
}
