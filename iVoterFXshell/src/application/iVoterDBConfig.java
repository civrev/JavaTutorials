// based on the https://github.com/HackeaMesta/Game-Store
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class iVoterDBConfig {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN = "jdbc:mysql://localhost/";
	private static final String DB = "iVoterDB";

	// connection  method that connects us to the MySQL database
	public static Connection getConnection() throws SQLException{
		//This is the syntax for connecting to the DB
		// identify DB 'iVoterDB' then use a username 'root' and for this user it
		//has no password
		return DriverManager.getConnection((CONN+DB), USERNAME, PASSWORD);
	}

	// method that displays our errors in more detail if connection fails
	//just basic errors that DB will throw back at you.
	//example try using a bad username/password combo
	public static void displayException(SQLException ex){
		System.out.println("Error Message: " + ex.getMessage());
		System.out.println("Error Code: " + ex.getErrorCode());
		System.out.println("SQL Status: " + ex.getSQLState());

	}
}
