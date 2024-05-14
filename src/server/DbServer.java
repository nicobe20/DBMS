package server;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import requestHandlers.RequestHandlers;
import tables.LogEvent;

public class DbServer {
    private ServerSocket serverSocket;
    private Map<Integer, List<LogEvent>> logEventIndex;

    public DbServer(int port, Map<Integer, List<LogEvent>> logEventIndex) {
        try {
            serverSocket = new ServerSocket(port);
            this.logEventIndex = logEventIndex;
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
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JSONObject jsonObject = new JSONObject(inputLine);
                handleJsonObject(jsonObject, out);
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleJsonObject(JSONObject jsonObject, PrintWriter out) {
        String queryType = jsonObject.getString("queryType");
        RequestHandlers requestHandlers = new RequestHandlers();

        if (queryType.equals("GET")) {
            requestHandlers.handleGet(jsonObject, logEventIndex, out);
        } else if (queryType.equals("POST")) {
            requestHandlers.handlePost(jsonObject);
        } else if (queryType.equals("PUT")) {
            requestHandlers.handlePut(jsonObject);
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
