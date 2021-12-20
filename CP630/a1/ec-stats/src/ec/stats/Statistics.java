package ec.stats;

/** 
* @class Statistics
* @brief This is the interface for Statistics class implemented by ECStatistics class
* @file  Statistics.java
*/ 
public interface Statistics {
	public void stats(); // computing the descriptive statistics
	public void addData(double d); // adding a data element 
	public int getCount(); // returning the number of elements
	public double getMin(); // returning the minimum element
	public double getMax(); // returning maximum element
	public double getMean(); // returning the mean
	public double getSTD(); // returning standard deviation
}
