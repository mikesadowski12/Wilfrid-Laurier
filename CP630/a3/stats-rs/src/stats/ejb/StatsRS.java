package stats.ejb;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/")
@RequestScoped
public class StatsRS {
	@EJB 
	StatsEJBStateless sbsl;
	
    @GET
    @Path("/getsummaryjson")
    @Produces({ "application/json" })
    public String getSummaryJSon() {
        int count = sbsl.getCount();
        double min = sbsl.getMin();
        double max = sbsl.getMax();
        double mean = sbsl.getMean();
        double std = sbsl.getSTD();
        String summaryString = sbsl.summaryString();
                
        return "{\"result\":{" 
        	+ "\"count\": " + count + "," 
	        + "\"min\": " + min + "," 
	        + "\"max\": " + max + "," 
	        + "\"mean\": " + mean + "," 
	        + "\"std\": " + std + "," 
	        + "\"summaryString\": " + "\"" + summaryString + "\"" + "}}";
    }
}
