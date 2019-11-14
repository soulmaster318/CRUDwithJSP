<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show users</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<table border=1>
	<thead>
		<tr>
			<th>User ID</th>
			<th>Email</th>
			<th>First Name</th>
			<th>Last name</th>
			<th>Address</th>
			<th>Phone number</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.email}" /></td>
               	<td><c:out value="${user.firstname}" /></td>
                <td><c:out value="${user.lastname}" /></td>
                <td><c:out value="${user.country}" /></td>
                <td><c:out value="${user.phoneno}" /></td>                
			</tr>
		
		</c:forEach>
	</tbody>
</table>
<p ><a href="UserController?action=insert">Add user</a></p>
<p><a href="homeView.jsp">Home</a>
</div>
</body>
</html>