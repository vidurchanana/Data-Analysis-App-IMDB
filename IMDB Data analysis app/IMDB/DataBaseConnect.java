package hw3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {

	Connection con;

	Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String host = "localhost";
			String port = "1521";
			String dbName = "XE";					//dbName needs to be corrected
			String OracleURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
			con = DriverManager.getConnection(OracleURL, "Scott", "tiger");

		} catch (Exception e) {
			System.out.println("Exception in DB connection ");
			e.printStackTrace();
		}
		return con;
	}

	void closeConnection() {
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}
}