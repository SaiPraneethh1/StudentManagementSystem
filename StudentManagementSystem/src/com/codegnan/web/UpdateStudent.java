package com.codegnan.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.codegnan.beans.Student;
import com.codegnan.exception.DatabaseInternalException;
import com.codegnan.service.StudentService;

/**
 * Servlet implementation class UpdateStudent
 */
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudent() {
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
	
	Logger logger = Logger.getLogger(UpdateStudent.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("username")!=null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			Student student = new Student(id, name, email);
			StudentService studentService = new StudentService();
			try {
				studentService.editStudent(student);
			} catch (ClassNotFoundException e) {
				logger.error(e);
			} catch (SQLException e) {
				logger.error(e);
			} catch (DatabaseInternalException e) {
				logger.error(e);
			}
			response.sendRedirect("student.jsp");
		}
		else {
			response.sendRedirect("index.jsp");
		}
		
	}

}