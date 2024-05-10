import constants.Constants;
import server.DbServer;
import utils.CleanTable;

public class Main {

    public static void main(String[] args) {

        CleanTable cleanRobotTable = new CleanTable();
        cleanRobotTable.cleanTable(Constants.DEFAULT_ROBOT_TABLE_NAME);

        CleanTable cleanEventTable = new CleanTable();
        cleanEventTable.cleanTable(Constants.DEFAULT_EVENT_TABLE_NAME);

        DbServer dbServer = new DbServer(12345);
        dbServer.start();
    }
}
