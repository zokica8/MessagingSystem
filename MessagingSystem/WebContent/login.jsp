<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="kurs.messaging.beans.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/messageStyle.css"/>
</head>
<body class="login">
	<div>
		<h3>User Login</h3>
	</div>
	<form action="MessagingController?page=userLoggedIn" method="post">
		<table>
			<tr>
				<td id="add">Username:</td>
				<td><input class="login" type="text" name="username"></td>
			</tr>
			<tr>
				<td id="add">Password:</td>
				<td><input class="login" type="password" name="password"></td>
			</tr>
		</table>
		<br>
		<input class="button" type="submit" value="Submit"/>
	</form>
	<h2 id="error"><%=request.getAttribute("errorMessage")%></h2>
</body>
</html>