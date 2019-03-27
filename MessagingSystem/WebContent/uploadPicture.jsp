<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="kurs.messaging.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/messageStyle.css" />
<meta charset="ISO-8859-1">
<title>Upload Picture</title>
</head>
<body class="login">
<form action="MessagingController?page=pictureUploaded" method="post" enctype="multipart/form-data">
	
	<h3>Please choose a picture to upload: </h3>
	<input type="file" id="uploadPicture" name="uploadPicture" accept="image/*">
	<br>
	<br>
	<div>
	<input class="button" type="submit" value="Submit" /> 
	</div>
</form>

</body>
</html>