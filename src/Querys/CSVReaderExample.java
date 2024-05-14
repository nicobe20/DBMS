package Querys;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import constants.Constants;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReaderExample {
    private static Map<Integer, List<LogEvent>> logEventIndex = new HashMap<>();
    
    public static void main(String[] args) {
        String logEvent = Constants.DEFAULT_LOG_EVENT_TABLE_NAME;
        readLogEvents(logEvent);
    }

    //metodo usado para leer todo el archivo csv
    private static void readLogEvents(String filename) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows.subList(1, rows.size())) { // Skip header row
                int logId = Integer.parseInt(row[0]);
                int robotId = Integer.parseInt(row[1]);
                String timestamp = row[2];
                int avenue = Integer.parseInt(row[3]);
                int street = Integer.parseInt(row[4]);
                int sirens = Integer.parseInt(row[5]);
                LogEvent logEvent = new LogEvent(logId, robotId, timestamp, avenue, street, sirens);
                
                // Update index
                if (!logEventIndex.containsKey(logId)) {
                    logEventIndex.put(logId, new ArrayList<>());
                }
                logEventIndex.get(logId).add(logEvent);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
    //
    static class LogEvent {
        private final int logId;
        private final int robotId;
        private final String timestamp;
        private final int avenue;
        private final int street;
        private final int sirens;

        public LogEvent(int logId, int robotId, String timestamp, int avenue, int street, int sirens) {
            this.logId = logId;
            this.robotId = robotId;
            this.timestamp = timestamp;
            this.avenue = avenue;
            this.street = street;
            this.sirens = sirens;
        }

        @Override
        public String toString() {
            return "LogEvent{" +
                    "logId=" + logId +
                    ", robotId=" + robotId +
                    ", timestamp='" + timestamp + '\'' +
                    ", avenue=" + avenue +
                    ", street=" + street +
                    ", sirens=" + sirens +
                    '}';

        
        }
    }
}
