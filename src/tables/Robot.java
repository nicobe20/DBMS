package tables;

public class Robot {
    private int robotId; // Numeric (Number greater than 1)
    private int robotType; // Numeric (1, 2, or 3)
    private boolean isTurnedOn; // Boolean (True/False)

    // Constructor
    public Robot(int robotId, int robotType, boolean isTurnedOn) {
        this.robotId = robotId;
        this.robotType = robotType;
        this.isTurnedOn = isTurnedOn;
    }

    //Getters
    public int getRobotId() {
        return robotId;
    }

    public int getRobotType() {
        return robotType;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    //Setters
    // public void setRobotType(int robotType) {
    //     this.robotType = robotType;
    // }


    // public void setTurnedOn(boolean isTurnedOn) {
    //     this.isTurnedOn = isTurnedOn;
    // }
}
