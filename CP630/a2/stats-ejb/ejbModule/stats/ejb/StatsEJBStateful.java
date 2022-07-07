package stats.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class StatsEJBStateful
 */
@Stateful
@LocalBean
public class StatsEJBStateful implements StatsEJBStatefulRemote, StatsEJBStatefulLocal {
    @EJB
    private StatsEJBSingleton statsEJBSingleton;
	
    @EJB
    private StatsEJBStateless statsEJBStateless;
    
	@Override
	public void insertData(double d) {
		statsEJBSingleton.addData(d);
	}

	@Override
	public void createModel() {
		statsEJBSingleton.saveModel();
	}

	@Override
	public String getStats() {
		return statsEJBStateless.summaryString();
	}
}
