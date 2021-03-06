package stats.ejb;

import javax.ejb.Local;

@Local
public interface StatsEJBStatelessLocal {
	public int getCount(); // returning the number of elements
	public double getMin(); // returning the minimum element
	public double getMax(); // returning maximum element
	public double getMean(); // returning the mean
	public double getSTD(); // returning standard deviation
	public String summaryString(); // returns a String of the simple stats summary.
}
