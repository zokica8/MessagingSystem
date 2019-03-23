<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="kurs.messaging.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Logged In</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/messageStyle.css" />
</head>
<body class="login">
<% User user = (User) session.getAttribute("user"); %>
<h3 id="success">User successfully logged in!!</h3>
<br>
<div id="success"><%= user.getUsername() %></div>
<br>
<a id="linkBack" href="<%= response.encodeURL(request.getContextPath()) %>">Back To Main Menu</a>
</body>
</html>