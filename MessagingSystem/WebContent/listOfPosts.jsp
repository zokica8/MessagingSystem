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
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	LikesService service = new LikesService();
	CommentsService commentsService = new CommentsService();
	service.returnConnection();
	commentsService.returnConnection();
	%>
	
<table class="posts">
	<tr>
		<th class="header"></th>
		<th class="header">User Name: </th>
		<th class="header">Message: </th>
		<th class="header">Time Of Message: </th>
		<th class="header">Insert Comment: </th>
		<th class="header">Delete Comment: </th>
	</tr>
	<% for(PostsSent post: posts) { %>
	
	<tr>
		<% if(post.getImageId() != null)  {%>
		<td><img class="image" alt="image" src="${pageContext.request.contextPath}/userImages/<%= post.getImageId() %>"></td>
		<% } else { %>
		<td><input type="hidden"></td>
		<% } %>
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
		<br>
		<br>
		<% } %>
		<p> Comments: </p>
		<% List<Comments> comments = commentsService.getCommentsPerPost(post.getPost_ID());  
			for(Comments c : comments) {
		%>
		<span class="content">
			<%= c.getContent() + "<br>" %> 
		</span>
		<% } %>
		</td>
		<td id="time"><%= post.getTimeOfMessage().format(formatter) %></td>
		<td>
		<a href="MessagingController?page=comment&postId=<%= post.getPost_ID() %>">
		<input class="button2" type="submit" value="Insert Comment">
		</a>
		</td>
		<td>
		<a href="MessagingController?page=commentDeleted">
		<input class="button2" type="submit" value="Delete Comment">
		</a>
		</td>
		<% } %>
	</tr>	
</table>
<br>
<p><a id="link" href="MessagingController">Back to Main Menu</a></p>

</body>
</html>