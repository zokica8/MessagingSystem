<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="kurs.messaging.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Created</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/messageStyle.css" />
</head>
<body class="addUser">
<% User user = (User) request.getAttribute("username");
%>
<h3>User was successfully created!</h3>
<br>
<table class="userCreated">
	<tr>
		<td id="add">User:</td>
		<td id="add"><%= user.getUsername() %></td>
	</tr>
</table>

<br>
<a id="link" href="MessagingController">Back To Home Page</a>

</body>
</html>