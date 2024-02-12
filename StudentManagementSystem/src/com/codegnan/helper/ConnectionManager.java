package com.codegnan.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionManager {
	
	static Logger log = Logger.getLogger(ConnectionManager.class);

	private ConnectionManager() {
		
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		log.debug("Creating connection");
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jap_4";
		String user = "root";
		String password = "Admin";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, password);
		con.setAutoCommit(false);
		log.debug("Connection created successfully");
		return con;
	}

}
