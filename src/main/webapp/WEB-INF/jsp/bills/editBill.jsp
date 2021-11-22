<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit bill</title>
</head>
<body>
	<form:form action="/bills/save" method="post" modelAttribute="bill">
		<form:hidden path="id"/>
		Concept: <form:input  path="concept"/> <form:errors path="concept"/>
		<br>
		Total: <form:input  path="total"/> <form:errors path="total" style="color:red"/>
		<br>
		<input type="submit" value="save"></input> 
		<br>
		
	</form:form>
</body>
</html>