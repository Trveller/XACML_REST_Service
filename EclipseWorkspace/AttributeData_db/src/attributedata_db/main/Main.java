package attributedata_db.main;

import static java.lang.System.out;
import smartie_dissertation.helperlib.database.DBManagerFactory;

public class Main {

	public static void main(String[] args) {
//		final String ipAddress = args.length > 0 ? args[0] : "localhost";
//		final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
//		out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
		AttributeDataDBManager db = DBManagerFactory.getDataManager(AttributeDataDBManager.class);
//		db.generateData();
		db.fetchAllEnvironmentAttributes();
		db.printAllData();
		db.close();
		out.print("FINISHED");
	}
	
}
