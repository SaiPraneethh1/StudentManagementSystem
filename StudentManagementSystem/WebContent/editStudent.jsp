<%@page import="com.codegnan.beans.Student"%>
<%@page import="com.codegnan.service.StudentService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="ISO-8859-1"%>
<%
	if (session != null && session.getAttribute("username") != null) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editStudent</title>
</head>
<body>
	<%
		int id = Integer.parseInt(request.getParameter("id"));
			StudentService studentService = new StudentService();
			Student student = studentService.findStudentById(id);
	%>
	<h3>Edit student details</h3>
	<form method="post" action="updateStudent">
		ID : <input type="text" name="id" value="<%=student.getId()%>" readonly="readonly"><br><br>
		Name : <input type="text" name="name" value="<%=student.getName()%>"><br><br>
		Email : <input type="text" name="email" value="<%=student.getEmail()%>"><br><br>
		<input type="submit" value="Update">
		<input type="reset" value="Cancel">
	</form>
</body>
</html>
<%
	} else {
		response.sendRedirect("index.jsp");
	}
%>
