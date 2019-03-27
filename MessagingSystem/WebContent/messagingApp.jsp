<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="kurs.messaging.beans.*, java.util.*, java.time.format.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Messaging Application</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/messageStyle.css"/> 
</head>
<body class="mainPage">
<% User user = (User) session.getAttribute("user"); 
	List<PostsSent> trending = (List<PostsSent>) request.getAttribute("trending");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");%>
<h1 id="welcome">Welcome to MessApp!!</h1>
<h2 id="place">The best place for sending messages!</h2>
<div>
	<h2 id="options">Please choose one of the options below: </h2>
</div>
<% if(user == null) { %>
<div>
	<a id="link" href="MessagingController?page=addUser">Create New User</a>
</div>
<br>
<div>
	<a id="link" href="MessagingController?page=login">Login</a>
</div>
<br>
	<p id="mostTrending"> Most trending messages: </p>
<table class="posts">
	<tr>
		<th class="header"></th>
		<th class="header">User Name: </th>
		<th class="header">Message: </th>
		<th class="header">Time Of Message: </th>
		<th class="header">Likes: </th>
	</tr>
	<tr>
		<% for(PostsSent message : trending) { %>
		<% if(message.getImageId() != null)  {%>
		<td id="image"><img alt="image" src="${pageContext.request.contextPath}/userImages/<%= message.getImageId() %>"></td>
		<% } else { %>
		<td><input type="hidden"></td>
		<% } %>
		<td id="username"><%= message.getUsername() %></td>
		<td id="message"><%= message.getContent() %></td>
		<td id="time"><%= message.getTimeOfMessage().format(formatter) %></td>
		<td id="like"><%= message.getLikesCount() %></td>
	</tr>
	<% } %>
</table>
<% } %>
<% if(user != null) { %>
<h4 id="username"><%= user.getUsername() %></h4>
<div>
	<a id="link" href="MessagingController?page=updateUser">Update User</a>
</div>
<br>
<div>
	<a id="link" href="MessagingController?page=updateUser">Delete User</a>
</div>
<br>
<div>
	<a id="link" href="MessagingController?page=insertPost">Insert Post</a>
</div>
<br>
<div>
	<a id="link" href="MessagingController?page=listOfPosts">List Of Posts</a>
</div>
<br>
<div>
	<a id="link" href="MessagingController?page=logout">Logout</a>
</div>
<% } %>
</body>
</html>