package ec.osgi.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import ec.osgi.serviceapi.CalculatorService;

public class OSGiServlet extends HttpServlet {
    private CalculatorService tservice;
    
    public OSGiServlet() {
         tservice = null;
    }

    public OSGiServlet(CalculatorService service) {
        tservice = service;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "test"; 
        if (tservice != null) {
            result = "3*4="+tservice.multiplication(3, 4);
        }
        
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>OSGi web component</title>");
            writer.println("</head>");
            writer.println("<body align='center'>");
            writer.println("<h1>OSGi Servlet</h1>");
            writer.println("<h2>"+result+"</h2>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}