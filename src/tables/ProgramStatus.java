package tables;

import java.time.LocalDateTime;

public class ProgramStatus {
    private LocalDateTime timeStamp;
    private int state;

    // Constructor
    public ProgramStatus(LocalDateTime timeStamp, int state) {
        this.timeStamp = timeStamp;
        this.state = state;
    }

    // Getters
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getState() {
        return state;
    }

    // Setters
    // public void setTimeStamp(LocalDateTime timeStamp) {
    // this.timeStamp = timeStamp;
    // }

    // public void setState(int state) {
    // this.state = state;
    // }
}
