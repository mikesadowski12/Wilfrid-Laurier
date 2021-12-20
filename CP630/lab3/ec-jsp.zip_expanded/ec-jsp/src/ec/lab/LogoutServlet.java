package ec.lab;

import java.io.*;  
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  
  
//@WebServlet("/LogoutServlet")
//public class LogoutServlet extends HttpServlet {
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      response.setContentType("text/html");
//      Cookie loginCookie = null;
//      Cookie[] cookies = request.getCookies();
//      if(cookies != null){
//      for(Cookie cookie : cookies){
//        if(cookie.getName().equals("user")){
//          loginCookie = cookie;
//          break;
//        }
//      }
//      }
//      if(loginCookie != null){
//        loginCookie.setMaxAge(0);
//          response.addCookie(loginCookie);
//      }
//      response.sendRedirect("login.html");
//    }
//}

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      Cookie[] cookies = request.getCookies();
      if(cookies != null){
      for(Cookie cookie : cookies){
        if(cookie.getName().equals("JSESSIONID")){
          System.out.println("JSESSIONID="+cookie.getValue());
        }
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      }
      }
      HttpSession session = request.getSession(false);
      System.out.println("User="+session.getAttribute("user"));
      if(session != null){
        session.invalidate();
      }
      response.sendRedirect("login.html");
    }
}