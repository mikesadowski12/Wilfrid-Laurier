package ec.lab;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EJBWebServlet")
public class EJBWebServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private SBStatefulRemote sbsf;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        try {
            out.println("EJB and Web component example<br>");
            int value = Integer.parseInt(request.getParameter("value"));
            out.println("query value: " + value + "<br>");
            out.println("Prediction result: "+sbsf.Predict(value) + "<br>");
            out.println("Prediction call count: " + sbsf.getCounter() + "<br>");     
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            out.close();
        }
    }
}