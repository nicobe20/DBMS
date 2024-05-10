package tables;

import java.time.LocalDateTime;

public class LogEvent {
    private int robotId;
    private LocalDateTime timeStamp;
    private int avenue;
    private int street;
    private int sirens;

    // Constructor
    public LogEvent(int robotId, LocalDateTime timeStamp, int avenue, int street, int sirens) {
        this.robotId = robotId;
        this.timeStamp = timeStamp;
        this.avenue = avenue;
        this.street = street;
        this.sirens = sirens;
    }

    // Getters
    public int getRobotId() {
        return robotId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getAvenue() {
        return avenue;
    }

    public int getStreet() {
        return street;
    }

    public int getSirens() {
        return sirens;
    }

    // Setters
    
    // public void setRobotId(int robotId) {
    //     this.robotId = robotId;
    // }

    // public void setTimeStamp(LocalDateTime timeStamp) {
    //     this.timeStamp = timeStamp;
    // }

    // public void setAvenue(int avenue) {
    //     this.avenue = avenue;
    // }

    // public void setStreet(int street) {
    //     this.street = street;
    // }

    // public void setSirens(int sirens) {
    //     this.sirens = sirens;
    // }
}

