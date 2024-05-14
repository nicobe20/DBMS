import constants.Constants;
import server.DbServer;
import utils.CleanTable;

public class Main {

    public static void main(String[] args) {

        CleanTable cleanTable = new CleanTable();

        cleanTable.cleanTable(Constants.DEFAULT_ROBOT_TABLE_NAME);
        cleanTable.cleanTable(Constants.DEFAULT_EVENT_TABLE_NAME);
        cleanTable.cleanTable(Constants.DEFAULT_LOG_EVENT_TABLE_NAME);
        cleanTable.cleanTable(Constants.DEFAULT_PROGRAM_STATUS_TABLE_NAME);

        DbServer dbServer = new DbServer(12345);
        dbServer.start();
    }
}

// Verificar que la tabla padre tenga los ids creados.
// Indexation y queries.
// Indexation always by id.

// USE TABLE events.csv;
