package stats.ejb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import ec.jpa.model.Model;

@WebServlet("/ModelEJBJMSServlet")
public class ModelEJBJMSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.toString());

    @EJB
    private StatsEJBJMSStateless statsEJBJMSStateless;

    @EJB
    private ModelEJBStateless modelEJBStateless;
    
    @EJB
    private StatsEJBStateful statsEJBStateful;
    
    @EJB
    private StatsEJBSingleton statsEJBSingleton;

    @EJB
    private StatsEJBStateless statsEJBStateless;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
        try {
        	double data = Double.parseDouble(req.getParameter("data"));
        	statsEJBSingleton.addData(data);
            statsEJBSingleton.stats();
            statsEJBStateful.createModel();
            
            StatsSummary sso = new StatsSummary(statsEJBStateless.getCount(), statsEJBStateless.getMin(), statsEJBStateless.getMax(), statsEJBStateless.getMean(), statsEJBStateless.getSTD());
            

        	Model model = new Model();
        	model.setClassname(sso.getClass().getSimpleName());      
        	model.setName("stats");
            model.setDate(new Timestamp(System.currentTimeMillis()));

            // https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream outStream = null;
            try {
            	outStream = new ObjectOutputStream(bos);   
            	outStream.writeObject(sso);
            	outStream.flush();
	            byte[] ssoBytes = bos.toByteArray();
	            model.setObject(ssoBytes);
            } finally {
            	try {
            		bos.close();
            	} catch (IOException ex) {
            		// ignore close exception
            	}
            }
            // https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
            
            modelEJBStateless.saveModel(model);
            
            String summary = statsEJBStateful.getStats();
            out.println("Summary: " + summary + " was saved in MySql <br>");
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            out.close();
        }
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        try {
        	Model model = modelEJBStateless.getModelByName("stats");
        	ByteArrayInputStream bis = new ByteArrayInputStream(model.getObject());
        	ObjectInput in = null;
        	
        	try {
        	  in = new ObjectInputStream(bis);
        	  StatsSummary o = (StatsSummary) in.readObject(); 
        	  
//        	  System.out.println(model.getName());
//        	  System.out.println(model.getClassname());
//        	  System.out.println(model.getObject());
//        	  System.out.println(model.getDate());
        	  
              String queryMessage = request.getParameter("value");

              switch(queryMessage) {
  	            case "count":
  	            	int count = o.getCount();
  	            	out.println("Count: " + count + " <br>");
  	            	break;
  	            case "min":
  	            	double min = o.getMin();
  	            	out.println("Min: " + min + " <br>");
  	            	break;
  	            case "max":
  	            	double max = o.getMax();
  	            	out.println("Max: " + max + " <br>");
  	                break;
  	            case "mean":
  	            	double mean = o.getMean();
  	            	out.println("Mean: " + mean + " <br>");
  	                break;
  	            case "std":
  	            	double std = o.getSTD();
  	            	out.println("Standard Deviation: " + std + " <br>");
  	                break;
  	            default:
  	            	out.println("Unknown query message <br>");
              }
        	} finally {
        	  try {
        	    if (in != null) {
        	      in.close();
        	    }
        	  } catch (IOException ex) {
        	    // ignore close exception
        	  }
        	}
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            out.close();
        }
    }
}