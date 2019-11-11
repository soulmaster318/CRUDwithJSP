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
			<th>First Name</th>
			<th>Last name</th>
			<th>Address</th>
			<th>Phone number</th>
			<th colspan=2>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.id}" /></td>
               	<td><c:out value="${user.firstname}" /></td>
                <td><c:out value="${user.lastname}" /></td>
                <td><c:out value="${user.country}" /></td>
                <td><c:out value="${user.phoneno}" /></td>
                <td><a href="UserController?action=edit&id=<c:out value="${user.id}"/>">Update</a></td>
                <td><a href="UserController?action=delete&id=<c:out value="${user.id}"/>">Delete</a></td>
			</tr>
		
		</c:forEach>
	</tbody>
</table>
<p ><a href="UserController?action=insert">Add user</a></p>
</div>
</body>
</html>