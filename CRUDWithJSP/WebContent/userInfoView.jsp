<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<h2>Hello ${user.email}</h2>
<p>First name: <c:out value="${user.firstname}" /> </p>
<p>Last name: <c:out value="${user.lastname}" /> </p>
<p>Address: <c:out value="${user.country}" /> </p>
<p>Phone number: <c:out value="${user.phoneno}" /> </p>
<a href="UserController?action=listUser">View users</a>
<a href="UserController?action=edit&id=<c:out value="${user.id}"/>">Update</a>
<a href="UserController?action=delete&id=<c:out value="${user.id}"/>">Delete</a>
</body>
</html>