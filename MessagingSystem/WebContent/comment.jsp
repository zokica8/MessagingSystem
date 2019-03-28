<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="kurs.messaging.beans.*, java.util.*, kurs.messaging.services.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Comment</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/messageStyle.css" />
</head>
<body>
<% User user = (User) session.getAttribute("user"); 
   Integer postID = (Integer) request.getAttribute("postID");
%>
<form action="MessagingController?page=commentInserted" method="post">
	<p>Insert Your Comment: </p>
	<input type="hidden" name="userID" value="<%= user.getUser_id() %>">
	<input type="hidden" name="postID" value="<%= postID %>"> 
	<textarea rows="10" cols="50" name="commentMessage"></textarea>
	<br>
	<br>
	<input class="button" type="submit" value="Submit">
</form>

</body>
</html>