<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
	</head>
	<body>
		<form:form id="PostForm" method="post" action="ec-spring3" modelAttribute="ECSpring">

			<form:label path="name">Enter EC Spring name</form:label>
			<form:input id="name" name="name" path="" /><br>
			<input type="submit" value="Submit" />
		</form:form>
	</body>
</html>
