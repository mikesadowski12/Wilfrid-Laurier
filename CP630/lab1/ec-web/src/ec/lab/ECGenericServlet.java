package ec.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

// This Servlet is configured by the following annotation
@WebServlet(name = "ec-gs", initParams = {
    @WebInitParam(name = "username", value = "admin"),
    @WebInitParam(name = "password", value = "cp630")
}, urlPatterns = "/ecgs")
public class ECGenericServlet extends GenericServlet {
    private static final long serialVersionUID = 1L;
    
    private ServletConfig config = null;

    // Pass configuration to the servlet instance 
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        System.out.println("init() is called when it is created : " + config.getServletName());
    }
    
    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }
    
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service() method is invoked");
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.print("<html>");
        pw.print("<body>");
        pw.print("<h1>EC GenericServlet Example</h1>");
        pw.print("<p>Servlet name: " + getServletName() + "</p>");
        pw.print("<p>getInitParameter(\"username\"): " + getInitParameter("username") + "</p>");
        pw.print("<p>getInitParameter(\"password\"): " + getInitParameter("password") + "</p>");
        pw.print("<p>getServletInfo(): " + getServletInfo() + "</p>");
        pw.print("<a href=\"index.html\">Index page</a>");
        pw.print("</body>");
        pw.print("</html>");
        pw.close();
    }
    
    @Override
    public String getServletInfo() {
        return "EC Generic Servelet example, configuration, lifecycle";
    }

    @Override
    public void destroy() {
        System.out.println("destroy() is called when the servlet is destroyed such as undeployment");
    }
}