package stats.ejb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StatsJMSDataServlet")
public class StatsJMSDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	JMSContext context;

	@Resource(lookup = "java:/queue/test")
	private Queue queue;

	@Resource(lookup = "java:/topic/test")
	private Topic topic;        

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        try {
            String queryMessage = request.getParameter("data");

	        final Destination destination = topic;
	        context.createProducer().send(destination, queryMessage);       
	        out.println("StatsJMSDataServlet: message sent - " + queryMessage);
	    } catch (Exception ex) {
	        throw new ServletException(ex);
	    } finally {
	        out.close();
	    }
	}
}
