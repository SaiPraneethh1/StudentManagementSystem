package com.codegnan.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.codegnan.exception.DatabaseInternalException;
import com.codegnan.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(LoginServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Login form submitted");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			if(validate(username, password)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
				rd.forward(request, response);
			}
			else {
				response.sendRedirect("index.jsp");
			}
		} catch (ClassNotFoundException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (DatabaseInternalException e) {
			logger.error(e);
		}
		
	}
	public boolean validate(String username, String password) throws ClassNotFoundException, SQLException, DatabaseInternalException {
		UserService userService = new UserService();
		return userService.validate(username, password);
	}

}