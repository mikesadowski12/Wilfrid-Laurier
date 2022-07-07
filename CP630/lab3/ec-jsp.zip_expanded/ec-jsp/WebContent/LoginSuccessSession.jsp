<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Session login success page</title>
</head>
<body>
	<%
		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					userName = cookie.getValue();
			}
		}

		HttpSession sess = request.getSession(false);
		if (userName == null && sess == null)
			response.sendRedirect("login-session.jsp");
	%>
	<h3>
		<%=userName%> session login successful.
	</h3>
	<br>

	<form action="LogoutServletSession" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>