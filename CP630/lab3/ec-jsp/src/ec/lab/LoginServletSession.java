package ec.lab;

import java.io.*;  
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  

//  
//@WebServlet("/LoginServlet")
//public class LoginServlet extends HttpServlet {
//  private static final long serialVersionUID = 1L;
//  private final String userID = "admin";
//  private final String password = "cp630";
//  
//  protected void doPost(HttpServletRequest request,
//      HttpServletResponse response) throws ServletException, IOException {
//
//    String user = request.getParameter("user");
//    String pwd = request.getParameter("pwd");
//    
//    if(userID.equals(user) && password.equals(pwd)){
//      Cookie loginCookie = new Cookie("user",user);
//      loginCookie.setMaxAge(30*60); // set expiry time to 30 minutes
//      response.addCookie(loginCookie);
//      response.sendRedirect("LoginSuccess.jsp");
//    }else{
//      RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
//      PrintWriter out= response.getWriter();
//      out.println("<font color=red>Either user name or password is wrong.</font>");
//      rd.include(request, response);
//    }
//  }
//}
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServletSession")
public class LoginServletSession extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final String USERNAME = "admin";
  private final String PASSWORD = "cp630";

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    
    if(USERNAME.equals(username) && PASSWORD.equals(password)){
      HttpSession session = request.getSession();
      session.setAttribute("user", username);
      session.setMaxInactiveInterval(30*60);      
      Cookie loginCookie = new Cookie("user", username);
      response.addCookie(loginCookie);
      String encodedURL = response.encodeRedirectURL("LoginSuccessSession.jsp");
      response.sendRedirect(encodedURL);
    }else{
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/login-session.jsp");
      PrintWriter out= response.getWriter();
      out.println("<font color=red>Incorrect credential.</font>");
      rd.include(request, response);
    }
  }
}