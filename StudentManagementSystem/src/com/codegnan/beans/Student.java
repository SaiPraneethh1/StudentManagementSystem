package com.codegnan.beans;


public class Student {
	int id;
	String name;
	String email;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	public String toCSV() {
		return id+", "+name+", "+email;
	}
	public static Student parseStudent(String strStudent) throws NumberFormatException {
		String[] values = strStudent.split(",");
		int id = Integer.parseInt(values[0].trim());
		String name = values[1].trim();
		String email = values[2].trim();
		return new Student(id, name, email);
	}
}
