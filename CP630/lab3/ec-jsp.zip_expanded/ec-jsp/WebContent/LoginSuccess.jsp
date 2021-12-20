<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>
	<%	
	    String username = null;
		HttpSession sess = request.getSession(false);
		
		if (sess == null) {
			response.sendRedirect("login.jsp");
		} else {
			username = sess.getAttribute("username").toString();
			String password = sess.getAttribute("password").toString();
			
			if (!username.equals("admin") || !password.equals("cp630")) {
				response.sendRedirect("login.jsp");
			}
		}
	%>
	<h3>
		<%=username%> Login successful.
	</h3>
	<br>
	
	<form action="Logout.jsp" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>