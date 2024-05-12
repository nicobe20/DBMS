package server;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

import org.json.JSONObject;

import constants.Constants;
import tables.LogEvent;
import tables.ProgramStatus;
import tables.Robot;
import database.CSVEventHandler;
import database.CSVLogEventHandler;
import database.CSVProgramStatusHandler;
import database.CSVRobotHandler;
import utils.GenerateId;

public class DbServer {
    private ServerSocket serverSocket;

    public DbServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("DBServer: Waiting for client connection...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //System.out.println("DBServer: Received message from client: " + inputLine);

                // Convert received JSON string to JSON object
                JSONObject jsonObject = new JSONObject(inputLine);

                // Get the values ​​of the JSONObject object
                String tableName = jsonObject.getString("tableName");

                if (tableName.equals(Constants.DEFAULT_ROBOT_TABLE_NAME)) {
                    handleRobotQueries(jsonObject, tableName);
                } else if (tableName.equals(Constants.DEFAULT_LOG_EVENT_TABLE_NAME)) {
                    handleLogEventsQueries(jsonObject, tableName);
                } else if (tableName.equals(Constants.DEFAULT_EVENT_TABLE_NAME)) {
                    handleEventsQueries(jsonObject, tableName);
                } else if (tableName.equals(Constants.DEFAULT_PROGRAM_STATUS_TABLE_NAME)) {
                    handleProgramStatus(jsonObject, tableName);
                }

            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRobotQueries(JSONObject jsonObject, String tableName) {
        // int robotId = jsonObject.getInt("robotId");
        int robotType = jsonObject.getInt("robotType");
        boolean isTurnedOn = jsonObject.getBoolean("isTurnedOn");
        String color = jsonObject.getString("color");
        String robotTypeString = jsonObject.getString("robotTypeString");

        // Get next id
        int nextRobotId = GenerateId.getLastId(Constants.DEFAULT_ROBOT_TABLE_NAME) + 1;

        // Create instance of Roboy and CSVRobotHandler
        Robot nuevoRobot = new Robot(nextRobotId, robotType, isTurnedOn, color, robotTypeString);
        CSVRobotHandler CSVRobotHandler = new CSVRobotHandler();

        try {
            CSVRobotHandler.saveRobot(nuevoRobot, tableName);
            // System.out.println("Robot successfully saved to CSV file.");

        } catch (IOException e) {
            System.out.println("Error saving robot: " + e.getMessage());
        }
    }

    public void handleLogEventsQueries(JSONObject jsonObject, String tableName) {
        int robotId = jsonObject.getInt("robotId");
        int avenue = jsonObject.getInt("avenue");
        int street = jsonObject.getInt("street");
        int sirens = jsonObject.getInt("sirens");

        // Create instance of LogEvent and CSVRobotHandler
        LogEvent newLogEvent = new LogEvent(robotId, LocalDateTime.now(), avenue, street, sirens);
        CSVLogEventHandler CSVLogEventHandler = new CSVLogEventHandler();

        try {
            CSVLogEventHandler.saveEvent(newLogEvent, tableName);
            // System.out.println("Event successfully saved to CSV file.");

        } catch (IOException e) {
            System.out.println("Error saving robot: " + e.getMessage());
        }
    }

    public void handleEventsQueries(JSONObject jsonObject, String tableName) {
        int robotId = jsonObject.getInt("robotId");
        int avenue = jsonObject.getInt("avenue");
        int street = jsonObject.getInt("street");
        int sirens = jsonObject.getInt("sirens");

        // Create instance of LogEvent and CSVRobotHandler
        LogEvent newLogEvent = new LogEvent(robotId, LocalDateTime.now(), avenue, street, sirens);
        CSVEventHandler CSVEventHandler = new CSVEventHandler();
        try {
            CSVEventHandler.saveAndUpdateEvent(newLogEvent, tableName);
            // System.out.println("Event successfully saved to CSV file.");
        } catch (IOException e) {
            System.out.println("Error saving robot: " + e.getMessage());
        }
    }

    public void handleProgramStatus(JSONObject jsonObject, String tableName) {
        String programStatus = jsonObject.getString("programStatus");

        // Create instance of ProgramStatus and CSVRobotHandler
        ProgramStatus newState = new ProgramStatus(LocalDateTime.now(), programStatus);
        CSVProgramStatusHandler CSVProgramStatusHandler = new CSVProgramStatusHandler();
        try {
            CSVProgramStatusHandler.saveProgramStatus(newState, tableName);
            // System.out.println("State successfully saved to CSV file.");
        } catch (IOException e) {
            System.out.println("Error saving robot: " + e.getMessage());
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
