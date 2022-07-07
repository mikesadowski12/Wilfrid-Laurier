package stats.ejb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import ec.lr.LRStateless;

@WebServlet("/ModelEJBJMSServlet")
public class ModelEJBJMSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.toString());
	private boolean loadedData = false;

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
    
	@EJB
	private LRStateless lrstateless;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
        try {        	
        	String dateRaw = req.getParameter("date");
        	String homeTeamName = req.getParameter("homeTeamName");
        	String awayTeamName = req.getParameter("awayTeamName");
        	int ftgh = Integer.parseInt(req.getParameter("fthg"));
        	int ftah = Integer.parseInt(req.getParameter("ftag"));
        	
//        	System.out.println(date);
//        	System.out.println(homeTeamName);
//        	System.out.println(awayTeamName);
//        	System.out.println(ftgh);
//        	System.out.println(ftah);
        	
        	// https://stackoverflow.com/questions/974973/java-timestamp-how-can-i-create-a-timestamp-with-the-date-23-09-2007
        	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	Date date = dateFormat.parse(dateRaw);
        	long time = date.getTime();
        	
        	SoccerGameResult result = new SoccerGameResult("E0", new Timestamp(time), homeTeamName, awayTeamName, ftgh, ftah);

        	statsEJBStateful.insertData(result);
        	statsEJBStateful.createModel();            
            SoccerStatsSummary sso = statsEJBStateless.loadModel();
//            List<SoccerGameResult> myList = statsEJBSingleton.getData();
  
//            System.out.println(myList);
//            System.out.println(myList.size());
            
        	Model model = new Model();
        	model.setClassname(sso.getClass().getSimpleName());      
        	model.setName("stats");
            model.setDate(new Timestamp(System.currentTimeMillis()));
//
//            // https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
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
        
    	if (!this.loadedData) {
    		this.loadedData = true;
    		statsEJBSingleton.load();
    		statsEJBStateful.createModel();
    		
            SoccerStatsSummary sso = statsEJBStateless.loadModel();
//          List<SoccerGameResult> myList = statsEJBSingleton.getData();

//          System.out.println(myList);
//          System.out.println(myList.size());
          
      	Model model = new Model();
      	model.setClassname(sso.getClass().getSimpleName());      
      	model.setName("stats");
          model.setDate(new Timestamp(System.currentTimeMillis()));
//
//          // https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
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
    		
    	}
    	  
        try {
        	Model model = modelEJBStateless.getModelByName("stats");
        	ByteArrayInputStream bis = new ByteArrayInputStream(model.getObject());
        	ObjectInput in = null;
        	
        	try {
        	  in = new ObjectInputStream(bis);
        	  SoccerStatsSummary o = (SoccerStatsSummary) in.readObject(); 
        	  
//        	  System.out.println(model.getName());
//        	  System.out.println(model.getClassname());
//        	  System.out.println(model.getObject());
//        	  System.out.println(model.getDate());
        	  
          	  String homeTeamName = request.getParameter("homeTeamNameGet");
          	  String awayTeamName = request.getParameter("awayTeamNameGet");
          	  
              String result = statsEJBStateless.getSeasonResult(homeTeamName, awayTeamName);
              String resultLr = lrstateless.prediction();
  	          out.println("Prediction Response:" + result + "<br>");
  	          out.println("<br>");
  	          out.println("Linear Regression results: " + resultLr + "<br>");
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