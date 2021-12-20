<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP login check</title>
</head>
<body>
	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ((username.equals("admin") && password.equals("cp630"))) {
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			response.sendRedirect("LoginSuccess.jsp");
		} else {
			response.sendRedirect("error.jsp");
		}
	%>
</body>
</html>
