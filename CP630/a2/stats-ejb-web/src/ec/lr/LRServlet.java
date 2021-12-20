package ec.lr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stats.ejb.LoginServlet;
import weka.core.Instances;

@WebServlet("/LRServlet")
public class LRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.toString());

	@EJB
	private LRStateless lrstateless;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

        try {
        	String data = req.getParameter("data");
        	String predicationDataSet = lrstateless.prediction();
//        	String predictionResults = predicationDataSet.toString();
    	    System.out.println(predicationDataSet);
			out.println("Summary: " + predicationDataSet + "<br>");
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            out.close();
        }
	}
}
