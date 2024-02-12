package com.codegnan.service;

import java.sql.SQLException;

import com.codegnan.dao.UserDao;
import com.codegnan.exception.DatabaseInternalException;

public class UserService {
	public boolean validate(String username, String password) throws ClassNotFoundException, SQLException, DatabaseInternalException {
		UserDao userDao = new UserDao();
		return userDao.find(username, password);
	}
}