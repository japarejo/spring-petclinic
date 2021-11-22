<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de facturas</title>
<link href="${petclinicCss}" rel="stylesheet"/>
</head>
<body>
	<table>
		<tr>
			<th>Concept</th><th>Total</th><th></th>
			<c:forEach items="${bills}" var="bill">
				<tr>
					<td>${bill.concept}</td>
					<td>${bill.total}</td>
					<td><a href="/bills/delete/${bill.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<p>
	<a href="/bills/create">Create Bill</a>
	</p>
</body>
</html>