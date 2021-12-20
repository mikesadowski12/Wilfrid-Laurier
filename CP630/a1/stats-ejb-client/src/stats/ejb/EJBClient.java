package stats.ejb;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import stats.ejb.MyStatsSingletonRemote;
import stats.ejb.StatsEJBStatelessRemote;
import stats.ejb.StatsEJBStatefulRemote;

public class EJBClient {
    public static void main(String[] args) throws NamingException {
    	System.out.println("Running stats-ejb project client");
    	MyStatsSingletonRemote statsSingleton = (MyStatsSingletonRemote) InitialContext.doLookup("stats-ejb/StatsEJBSingleton!stats.ejb.MyStatsSingletonRemote");
    	StatsEJBStatelessRemote statsStateless = (StatsEJBStatelessRemote) InitialContext.doLookup("stats-ejb/StatsEJBStateless!stats.ejb.StatsEJBStatelessRemote");
    	StatsEJBStatefulRemote statsStateful = (StatsEJBStatefulRemote) InitialContext.doLookup("stats-ejb/StatsEJBStateful!stats.ejb.StatsEJBStatefulRemote");

    	for (int i = 1; i < 11; i ++) {
    		statsSingleton.addData(i);
    	}
    	statsSingleton.stats();
    	statsSingleton.saveModel();

    	System.out.println("Count is: " + statsStateless.getCount());
    	System.out.println("Mean is: " + statsStateless.getMean());
    	    	
    	for (int i = 11; i < 101; i ++) {
    		statsStateful.insertData(i);
    	}

    	statsSingleton.stats();
    	statsStateful.createModel();
    	System.out.println("Stats Summary: " + statsStateful.getStats());
    }
}
