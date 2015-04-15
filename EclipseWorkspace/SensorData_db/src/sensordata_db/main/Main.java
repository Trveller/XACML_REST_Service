package sensordata_db.main;

import static java.lang.System.out;

public class Main {

	public static void main(String[] args) {
//		final String ipAddress = args.length > 0 ? args[0] : "localhost";
//		final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
//		out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
		SensorDataDBManager db = SensorDataDBManager.getInstance();
//		db.generateSensorData();
		db.printAllData();
		db.close();
		out.print("FINISHED");
	}
	
}
