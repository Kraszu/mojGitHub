package database;
//Maciej Kubiniec R00144142

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private Connection connection = null; // manages connection
	private static Database instance;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	// constructor
	public Database() {
		try {
			// makes sure we have the library loaded.
			Class.forName("com.mysql.jdbc.Driver"); 
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dentist", "root", "");
		} // end try
		catch (SQLException | ClassNotFoundException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch
	} // end PersonQueries constructor

	public Connection DatabaseConnection() {
		return connection;

	}

	public void CloseDB() {
		System.out.println("Close");
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

} // end class PersonQueries
