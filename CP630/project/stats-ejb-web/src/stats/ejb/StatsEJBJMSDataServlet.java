package stats.ejb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StatsEJBJMSDataServlet")
public class StatsEJBJMSDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB
    private StatsEJBJMSStateless statsEJBJMSStateless;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        try {
        	String queryMessage = request.getParameter("value");
        	statsEJBJMSStateless.publish(queryMessage);
            out.println(queryMessage + " message sent from StatsEJBJMSDataServlet <br>");     
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            out.close();
        }
    }
}
