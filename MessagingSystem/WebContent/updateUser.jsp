<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, kurs.messaging.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Or Delete User</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/messageStyle.css" />
</head>
<body class="login">
<% User user = (User) session.getAttribute("user"); %>
<h3>Update User</h3>
<form action="MessagingController?page=userUpdated" method="post">
	<table>
		<tr>
			<td id="add">Old Password:</td>
			<td><input class="login" type="password" name="oldPassword"></td>
		</tr>
		<tr>
			<td id="add">New Password:</td>
			<td><input class="login" type="password" name="newPassword"></td>
		</tr>
	</table>
	<br>
	<input class="button" type="submit" value="Submit">
</form>
<br>
<h2 id="error"><%= request.getAttribute("notEqualPasswords") %></h2>
<h2 id="error"><%= request.getAttribute("lengthPassword") %></h2>
<br>
<h3>Delete User</h3>
<% if(user != null)  { %>
<input type="hidden" name="delete" value="<%= user.getUser_id() %>"> 
<% } %>
<a id="link" href="MessagingController?page=userDeleted">Delete User</a>
</body>
</html>