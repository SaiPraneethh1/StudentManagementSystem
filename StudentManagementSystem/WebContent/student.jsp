<%@page import="com.codegnan.beans.Student"%>
<%@page import="java.util.List"%>
<%@page import="com.codegnan.service.StudentService"%>
<%@page import="com.codegnan.dao.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
if(session != null && session.getAttribute("username")!=null ){
		%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student</title>
</head>
<body>
	<h2>Student Management System</h2>
	<form method="post" action="studReg">
		ID : <input type="text" name="id"><br><br>
		Name : <input type="text" name="name"><br><br>
		Email : <input type="text" name="email"><br><br>
		<input type="submit" value="Register">
		<input type="reset" value="Cancel">
	</form>
	<br><br>
	<%
	StudentService studentService = new StudentService();
	List<Student> students = studentService.findAllStudents();
	%>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Edit | Delete</th>
		</tr>
		<%
		for(Student student : students){
			%>
			<tr>
				<td><%= student.getId() %></td>
				<td><%= student.getName() %></td>
				<td><%= student.getEmail() %></td>
				<td><a href="editStudent.jsp?id=<%=student.getId()%>">Edit</a> | <a href="#">Delete</a></td>
				
			</tr>
			<%
		}
		
		%>
		
	</table>
	<br><br>
	<a href="signout">Signout</a>
</body>
</html>

<%
}
else{
	%>
	<jsp:forward page="index.jsp">
		<jsp:param value="Invalid way to access" name="msg"/>
	</jsp:forward>
	<%
}
%>