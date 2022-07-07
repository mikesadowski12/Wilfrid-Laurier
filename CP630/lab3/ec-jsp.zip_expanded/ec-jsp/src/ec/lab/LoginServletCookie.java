package ec.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServletCookie")
public class LoginServletCookie extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final String USERNAME = "admin";
  private final String PASSWORD = "cp630";
  
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    String user = request.getParameter("username");
    String pwd = request.getParameter("password");
    
    if(USERNAME.equals(user) && PASSWORD.equals(pwd)){
      Cookie loginCookie = new Cookie("user", user);
      loginCookie.setMaxAge(30*60); // set expiry time to 30 minutes
      response.addCookie(loginCookie);
      System.out.println("Cookie to client, name:"+loginCookie.getName() + ",value:" + loginCookie.getValue());
      response.sendRedirect("LoginSuccessCookie.jsp");
    }else{
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/login-cookie.jsp");
      PrintWriter pw= response.getWriter();
      pw.println("<font color=red>Wrong credential.</font>");
      rd.include(request, response);
    }
  }
}