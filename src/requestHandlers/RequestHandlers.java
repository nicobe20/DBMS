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
import database.CSVProgramStatusHandler;
import database.CSVRobotHandler;
import tables.LogEvent;
import tables.ProgramStatus;
import tables.Robot;
import utils.LogEventFactory;
import utils.ProgramStatusFactory;
import utils.RobotFactory;

public class RequestHandlers {

    public String handleGet(JSONObject jsonString) {
        // new handlers
        CSVLogEventHandler CSVLogEventHandler = new CSVLogEventHandler();
        CSVRobotHandler CSVRobotHandler = new CSVRobotHandler();
        // Filters
        String tableName = jsonString.getString("tableName");
        int filterValue = jsonString.optInt("filterValue", -1);
        // Maps
        Map<Integer, List<LogEvent>> logEventIndex = CSVLogEventHandler
                .getLogEvents(Constants.DEFAULT_LOG_EVENT_TABLE_NAME);
        Map<Integer, List<Robot>> ListRobotItems = CSVRobotHandler.getRobotItems(Constants.DEFAULT_ROBOT_TABLE_NAME);

        if (tableName.equals(Constants.DEFAULT_LOG_EVENT_TABLE_NAME)) {
            if (filterValue != -1) {
                // Retrieve data for a specific LogId
                List<LogEvent> logEvents = logEventIndex.getOrDefault(filterValue, new ArrayList<>());
                for (LogEvent event : logEvents) {
                    return event.toString();
                }
            } else {
                // aqui esta todo el manejo del json
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
                return jsonString2;

            }
        } else if (tableName.equals(Constants.DEFAULT_ROBOT_TABLE_NAME)) {
            if (filterValue != -1) {
                // Retrieve data for a specific LogId
                List<Robot> robot = ListRobotItems.getOrDefault(filterValue, new ArrayList<>());
                for (Robot rot : robot) {
                    return rot.toString();
                }
            } else {
                JSONArray jsonArray = new JSONArray();
                for (Map.Entry<Integer, List<Robot>> entry : ListRobotItems.entrySet()) {
                    for (Robot rot : entry.getValue()) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("robotId", rot.getRobotId());
                        jsonObject.put("robotType", rot.getRobotType());
                        jsonObject.put("isTurnedOn", rot.isTurnedOn());
                        jsonObject.put("color", rot.getColor());
                        jsonObject.put("robotTypeString", rot.getRobotTypeString());
                        jsonArray.put(jsonObject);
                    }
                }

                String jsonString2 = jsonArray.toString();
                return jsonString2;
            }
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
        } else if (tableName.equals(Constants.DEFAULT_PROGRAM_STATUS_TABLE_NAME)) {
            ProgramStatus programStatus = ProgramStatusFactory.createProgramStatusFromJson(data);
            CSVProgramStatusHandler CSVProgramStatusHandler = new CSVProgramStatusHandler();

            try {
                CSVProgramStatusHandler.saveProgramStatus(programStatus, tableName);
                return "Program Status successfully saved to CSV file.";
            } catch (IOException e) {
                return "Error saving Program Status: " + e.getMessage();
            }
        }

        // Si el nombre de la tabla no coincide con ninguno de los nombres
        // predeterminados, devuelve un mensaje de error.
        return "Error: Table name not recognized.";
    }

    public String handlePut(JSONObject jsonObject) {
        String tableName = jsonObject.getString("tableName");
        JSONObject data = jsonObject.getJSONObject("data");

        if (tableName.equals(Constants.DEFAULT_EVENT_TABLE_NAME)) {
            // Logica para actualizar un evento

            LogEvent event = LogEventFactory.createRobotFromJson(data);
            CSVEventHandler CSVEventHandler = new CSVEventHandler();

            try {
                CSVEventHandler.updateEvent(event, tableName);
                return "Event successfully updated to CSV file.";
            } catch (IOException e) {
                return "Error updating event";
            }
        } else if (tableName.equals(Constants.DEFAULT_ROBOT_TABLE_NAME)) {
            // Agregar logica para actualizar archivo robots.csv

            // Para esto se debe utilizar una logica similar a la anterior donde dice
            // "Logica para actualizar un evento". la idea es crear una funcion en
            // "CSVRobotHandler.java" llamada updateRobot la cual maneje la logica para
            // actualizar un robot por "robotId"
        }

        return "Error: Table name not recognized or this table cannot be updated";

    }
}
