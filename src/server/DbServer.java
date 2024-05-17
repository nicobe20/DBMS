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
        int connectionCount = 0;
        System.out.println("DBServer: Waiting for client connection...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                connectionCount++;

                System.out.print("\rDBServer: Total connections: " + connectionCount);

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

            // Creamos un PrintWriter para enviar respuestas al cliente
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // System.out.println("DBServer: Received message from client: " + inputLine);

                // Convert received JSON string to JSON object
                JSONObject jsonObject = new JSONObject(inputLine);

                // Get queryType ​​of the JSONObject object
                String queryType = jsonObject.getString("queryType");

                RequestHandlers requestHandlers = new RequestHandlers();
                String response = ""; // Variable para almacenar la respuesta que se enviara al cliente

                if (queryType.equals("GET")) {
                    response = requestHandlers.handleGet(jsonObject);
                } else if (queryType.equals("POST")) {
                    response = requestHandlers.handlePost(jsonObject);
                } else if (queryType.equals("PUT")) {
                    response = requestHandlers.handlePut(jsonObject);
                }

                // Enviamos la respuesta al cliente
                out.println(response);
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
