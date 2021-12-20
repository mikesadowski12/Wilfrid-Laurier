package stats.ejb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StatelessServlet")
public class StatsEJBStatelessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB
    private StatsEJBStateless statsEJBStateless;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        try {
            String queryMessage = request.getParameter("value");
            
            switch(queryMessage) {
//	            case "count":
//	            	int count = statsEJBStateless.getCount();
//	            	out.println("Count: " + count + " <br>");
//	            	break;
//	            case "min":
//	            	double min = statsEJBStateless.getMin();
//	            	out.println("Min: " + min + " <br>");
//	            	break;
//	            case "max":
//	            	double max = statsEJBStateless.getMax();
//	            	out.println("Max: " + max + " <br>");
//	                break;
//	            case "mean":
//	            	double mean = statsEJBStateless.getMean();
//	            	out.println("Mean: " + mean + " <br>");
//	                break;
//	            case "std":
//	            	double std = statsEJBStateless.getSTD();
//	            	out.println("Standard Deviation: " + std + " <br>");
//	                break;
//	            case "summary":
//	            	String summary = statsEJBStateless.summaryString();
//	            	out.println("Summary: " + summary + " <br>");
//	                break;
	            default:
	            	out.println("Unknown query message <br>");
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            out.close();
        }
    }
}
