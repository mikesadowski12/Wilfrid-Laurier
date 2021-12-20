package ec.lab;

import javax.servlet.*;
import java.io.*;

// This servlet will be configured by deployment descriptor web.xml
public class ECServlet implements Servlet {
    ServletConfig config = null;
    public void init(ServletConfig config) {
        this.config = config;
         System.out.println(config.getServletName() + " init() method is called when it is created");
    }
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<p>config.getServletName():" + config.getServletName() + "</p>");
        pw.println("<p>getServletInfo():" + getServletInfo() + "</p>");
        pw.println("<p>getClass().getName():" + getClass().getName() + "</p>");
        pw.println("<p>getClass().getSimpleName():" + getClass().getSimpleName() + "</p>");
        pw.println("<p>getClass().getCanonicalName():" + getClass().getCanonicalName()+ "</p>");
    }
    
    public void destroy() {
        System.out.println(this.getClass().getCanonicalName()+" destroy() method is called when the servlet is destroyed such as undeployment");
    }
    public String getServletInfo() {
        return "EC Servlet interface example";
    }
    public ServletConfig getServletConfig() {
        return config;
    }
}
