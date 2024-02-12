package com.codegnan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.codegnan.beans.User;
import com.codegnan.exception.DatabaseInternalException;
import com.codegnan.helper.ConnectionManager;

public class UserDao extends Dao {
	
	Logger logger = Logger.getLogger(UserDao.class);

	public UserDao() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
	}
	public boolean save(User user) throws DatabaseInternalException {
		logger.debug("Saving user : "+user);
		boolean res = false;
		String sql = "insert into user values ('"+user.getUsername()+"','"+user.getPassword()+"')";
		logger.debug("Executing SQL : "+sql);
		try {
			Statement stmt = con.createStatement();
			if(1==stmt.executeUpdate(sql)) {
				logger.debug("INSERTed successfully");
				res = true;
			}
		}
		catch(SQLException e) {
			logger.error(e);
			throw new DatabaseInternalException(e);
		}
		logger.debug("Returning true since user inserted successfully");
		return res;
	}
	public boolean find(String username, String password) throws ClassNotFoundException, DatabaseInternalException {
		try (
				Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				) {
			logger.debug("Validating user "+username);
			con.commit();
			
			String sql = "select * from user where username = binary '"+username+"' and password = binary '"+password+"'";
			logger.debug("Executing SQL : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				logger.debug("User validated successfully");
				return true;
			}
			else {
				logger.debug("User validation failed");
				return false;
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new DatabaseInternalException(e);
		}
	}
	public boolean edit(String username, String password) throws DatabaseInternalException {
		logger.debug("Editing user : "+username);
		boolean res = false;
		String sql = "update user set password = '"+password+"' where username = '"+username+"'";
		logger.debug("Executing SQL : "+sql);
		try {
			Statement stmt = con.createStatement();
			if(1==stmt.executeUpdate(sql)) {
				logger.debug("UPDATEd successfully");
				res = true;
			}
		}
		catch(SQLException e) {
			logger.error(e);
			throw new DatabaseInternalException(e);
		}
		logger.debug("Returning true since user udpated successfully");
		return res;
	}
	public boolean delete(String username) throws DatabaseInternalException {
		logger.debug("Deleting user : "+username);
		boolean res = false;
		String sql = "delete from user where username = '"+username+"'";
		logger.debug("Executing SQL : "+sql);
		try {
			Statement stmt = con.createStatement();
			if(1==stmt.executeUpdate(sql)) {
				logger.debug("DELETEd successfully");
				res = true;
			}
		}
		catch(SQLException e) {
			logger.error(e);
			throw new DatabaseInternalException(e);
		}
		logger.debug("Returning true since user deleted successfully");
		return res;
	}

}