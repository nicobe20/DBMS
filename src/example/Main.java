package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Utility {
	public static void createRobot(int tipoRobotInt, String robotColorString, String robotTypeString) {
		String jsonRobot = "{"
				+ "\"robotType\":" + tipoRobotInt + ","
				+ "\"isTurnedOn\":true" + ","
				+ "\"color\":" + robotColorString + ","
				+ "\"robotTypeString\":" + robotTypeString + ","
				+ "}";

		Main.connectionWithDbServer(jsonRobot, "POST", "robots.csv", null, null);
	}

	public static void createLogEvent(int robotId, int avenue, int street, int sirens) {
		String jsonEvent = "{"
				+ "\"robotId\":" + robotId + ","
				+ "\"avenue\":" + avenue + ","
				+ "\"street\":" + street + ","
				+ "\"sirens\":" + sirens + ","
				+ "}";

		Main.connectionWithDbServer(jsonEvent, "POST", "logEvents.csv", null, null);
	}

	public static void createEvent(int robotId, int avenue, int street, int sirens) {
		String jsonLogEvent = "{"
				+ "\"robotId\":" + robotId + ","
				+ "\"avenue\":" + avenue + ","
				+ "\"street\":" + street + ","
				+ "\"sirens\":" + sirens
				+ "}";

		Main.connectionWithDbServer(jsonLogEvent, "POST", "events.csv", null, null);
	}

	public static void updateEvent(int robotId, int avenue, int street, int sirens) {
		String jsonLogEvent = "{"
				+ "\"robotId\":" + robotId + ","
				+ "\"avenue\":" + avenue + ","
				+ "\"street\":" + street + ","
				+ "\"sirens\":" + sirens
				+ "}";

		Main.connectionWithDbServer(jsonLogEvent, "PUT", "events.csv", "robotId", robotId);
	}

	public static void getRobotById() {

		Main.connectionWithDbServer(null, "GET", "robots.csv", "robotId", 0);
	}
}

public class Main {
	public static void main(String[] args) {

		// Option 1: Create a robot
		Utility.createRobot(1, "BLACK", "MINERO");

		// Option 2: Create logEvent
		Utility.createLogEvent(0, 1, 1, 0);

		// Option 4: Create event
		Utility.createEvent(0, 10, 10, 0);

		// Option 3: Update logEvent
		Utility.updateEvent(0, 11, 10, 20);

		// Option 4: Get robots
		Utility.getRobotById();

	}

	public static void connectionWithDbServer(String dataJsonString, String queryType, String tableName,
			String filterField, Integer filterValue) {
		try {
			Socket socket = new Socket("localhost", 12345);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			String jsonMessage = "{"
					+ "\"queryType\":\"" + queryType + "\","
					+ "\"tableName\":\"" + tableName + "\","
					+ "\"filterField\":\"" + filterField + "\","
					+ "\"filterValue\":" + filterValue + ","
					+ "\"data\": " + dataJsonString
					+ "}";

			// Enviar mensaje al servidor
			out.println(jsonMessage);

			// Leer respuesta del servidor
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String response = in.readLine();
			System.out.println("Response from server: " + response);

			// Cerrar la conexión
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
