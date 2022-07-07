package ec.lab;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/echs")
public class ECHttpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String value =req.getParameter("value");
        System.out.println(value);
        pw.println("<html><body>");
        pw.println("GET method, value:"+value);
        pw.println("</body></html>");
        pw.close();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        String value =req.getParameter("value");
        System.out.println(value);      
        pw.println("POST method, received value:"+value);
        pw.close();
    }
}