<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="kurs.messaging.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Post</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/messageStyle.css" />
</head>
<body class="insert">
<% User user = (User) session.getAttribute("user"); 
	if(user != null) {
%>
<p id="usernameForPost"><%= user.getUsername() %></p>
<% } %>
<form action="MessagingController?page=postInserted" method="post">
	<input type="hidden" name="id" value="<%= user.getUser_id() %>">
	<table>
		<tr>
			<td class="message">Your message: </td>
			<td><textarea rows="10" cols="50" name="textMessage"></textarea></td>
		</tr>
	</table>
	<br>
	<input class="button" type="submit" value="Submit">
<br>
<p><%= request.getAttribute("postErrorMessage") %></p>
</form>
</body>
</html>