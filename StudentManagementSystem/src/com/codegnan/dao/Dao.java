package com.codegnan.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.codegnan.helper.ConnectionManager;

public class Dao {

	Connection con ;
	Logger log = Logger.getLogger(Dao.class);

	public Dao() throws ClassNotFoundException, SQLException {
		log.debug("Dao constructor called");
		con = ConnectionManager.getConnection();
		log.debug("Connection object created");
	}
	public void commit() throws SQLException {
		log.debug("Trying to commit and close connection");
		if(con != null) {
			con.commit();
			log.debug("Committed the connection");
			con.close();
			log.debug("Closed the Connection");
		}
	}
	public void rollback() throws SQLException {
		log.debug("Trying to rollback and close connection");
		if(con != null) {
			con.rollback();
			log.debug("Rolled back the connection");
			con.close();
			log.debug("Closed the connection");
		}
	}

}
