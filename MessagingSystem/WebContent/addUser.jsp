<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="kurs.messaging.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/messageStyle.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script>

<script type="text/javascript">
	function loading() {

		$("#password").keyup(checkPasswordsMatch);
		$("#confirm").keyup(checkPasswordsMatch);

		$("#details").submit(canSubmit);

	}

	function canSubmit() {
		var password = $("#password").val();
		var confirm = $("#confirm").val();

		if(password != confirm) {
			alert("Passwords do not match!");
			return false;
		}
		else {
			return true;
		}
	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirm = $("#confirm").val();

		if (password.length > 3 || confirm.length > 3) {

			if (password == confirm) {
				$("#matchpass").text("Passwords match.");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("error");
			} else {
				$("#matchpass").text("Passwords do not match.");
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("valid");
			}
		}
	}

	$(document).ready(loading);
</script>

</head>
<body class="addUser">
	<%
		User user = (User) request.getAttribute("user");
	%>
	<div>
		<h3>Create New User</h3>
	</div>
	<form id="details" action="MessagingController?page=userCreated" method="post">
		<table>
			<tr>
				<td id="add">Username:</td>
				<td><input class="inputFields" type="text" name="username">
				</td>
			</tr>
			<tr>
				<td id="add">Password:</td>
				<td><input class="inputFields" id="password" type="password" name="password">
				</td>
			</tr>
			<tr>
				<td id="add">Confirm Password:</td>
				<td><input class="inputFields" id="confirm" type="password" name="confirm">
					<div class="error" id="matchpass"></div></td>
			</tr>
		</table>
		<br> <input class="button" type="submit" value="Submit" />
	</form>
	<h2 id="error"><%=request.getAttribute("errorMessage")%></h2>
</body>
</html>