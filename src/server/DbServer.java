package server;

import java.io.*;
import java.net.*;

import org.json.JSONObject;

import tables.Robot;
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

                // Iniciar un hilo para manejar la conexión con el cliente
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
                System.out.println("DBServer: Received message from client: " + inputLine);

                // Convert received JSON string to JSON object
                JSONObject jsonObject = new JSONObject(inputLine);

                // Get the values ​​of the JSONObject object
                String tableName = jsonObject.getString("tableName");
                int robotType = jsonObject.getInt("robotType");
                boolean isTurnedOn = jsonObject.getBoolean("isTurnedOn");

                //Get next id
                int nextRobotId = GenerateId.getLastId("robots.csv") + 1;

                // Create instance of Roboy and CSVRobotHandler
                Robot nuevoRobot = new Robot(nextRobotId, robotType, isTurnedOn);
                CSVRobotHandler CSVRobotHandler = new CSVRobotHandler();

                try {
                    CSVRobotHandler.saveRobot(nuevoRobot, tableName);
                    System.out.println("Robot guardado exitosamente en el archivo CSV.");

                } catch (IOException e) {
                    System.out.println("Error al guardar el robot: " + e.getMessage());
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
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
