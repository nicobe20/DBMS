package tables;

import java.time.LocalDateTime;

public class ProgramStatus {
    private LocalDateTime timeStamp;
    private String programStatus;

    // Constructor
    public ProgramStatus(LocalDateTime timeStamp, String programStatus) {
        this.timeStamp = timeStamp;
        this.programStatus = programStatus;
    }

    // Getters
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getState() {
        return programStatus;
    }

    // Setters
    // public void setTimeStamp(LocalDateTime timeStamp) {
    // this.timeStamp = timeStamp;
    // }

    // public void setState(int state) {
    // this.state = state;
    // }
}
