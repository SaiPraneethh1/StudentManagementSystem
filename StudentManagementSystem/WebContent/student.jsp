<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
</body>
</html>

<%
}
else{
	response.sendRedirect("index.jsp");
}
%>