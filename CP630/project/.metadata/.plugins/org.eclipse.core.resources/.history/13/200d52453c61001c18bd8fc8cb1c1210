package stats.ejb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StatefulServlet")
public class StatsEJBStatefuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    private StatsEJBStateful statsEJBStateful;
    
    @EJB
    private StatsEJBSingleton statsEJBSingleton;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        try {
        	double value = Double.parseDouble(request.getParameter("value"));
            statsEJBStateful.insertData(value);
            statsEJBSingleton.stats();
            statsEJBStateful.createModel();
            
            String summary = statsEJBStateful.getStats();
            out.println("Summary: " + summary + " <br>");     
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            out.close();
        }
    }
}
