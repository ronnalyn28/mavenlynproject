package com.herokuapp.theinternet.pages;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MySqlMain extends BasePageObject {

	// Constant for Database Username
	String DB_USER = "root";
	// Constant for Database Password
	String DB_PASSWORD = "Temp1234";
	// Constant for Database URL
	String DB_URL = "jdbc:mysql://localhost:3306/world";
		// Format for MySQL: jdbc:mysql://hostname/ databaseName
			// For Oracle - "jdbc:oracle:thin:@localhost:1521/sid"
			//Format for Oracle: jdbc:oracle:thin:@hostname:port Number:databaseName
	
	// Connection object
	static Connection conn = null;
	// Statement object
	Statement stmt;
	// Result Set
	ResultSet results = null;

	// String myDriver = "com.mysql.jdbc.Driver"; --this is one way of initializing driver
			// "oracle.jdbc.driver.OracleDriver" - this is for Oracle

	public MySqlMain(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void connectDB() {
		// Properties for creating connection to database
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "Temp1234");

		try

		{
			// STEP 1: Register JDBC driver
			// Class.forName(myDriver); --one way of registering driver
			Driver myDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(myDriver);

			// STEP 2: Get connection to DB
			log.info("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// conn = DriverManager.getConnection(DB_URL, props); --another way of
			// connecting to DB
			log.info("Connected database successfully...");

			// STEP 3: Statement object to send the SQL statement to the Database
			log.info("Creating statement...");
			stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeQuery() {
	String query = "SELECT * FROM world.city WHERE CountryCode = 'AFG';";
		try {
			// STEP 4: Extract data from result set
			results = stmt.executeQuery(query);
			while (results.next()) {
				int id = results.getInt("ID");
				String Name = results.getString("Name");
				String CountryCode = results.getString("CountryCode");
				String District = results.getString("District");
				String Population = results.getString("Population");

				// Display Values
				log.info("ID: " + id);
				log.info("Name: " + Name);
				log.info("Country Code: " + CountryCode);
				log.info("District: " + District);
				log.info("Population: " + Population);
			}
			results.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

}
	
	public void closeDB() {
		try {
			if (results != null)
				results.close();
			if (stmt != null)
				conn.close();
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
}
