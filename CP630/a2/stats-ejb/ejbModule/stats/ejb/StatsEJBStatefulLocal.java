package stats.ejb;

import javax.ejb.Local;

@Local
public interface StatsEJBStatefulLocal {
	public void insertData(double d); // inserts a data into the data set on of the StatsEJBSingleton object.
	public void createModel(); // saves the stats summary model StatsEJBSingleton.
	public String getStats(); // gets the stats summary string by invoking the toString() of  StatsEJBStateless object.
}
