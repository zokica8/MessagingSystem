<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, kurs.messaging.beans.*" %>
<%@ page import="kurs.messaging.services.*" %>
<%@ page import="java.time.format.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Of All Posts In The Messaging System</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/messageStyle.css" />
</head>
<body class="mainPage">
<% List<PostsSent> posts = (List<PostsSent>) request.getAttribute("posts");
	User user = (User) request.getAttribute("userMessages");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	LikesService service = new LikesService();
	service.returnConnection();%>
<table class="posts">
	<tr>
		<th class="header">User Name: </th>
		<th class="header">Message: </th>
		<th class="header">Time Of Message: </th>
	</tr>
	<% for(PostsSent post: posts) { %>
	
	<tr>
		<td id="username">
		<a id="linkBack" href="MessagingController?page=listOfPosts&id=<%= post.getUsername() %>"><%= post.getUsername() %></a>
		</td>
		<td id="message">
		<%= post.getContent() %>
		<p><a id="likeLink" href="MessagingController?page=like&liked=<%= post.getPost_ID() %>">&#9829;</a>
		<%= post.getLikesCount() %>
		<br>
		<% if(post.getLikesCount() > 0) { %>
		<span class="whoLikedIt">
		<% List<PostsSent> likes = (List<PostsSent>) service.getLikesPerPerson(post.getPost_ID());  
		for(PostsSent like : likes) {
			out.println(like.getUsername() + "<br>");
		}
		%>
		</span>
		</p>
		</td>
		<% } %>
		<td id="time"><%= post.getTimeOfMessage().format(formatter) %></td>
	</tr>
	<% } %>
</table>
<br>
<p><a id="link" href="MessagingController">Back to Main Menu</a></p>

</body>
</html>