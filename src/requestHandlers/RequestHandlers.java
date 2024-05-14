package requestHandlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
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

    public String handleGet(JSONObject jsonString) {
        CSVLogEventHandler CSVLogEventHandler = new CSVLogEventHandler();

        String tableName = jsonString.getString("tableName");
        int filterValue = jsonString.optInt("filterValue", -1);

        Map<Integer, List<LogEvent>> logEventIndex = CSVLogEventHandler
                .getLogEvents(Constants.DEFAULT_LOG_EVENT_TABLE_NAME);

        if (tableName.equals(Constants.DEFAULT_LOG_EVENT_TABLE_NAME)) {
            System.out.println("Entra aca : " + tableName);
            System.out.println("Entra aca 2 : " + filterValue);
            if (filterValue != -1) {
                // Retrieve data for a specific LogId
                List<LogEvent> logEvents = logEventIndex.getOrDefault(filterValue, new ArrayList<>());
                for (LogEvent event : logEvents) {
                    return event.toString();
                }
            } else {
                JSONArray jsonArray = new JSONArray();

                // Retrieve data for all LogIds
                for (Map.Entry<Integer, List<LogEvent>> entry : logEventIndex.entrySet()) {
                    // Iterar sobre la lista de LogEvent
                    for (LogEvent logEvent : entry.getValue()) {
                        // Crear un JSONObject para cada LogEvent
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("logId", logEvent.getLogId());
                        jsonObject.put("robotId", logEvent.getRobotId());
                        jsonObject.put("timestamp", logEvent.getTimeStamp().toString());
                        jsonObject.put("avenue", logEvent.getAvenue());
                        jsonObject.put("street", logEvent.getStreet());
                        jsonObject.put("sirens", logEvent.getSirens());

                        // Agregar el JSONObject al JSONArray
                        jsonArray.put(jsonObject);
                    }
                }

                String jsonString2 = jsonArray.toString();
                System.out.println(jsonString2);
                return jsonString2;

            }
        } else {
            return "Invalid table name";
        }

        return "Error: Se ha producido un error inesperado";
    }

    public String handlePost(JSONObject jsonObject) {
        String tableName = jsonObject.getString("tableName");
        JSONObject data = jsonObject.getJSONObject("data");

        if (tableName.equals(Constants.DEFAULT_ROBOT_TABLE_NAME)) {
            Robot robot = RobotFactory.createRobotFromJson(data);
            CSVRobotHandler CSVRobotHandler = new CSVRobotHandler();

            try {
                CSVRobotHandler.saveRobot(robot, tableName);
                return "Robot successfully saved to CSV file.";
            } catch (IOException e) {
                return "Error saving robot: " + e.getMessage();
            }
        } else if (tableName.equals(Constants.DEFAULT_LOG_EVENT_TABLE_NAME)) {
            LogEvent logEvent = LogEventFactory.createRobotFromJson(data);
            CSVLogEventHandler CSVLogEventHandler = new CSVLogEventHandler();

            try {
                CSVLogEventHandler.saveLogEvent(logEvent, tableName);
                return "Log event successfully saved to CSV file.";
            } catch (IOException e) {
                return "Error saving log event: " + e.getMessage();
            }
        } else if (tableName.equals(Constants.DEFAULT_EVENT_TABLE_NAME)) {
            LogEvent event = LogEventFactory.createRobotFromJson(data);
            CSVEventHandler CSVEventHandler = new CSVEventHandler();

            try {
                CSVEventHandler.saveEvent(event, tableName);
                return "Event successfully saved to CSV file.";
            } catch (IOException e) {
                return "Error saving event: " + e.getMessage();
            }
        }

        // Si el nombre de la tabla no coincide con ninguno de los nombres
        // predeterminados, devuelve un mensaje de error.
        return "Error: Table name not recognized.";
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
