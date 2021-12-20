package stats_osgi_web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import stats.osgi.impl.StatsOSGiImpl;

public class OSGiServlet extends HttpServlet {
	private StatsOSGiImpl tservice;
    
    public OSGiServlet() {
         tservice = null;
    }

    public OSGiServlet(StatsOSGiImpl service) {
        tservice = service;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "test"; 
        PrintWriter writer = response.getWriter();

        try {
        	String queryMessage = request.getParameter("value");
        	
            switch(queryMessage) {
            case "count":
            	int count = tservice.getCount();
            	result = "{\"count\": " + count + "}";
            	break;
            case "min":
            	double min = tservice.getMin();
            	result = "{\"min\": " + min + "}";
            	break;
            case "max":
            	double max = tservice.getMax();
            	result = "{\"max\": " + max + "}";
                break;
            case "mean":
            	double mean = tservice.getMean();
            	result = "{\"mean\": " + mean + "}";
                break;
            case "std":
            	double std = tservice.getSTD();
            	result = "{\"std\": " + std + "}";
                break;
            case "summary":
            	String summary = tservice.toJSON();
            	result = "{\"summary\": " + summary + "}";
                break;
            default:
            	result = "{\"error\": \"unknown query message\"}";
        }
        	
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>stats-osgi-web component</title>");
            writer.println("</head>");
            writer.println("<body align='center'>");
            writer.println("<h1>OSGi Servlet</h1>");
            writer.println("<h2>" + result + "</h2>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
        	writer.close();
        }
    }
}