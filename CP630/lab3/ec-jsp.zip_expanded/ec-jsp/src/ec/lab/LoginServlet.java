package ec.lab;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String USERNAME = "admin";
	private final String PASSWORD = "cp630";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		PrintWriter pw = response.getWriter();

		if (USERNAME.equals(username) && PASSWORD.equals(password)) {
			response.sendRedirect("LoginSuccess.html");
		} else {
			response.sendRedirect("login.html");
		}

		pw.close();

	}
}
