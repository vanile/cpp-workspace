package edu.cpp.cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Lab4 {
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/Lab4?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "thealbino1";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url, user, password);
			Scanner scanner = new Scanner(System.in);
			String input = "";
			
			while(!input.equals("9")) {
				Statement stmt;
				ResultSet rs = null;
				ResultSetMetaData rsMeta = null;
				String sqlQuery = "";
				printOptions();
				
				System.out.print("Select command: ");
				input = scanner.next();
				scanner.nextLine();
						
				if(input.equals("1")) {
					stmt = con.createStatement();
					System.out.print("Enter Start Location Name: ");
					String startLocationName = scanner.nextLine();
					System.out.println();
					
					System.out.print("Enter Destination Name: ");
					String destinationName = scanner.nextLine();
					System.out.println();
					
					System.out.print("Enter Date (MM/DD/YYYY): ");
					String date = scanner.nextLine();
					System.out.println();
					
					sqlQuery = "SELECT t.TripNumber, t.StartLocationName, t.DestinationName, o.Date"
							+ ", o.ScheduledStartTime, o.ScheduledArrivalTime, o.DriverName, "
							+ "o.BusId "
							+ "FROM Trip t, TripOffering o "
							+ "WHERE t.TripNumber = o.TripNumber AND "
							+ "t.StartLocationName LIKE '" + startLocationName + "' AND "
							+ "t.DestinationName" + " LIKE '" + destinationName + "' "+ " AND "
							+ "o.Date LIKE '" + date + "'";
					rs = stmt.executeQuery(sqlQuery);
					
					rsMeta = rs.getMetaData();
					
					// Display Column names as string
					String varColNames = "";
					int varColCount = rsMeta.getColumnCount();
					for(int col = 1; col <= varColCount;col++){
						varColNames = varColNames + rsMeta.getColumnName(col)+ " ";
					}
					System.out.println(varColNames);
					
					//Display column values
					while(rs.next()){
						for(int col = 1; col <= varColCount;col++){
							System.out.print(rs.getString(col) + " ");
						}
						System.out.println();
					}
				} else if(input.equals("2")) {
					stmt = con.createStatement();
					System.out.println("Schedule Editing Options: ");
					System.out.println("1. Delete a trip offering specified by Trip#, Date, and ScheduledStartTime.");
					System.out.println("2. Add a set of trip offerings.");
					System.out.println("3. Change the driver for a given trip.");
					System.out.println("4. Change the bus for a given trip.");
					System.out.print("Option: ");
					int option = scanner.nextInt();
					
					switch(option) {
					case 1: deleteTripOffering(stmt, rs);
						break;
					case 2: addTripOfferings(stmt);
						break;
					case 3: changeDriver(stmt);
						break;
					case 4: changeBus(stmt);
						break;
					default: System.out.println("Invalid input. Try again.");
						break;
					}
				} else if(input.equals("3")) {
					stmt = con.createStatement();
					
					System.out.print("Enter Trip number: ");
					int tripNumber = scanner.nextInt();
					System.out.println();
					
					sqlQuery = "SELECT * "
							+ "FROM TripStopInfo "
							+ "WHERE TripNumber=" + tripNumber;
					
					rs = stmt.executeQuery(sqlQuery);
					rsMeta = rs.getMetaData();
	
					String varColNames = "";
					int varColCount = rsMeta.getColumnCount();
					for(int col = 1; col <= varColCount;col++){
						varColNames = varColNames + rsMeta.getColumnName(col)+ " ";
					}
					System.out.println(varColNames);
					
					
					while(rs.next()){
						for(int col = 1; col <= varColCount;col++){
							System.out.print(rs.getString(col) + " ");
						}
						System.out.println();
					}
				} else if(input.equals("4")) {
					stmt = con.createStatement();
					
					System.out.print("Enter in a driver's name: ");
					String driverName = scanner.next();
							
					System.out.print("Given month(two digits): ");
					String month = scanner.next();
					
					System.out.print("Given day(two digits): ");
					int day = scanner.nextInt();
							
					System.out.print("Given year (two digits): ");
					String year = scanner.next();
					
					
					int i = 1;
					while (i <= 7) {
						String tempDay;
						if(day < 9) {
							tempDay = "0" + day;
						} else {
							tempDay = "" + day;
						}
						sqlQuery = "SELECT * FROM TripOffering WHERE DATE LIKE '00"
								+ month + "-" + tempDay + "-" + year + "' AND DriverName LIKE '" + driverName + "'";
						rs = stmt.executeQuery(sqlQuery);
						rsMeta = rs.getMetaData();
						
						String varColNames = "";
						int varColCount = rsMeta.getColumnCount();
						for(int col = 1; col <= varColCount;col++){
							varColNames = varColNames + rsMeta.getColumnName(col)+ " ";
						}
						System.out.println(varColNames);
						
						
						while(rs.next()){
							for(int col = 1; col <= varColCount;col++){
								System.out.print(rs.getString(col) + " ");
							}
							System.out.println();
						}
						i++;
						day++;
					}
					rs = stmt.executeQuery(sqlQuery);
					
					while(rs.next()) {
						String name = rs.getString("name");

					}
				} else if(input.equals("5")) {
					stmt = con.createStatement();
					
					System.out.print("Enter driver name: ");
					String driverName = scanner.nextLine();
					System.out.println();
					
					System.out.print("Enter driver telephone number: ");
					String driverPhoneNum = scanner.nextLine();
					System.out.println();
					
					sqlQuery = "INSERT INTO driver "
							+ "VALUES ('" + driverName + "', '" + driverPhoneNum + "')";
					stmt.executeUpdate(sqlQuery);
				} else if(input.equals("6")) {
					stmt = con.createStatement();
					
					System.out.print("Enter bus ID: ");
					String id = scanner.nextLine();
					System.out.println();
					
					System.out.print("Enter bus model: ");
					String model = scanner.nextLine();
					System.out.println();
					
					System.out.print("Enter bus year: ");
					String year = scanner.nextLine();
					System.out.println();
					
					sqlQuery = "INSERT INTO bus "
							+ "VALUES ('" + id + "', '" + model + "', '" + year + "')";
					stmt.executeUpdate(sqlQuery);
				} else if(input.equals("7")) {
					stmt = con.createStatement();
					
					System.out.print("Enter bus ID: ");
					String id = scanner.nextLine();
					System.out.println();
					
					sqlQuery = "DELETE FROM bus "
							+ "WHERE BusID = " + id;
					stmt.executeUpdate(sqlQuery);
					
				} else if(input.equals("8")) {
					stmt = con.createStatement();
					sqlQuery = "";
					stmt.executeQuery(sqlQuery);
					
					while(rs.next()) {
						String name = rs.getString("name");

					}
				} else if(input.equals("9")) {
					stmt = con.createStatement();
					
					System.out.print("TripNumber: ");
					int tripNumber = scanner.nextInt();
					
					System.out.print("Date (YYYY-MM-DD): ");
					String date = scanner.next();
					
					System.out.print("ScheduledStartTime (HH:MM:SS): ");
					String scheduledStartTime = scanner.next();
					
					String scheduledArrival = "";
					String stopNumber = "";
					
					sqlQuery = "SELECT o.scheduledArrivalTime, t.StopNumber FROM TripOffering o, "
							+ " TripStopInfo t WHERE t.TripNumber = o.TripNumber AND t.TripNumber =" + 
							tripNumber + " AND DATE LIKE '" + date + "' AND ScheduledStartTime LIKE '"
							+ scheduledStartTime + "'";
					
					try{

						Class.forName("com.mysql.jdbc.Driver").newInstance();
						
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Lab4?autoReconnect=true&useSSL=false", "root", "thealbino1");
						Statement stmt2 = conn.createStatement();

						ResultSet rs2 = stmt.executeQuery(sqlQuery);
						
						//Display column values
						while(rs2.next()){
							scheduledArrival = rs.getString(1);
							stopNumber = rs.getString(2);
						}
					} catch(Exception e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("[Error] Invalid option");
				}
			}
			
			System.exit(0);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteTripOffering(Statement stmt, ResultSet rs) throws SQLException {
		String sqlQuery;
		int tripNumber;
		String date;
		String scheduledStartTime;
		Scanner in = new Scanner(System.in);
	
		System.out.print("TripNumber: ");	
		tripNumber = in.nextInt();
		
		System.out.print("Date(Please follow 'YYYY-MM-DD' format): ");	
		date = in.next();
		
		System.out.print("ScheduledStartTime(Please follow 'HH:MM' 24H format): ");	
		scheduledStartTime = in.next() + ":00";
	
		sqlQuery = "DELETE FROM TripOffering WHERE "
				+ "TripNumber='" + tripNumber + "'" 
				+ " AND Date='" + date + "'"
				+ " AND ScheduledStartTime='" + scheduledStartTime
				+"';";
		stmt.executeUpdate(sqlQuery);
		
		sqlQuery = "";
		rs = stmt.executeQuery(sqlQuery);
		System.out.println("Trip deleted!");
	}

	private static void addTripOfferings(Statement stmt) throws SQLException {
		String sqlQuery;
		int tripNumber;
		int busID;
		String date;
		String scheduledStartTime, scheduledArrivalTime;
		String driverName;
		Scanner in = new Scanner(System.in);
	
		System.out.print("TripNumber: ");	
		tripNumber = in.nextInt();
		
		System.out.print("Date(Please follow 'YYYY-MM-DD' format): ");	
		date = in.next();
		
		System.out.print("ScheduledStartTime(Please follow 'HH:MM' 24H format): ");	
		scheduledStartTime = in.next() + ":00";
		
		System.out.print("ScheduledArrivalTime(Please follow 'HH:MM' 24H format): ");	
		scheduledArrivalTime = in.next() + ":00";
		
		System.out.print("DriverName: ");	
		driverName = in.next();
		
		System.out.print("BusID: ");	
		busID = in.nextInt();
	
		sqlQuery = "INSERT INTO TripOffering VALUES("
				+ tripNumber + ",'" 
				+ date + "','" 
				+ scheduledStartTime + "','"
				+ scheduledArrivalTime + "','"
				+ driverName + "',"
				+ busID
				+");";
		stmt.executeUpdate(sqlQuery);
	}

	private static void changeDriver(Statement stmt) throws SQLException {
		String sqlQuery;
		int tripNumber;
		String date;
		String scheduledStartTime;
		String driverName;
		Scanner in = new Scanner(System.in);
	
		System.out.print("TripNumber: ");	
		tripNumber = in.nextInt();
		
		System.out.print("Date(Please follow 'YYYY-MM-DD' format): ");	
		date = in.next();
		
		System.out.print("ScheduledStartTime(Please follow 'HH:MM' 24H format): ");	
		scheduledStartTime = in.next() + ":00";
		
		System.out.print("New Bus Driver: ");
		driverName = in.next();
	
		sqlQuery = "UPDATE TripOffering "
				+ "SET DriverName='" + driverName + "'"
				+ "WHERE "
				+ "TripNumber='" + tripNumber + "'" 
				+ " AND Date='" + date + "'"
				+ " AND ScheduledStartTime='" + scheduledStartTime
				+"';";
		stmt.executeUpdate(sqlQuery);
		
		System.out.println("Driver Changed!");
		in.close();
	}

	private static void changeBus(Statement stmt) throws SQLException {
		String sqlQuery;
		int tripNumber;
		String date;
		String scheduledStartTime;
		int busID;
		Scanner in = new Scanner(System.in);
	
		System.out.print("TripNumber: ");	
		tripNumber = in.nextInt();
		
		System.out.print("Date(Please follow 'YYYY-MM-DD' format): ");	
		date = in.next();
		
		System.out.print("ScheduledStartTime(Please follow 'HH:MM' 24H format): ");	
		scheduledStartTime = in.next() + ":00";
		
		System.out.print("New Bus: ");
		busID = in.nextInt();
	
		sqlQuery = "UPDATE TripOffering "
				+ "SET BusID='" + busID + "'"
				+ "WHERE "
				+ "TripNumber='" + tripNumber + "'" 
				+ " AND Date='" + date + "'"
				+ " AND ScheduledStartTime='" + scheduledStartTime
				+"';";
		stmt.executeUpdate(sqlQuery);
	}
	
	public static void printOptions() {
		System.out.println("1. Display the schedule of all trips for a given StartLocationName \n"
				+ "and Destination Name, and Date. In addition to these attributes, the schedule "
				+ "includes: Scheduled StartTime,  ScheduledArrivalTime , DriverID, and BusID.");
		System.out.println("2. Edit the schedule i.e. edit the table of Trip Offering as follows: \n"
				+ "  - Delete a trip offering specified by Trip#, Date, and ScheduledStartTime \n"
				+ "  - Add a set of trip offerings assuming the values of all attributes are given \n"
				+ "     (the software asks if you have more trips to enter) \n"
				+ "  - Change the driver for a given Trip offering (i.e given TripNumber, Date, "
				+ "  ScheduledStartTime) \n"
				+ "  - Change the bus for a given Trip offering.");
		System.out.println("3. Display the stops of a given trip ( i.e. the attributes of the table TripStopInfo).");
		System.out.println("4. Display the weekly schedule of a given driver and date.");
		System.out.println("5. Add a drive.");
		System.out.println("6. Add a bus.");
		System.out.println("7. Delete a bus.");
		System.out.println("8. Record (insert) the actual data of a given trip offering specified by its key. "
				+ "The actual data include the attributes of the table ActualTripStopInfo.");
		System.out.println("9. Exit");
	}

}
