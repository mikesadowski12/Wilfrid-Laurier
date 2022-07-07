package stats.ejb;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import stats.ejb.StatsEJBStateless;

@WebService()
public class StatsWS {
    @EJB
    StatsEJBStateless ejbsl;
    
    @WebMethod()
    public int getCount() {
        return ejbsl.getCount();
    }
    
    @WebMethod()
    public double getMin() {
        return ejbsl.getMin();
    }
    
    @WebMethod()
    public double getMax() {
        return ejbsl.getMax();
    }
    
    @WebMethod()
    public double getMean() {
        return ejbsl.getMean();
    }
    	
    @WebMethod()
    public double getSTD() {
        return ejbsl.getSTD();
    }
    
    @WebMethod()
    public String summaryString() {
        return ejbsl.summaryString();
    }
}
