import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.CSVLogEventHandler;
import database.CSVProgramStatusHandler;
import database.CSVRobotHandler;
import database.CSVStaticVariableHandler;
import tables.LogEvent;
import tables.ProgramStatus;
import tables.Robot;
import tables.StaticVariables;

public class Main {
    public static void main(String[] args) {

        // // Guardar robot
        // // Crear una nueva instancia de Robot
        // Robot newRobot = new Robot(4, 1, false);

        // // Crear una lista para almacenar robots

        // // Crear una instancia de CSVHandler para manejar la escritura en archivo
        // CSVRobotHandler CSVRobotHandler = new CSVRobotHandler();

        // // Intentar guardar la lista de robots en el archivo CSV
        // try {
        // CSVRobotHandler.saveRobot(newRobot);
        // System.out.println("Robot guardado exitosamente en el archivo CSV.");
        // } catch (IOException e) {
        // System.err.println("Error al guardar robots en el archivo CSV: " +
        // e.getMessage());
        // }

        // --------------------------------------------------------------------------------

        // // Obtener robots
        // // Crear una instancia de CSVHandler para manejar la carga desde archivo
        // CSVRobotHandler CSVRobotHandler = new CSVRobotHandler();

        // // Nombre del archivo donde están guardados los robots

        // // Intentar cargar la lista de robots desde el archivo CSV
        // try {
        // List<Robot> robots = CSVRobotHandler.loadRobots();
        // System.out.println("Robots cargados exitosamente desde el archivo CSV:");
        // for (Robot robot : robots) {
        // System.out.println("ID: " + robot.getRobotId() + ", Tipo: " +
        // robot.getRobotType() + ", Encendido: " + robot.isTurnedOn());
        // }
        // } catch (IOException e) {
        // System.err.println("Error al cargar robots desde el archivo CSV: " +
        // e.getMessage());
        // }

        // --------------------------------------------------------------------------------

        // // Guardar evento
        // // Crear un evento de log
        // LogEvent event = new LogEvent(1, LocalDateTime.now(), 6, 10, 2);

        // // Instancia de CSVLogEventHandler
        // CSVLogEventHandler logHandler = new CSVLogEventHandler();

        // // Guardar el evento en el archivo CSV
        // try {
        // logHandler.saveEvent(event);
        // System.out.println("Evento guardado correctamente en el archivo CSV.");
        // } catch (IOException e) {
        // System.err.println("Error al guardar el evento: " + e.getMessage());
        // }

        // --------------------------------------------------------------------------------

        // // Crear un estado del programa
        // ProgramStatus status = new ProgramStatus(LocalDateTime.now(), 1);

        // // Instancia de CSVProgramStatusHandler
        // CSVProgramStatusHandler statusHandler = new CSVProgramStatusHandler();

        // // Intentar guardar el estado del programa en el archivo CSV
        // try {
        // statusHandler.saveProgramStatus(status);
        // System.out.println("Estado del programa guardado correctamente en el archivo
        // CSV.");
        // } catch (IOException e) {
        // System.err.println("Error al guardar el estado del programa: " +
        // e.getMessage());
        // }

        // --------------------------------------------------------------------------------
        // //

        // // Crear un conjunto de variables estáticas
        // StaticVariables staticVars = new StaticVariables(LocalDateTime.now(),
        // "var1=100,var2=200");

        // // Instancia de CSVStaticVariableHandler
        // CSVStaticVariableHandler variableHandler = new CSVStaticVariableHandler();

        // // Intentar guardar las variables estáticas en el archivo CSV
        // try {
        // variableHandler.saveStaticVariable(staticVars);
        // System.out.println("Variables estáticas guardadas correctamente en el archivo
        // CSV.");
        // } catch (IOException e) {
        // System.err.println("Error al guardar las variables estáticas: " +
        // e.getMessage());
        // }
    }
}
