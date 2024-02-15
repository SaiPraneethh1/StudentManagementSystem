package com.codegnan.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.codegnan.exception.DatabaseInternalException;
import com.codegnan.service.StudentService;

/**
 * Servlet implementation class DeleteStudent
 */
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		if(session!= null && session.getAttribute("username")!=null) {
			int id =Integer.parseInt(request.getParameter("id"));
			StudentService studentService = new StudentService();
		
			try {
				logger.debug("deleted  student with id : "+id);
				studentService.deleteStudent(id);
				logger.debug("deleted successfully");
				
			} catch (ClassNotFoundException e) {
				logger.debug(e);
			} catch (DatabaseInternalException e) {
				logger.debug(e);
			} catch (SQLException e) {
				logger.debug(e);
			}
			response.sendRedirect("student.jsp");
			
		}
		else {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	Logger logger= Logger.getLogger(DeleteStudent.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

}