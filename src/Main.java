import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import constants.Constants;
import server.DbServer;
import tables.LogEvent;
import utils.CleanTable;

public class Main {

    public static void main(String[] args) {

        CleanTable cleanTable = new CleanTable();

        cleanTable.cleanTable(Constants.DEFAULT_ROBOT_TABLE_NAME);
        cleanTable.cleanTable(Constants.DEFAULT_EVENT_TABLE_NAME);
        cleanTable.cleanTable(Constants.DEFAULT_LOG_EVENT_TABLE_NAME);
        Map<Integer, List<LogEvent>> logEventIndex = new HashMap<>();
        DbServer dbServer = new DbServer(12345, logEventIndex);
        dbServer.start();
        
        // Create a JSON object for the request
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("queryType", "GET");
            jsonObject.put("tableName", "example_table");
            jsonObject.put("logId", 123);

            // Send the JSON object as a string to the server
            PrintWriter out = new PrintWriter(dbServer.socket.getOutputStream(), true);
            out.println(jsonObject.toString());
        
    }
}


//Verificar que la tabla padre tenga los ids creados.
//Indexation y queries.
//Indexation always by id. 

//USE TABLE events.csv;
