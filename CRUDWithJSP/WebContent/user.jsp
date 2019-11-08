<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add new user</title>
</head>
<body>
<form method="POST" action='UserController' name="frmAddUser">
	ID: <input type="text" readonly="readonly" name="id" value="<c:out value="${user.id}"/>" <br/>>
	First name: <input
            type="text" name="firstname"
            value="<c:out value="${user.firstname}" />" /> <br /> 
    Last Name : <input
            type="text" name="lastname"
            value="<c:out value="${user.lastname}" />" /> <br />
    Address : <input
            type="text" name="country"
            value="<c:out value="${user.country}" />" /> <br />
    Phone number : <input
            type="text" name="phone"
            value="<c:out value="${user.phoneno}" />" /> <br />
            <input
            type="submit" value="Submit" />
</form>
</body>
</html>