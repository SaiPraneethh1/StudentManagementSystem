package com.codegnan.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.codegnan.beans.Student;
import com.codegnan.dao.StudentDao;
import com.codegnan.exception.DatabaseInternalException;
import com.codegnan.exception.InvalidStudentIdException;

public class StudentService {
	Logger logger = Logger.getLogger(StudentService.class);
	
	public Student findStudentById(int id) throws ClassNotFoundException, SQLException, InvalidStudentIdException, DatabaseInternalException {
		logger.debug("finding a student by id : "+id);
		StudentDao studentDao = new StudentDao();
		Student student = studentDao.findById(id);
		logger.debug("Student found with the given id "+id);
		return student;
	}
	public List<Student> findAllStudents() throws ClassNotFoundException, SQLException, DatabaseInternalException{
		logger.debug("finding all students existing");
		StudentDao studentDao = new StudentDao();
		List<Student> students = studentDao.findAll();
		logger.debug("found "+students.size()+" and returning");
		return students;
	}
	public boolean saveStudent(Student student) throws ClassNotFoundException, SQLException, DatabaseInternalException {
		logger.debug("Saving a student "+student);
		StudentDao studentDao = new StudentDao();
		boolean result = studentDao.save(student);
		if(result) {
			studentDao.commit();
			logger.debug("Saved student successfully with id : "+student.getId());
			return true;
		}
		else {
			studentDao.rollback();
			logger.debug("Failed to save a student with id : "+student.getId());
			return false;
		}
	}
	public boolean editStudent(Student student) throws ClassNotFoundException, SQLException, DatabaseInternalException {
		logger.debug("Editing a student "+student);
		StudentDao studentDao = new StudentDao();
		boolean result = studentDao.edit(student);
		if(result) {
			studentDao.commit();
			logger.debug("Saved student successfully with id : "+student.getId());
			return true;
		}
		else {
			studentDao.rollback();
			logger.debug("Failed to save a student with id : "+student.getId());
			return false;
		}
	}
//	public boolean deleteStudentById(int id) throws ClassNotFoundException, SQLException, DatabaseInternalException {
//		logger.debug("Deleting student with id : "+id);
//		StudentDao studentDao = new StudentDao();
//		boolean res = studentDao.delete(id);
//		logger.debug("student deleted successfully with the id : "+id);
//		return res;
//	}
	public boolean deleteStudent(int id) throws DatabaseInternalException, SQLException, ClassNotFoundException {
		logger.debug("Deleting  the stduent with id :"+id);
		StudentDao studentDao=new StudentDao();
		boolean result = studentDao.delete(id);
		logger.debug("student deleted successfully with the id : "+id);
	
		if(studentDao.delete(id)) {
			
			studentDao.commit();
			logger.debug("student deleted sucessfully"+id);
			return true;
		}
		else {
			studentDao.rollback();
			logger.debug("can not delete student"+id);
			return false;
		}
	}
}