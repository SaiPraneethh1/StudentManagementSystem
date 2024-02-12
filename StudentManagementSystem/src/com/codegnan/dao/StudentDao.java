package com.codegnan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.codegnan.beans.Student;
import com.codegnan.exception.DatabaseInternalException;
import com.codegnan.exception.InvalidStudentIdException;
import com.codegnan.helper.ConnectionManager;

public class StudentDao extends Dao {
	Logger log = Logger.getLogger(StudentDao.class);
	
	public StudentDao() throws SQLException, ClassNotFoundException {
		
	}
	
	// find a single record by the ID
	public Student findById(int id) throws ClassNotFoundException, InvalidStudentIdException, DatabaseInternalException {
		log.debug("finding a student with id : "+id);
		Scanner sc = new Scanner(System.in);
		Student student = null;
		Connection con = null ;
		try {
			log.debug("getting local connection");
			con = ConnectionManager.getConnection();
			con.commit();
			Statement stmt = con.createStatement();
			String sql = "select * from student where id = "+id;
			log.debug("Executing sql command : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				log.debug("Found record with the id : "+id);
				String name = rs.getString("name");
				String email = rs.getString("email");
				student = new Student(id, name, email);
			}
			else {
				log.warn("Didn't find student with id : "+id);
				throw new InvalidStudentIdException("Student not found with id : "+id);
			}
		}
		catch(SQLException e) {
			throw new DatabaseInternalException(e);
		}
		finally {
			try {
				log.debug("Closing local connection");
				con.close();
			} catch (SQLException e) {
				throw new DatabaseInternalException(e);
			}
		}
		log.debug("Returning student with id : "+student.getId()+" and name : "+student.getName());
		return student;
	}
	// find All students
	public List<Student> findAll() throws ClassNotFoundException, DatabaseInternalException {
		log.debug("finding all students");
		ArrayList<Student> students = new ArrayList<>();
		Connection con = null ;
		try {
			log.debug("getting local connection");
			con = ConnectionManager.getConnection();
			con.commit();
			Statement stmt = con.createStatement();
			String sql = "select * from student";
			log.debug("Executing SQL command : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Student student = new Student(id, name, email);
				log.debug("adding student with id "+student.getId()+" to list");
				students.add(student);
			}
		}
		catch(SQLException e) {
			throw new DatabaseInternalException(e);
		}
		finally {
			try {
				log.debug("Closing local connection");
				con.close();
			} catch (SQLException e) {
				throw new DatabaseInternalException(e);
			}
		}
		log.debug("Returning "+students.size()+" students in the list.");
		return students;
	}
	// save
	public boolean save(Student student) throws DatabaseInternalException {
		log.debug("Saving student : "+student);
		boolean res = false;
		String sql = "insert into student values (?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getEmail());
			if(pstmt.executeUpdate()==1) {
				log.debug("Student successfully saved with id : "+student.getId());
				res = true;
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DatabaseInternalException(e);
		}
		log.debug("returning true since student saved successfully with id : "+student.getId());
		return res;
	}
	// edit
	public boolean edit(Student student) throws DatabaseInternalException {
		log.debug("Editing student : "+student);
		boolean res = false;
		String sql = "update student set name = ?, email = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getEmail());
			pstmt.setInt(3, student.getId());
			if(pstmt.executeUpdate() == 1) {
				log.debug("Student successfully edited with ID "+student.getId());
				res = true;
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DatabaseInternalException(e);
		}
		log.debug("returning true since student edited successfully with id : "+student.getId());
		return res;
	}
	// delete
	public boolean delete(int id) throws DatabaseInternalException {
		log.debug("Deleting student with id : "+id);
		boolean res = false;
		String sql = "delete from student where id = "+id;
		log.debug("Executing SQL command : "+sql);
		try {
			Statement stmt = con.createStatement();
			if(stmt.executeUpdate(sql) == 1) {
				log.debug("Successfully deleted the student with id : "+id);
				res = true;
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DatabaseInternalException(e);
		}
		log.debug("returning true since student deleted successfully with id : "+id);
		return res;
	}
}