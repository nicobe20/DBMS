package server;

import java.io.*;
import java.net.*;
import org.json.JSONObject;
import requestHandlers.RequestHandlers;

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
                // System.out.println("DBServer: Received message from client: " + inputLine);

                // Convert received JSON string to JSON object
                JSONObject jsonObject = new JSONObject(inputLine);

                // Get queryType ​​of the JSONObject object
                String queryType = jsonObject.getString("queryType");

                RequestHandlers requestHandlers = new RequestHandlers();

                if (queryType.equals("GET")) {
                    requestHandlers.handleGet(jsonObject);
                } else if (queryType.equals("POST")) {
                    requestHandlers.handlePost(jsonObject);
                } else if (queryType.equals("PUT")) {
                    requestHandlers.handlePut(jsonObject);
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
