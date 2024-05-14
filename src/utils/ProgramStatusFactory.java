package utils;

import java.time.LocalDateTime;

import org.json.JSONObject;

import tables.ProgramStatus;

public class ProgramStatusFactory {
    public static ProgramStatus createProgramStatusFromJson(JSONObject data) {
        LocalDateTime timeStamp = LocalDateTime.now();
        String programStatus = data.getString("programStatus");

        return new ProgramStatus(timeStamp, programStatus);
    }
}
