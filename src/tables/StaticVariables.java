package tables;
import java.time.LocalDateTime;

public class StaticVariables {
    private LocalDateTime timeStamp;
    private String variables;

    // Constructor
    public StaticVariables(LocalDateTime timeStamp, String variables) {
        this.timeStamp = timeStamp;
        this.variables = variables;
    }

    // Getters


    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getVariables() {
        return variables;
    }

    // Setters
    // public void setTimeStamp(LocalDateTime timeStamp) {
    //     this.timeStamp = timeStamp;
    // }

    // public void setVariables(String variables) {
    //     this.variables = variables;
    // }
}

