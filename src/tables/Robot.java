package tables;

public class Robot {
    private int robotId;
    private int robotType;
    private boolean isTurnedOn;
    private String color;
    private String robotTypeString;
    

    // Constructor
    public Robot(int robotId, int robotType, boolean isTurnedOn, String color, String robotTypeString) {
        this.robotId = robotId;
        this.robotType = robotType;
        this.isTurnedOn = isTurnedOn;
        this.color = color;
        this.robotTypeString = robotTypeString;
    }

    // Getters
    public int getRobotId() {
        return robotId;
    }

    public int getRobotType() {
        return robotType;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    public String getColor() {
        return color;
    }
    
    public String getRobotTypeString() {
        return robotTypeString;
    }

    @Override
    public String toString() {
        return "Robot{" +
                ", robotId=" + robotId +
                ", RobotType='" + robotType + '\'' +
                ", isTurnedOn=" + isTurnedOn +
                ", Color=" + color +
                ", RobotType=" + robotTypeString +
                '}';
    }

    // Setters
    // public void setRobotType(int robotType) {
    // this.robotType = robotType;
    // }

    // public void setTurnedOn(boolean isTurnedOn) {
    // this.isTurnedOn = isTurnedOn;
    // }
}
