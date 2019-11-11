<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Add new user</title>
</head>
<body>
<form method="POST" action='UserController' name="frmAddUser">
	<div class="container">
	<h1>Add User Form</h1>
	<input type="hidden" readonly="readonly" name="id" value="<c:out value="${user.id}"/>">
	<table><tr><td>First name:</td><td> <input            type="text" name="firstname"            value="<c:out value="${user.firstname}" />" /> </td></tr>
	<tr><td>Last Name :</td><td><input type="text" name="lastname"     value="<c:out value="${user.lastname}" />" /></td></tr> 
    <tr><td>Address :</td><td><input  type="text" name="country"      value="<c:out value="${user.country}" />" /></td></tr>  
    <tr><td>Phone number :</td><td><input type="text" name="phone"   value="<c:out value="${user.phoneno}" />" /></td></tr> 
     
            
    </table>
    <input
            type="submit" value="Submit" />
            <p>${error_message1}</p>
            <p>${error_message2}</p>
            <p>${error_message3}</p>
            <p>${error_message4}</p>
            </div>
            
            
</form>
</body>
</html>