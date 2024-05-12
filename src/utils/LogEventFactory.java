package utils;

import java.time.LocalDateTime;
import org.json.JSONObject;
import tables.LogEvent;

public class LogEventFactory {
    public static LogEvent createRobotFromJson(JSONObject data) {

        int robotId = data.getInt("robotId");
        LocalDateTime timeStamp = LocalDateTime.now();
        int avenue = data.getInt("avenue");
        int street = data.getInt("street");
        int sirens = data.getInt("sirens");

        return new LogEvent(robotId, timeStamp, avenue, street, sirens);
    }
}
