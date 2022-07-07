package stats.osgi.impl;

public class StatsSummary implements java.io.Serializable {
	private int count; // number of elements
	private double min; // minimum element
	private double max; // maximum element
	private double mean; // mean
	private double std; // standard deviation

	public StatsSummary(int count, double min, double max, double mean, double std) {
		this.count = count;
		this.min = min;
		this.max = max;
		this.mean = mean;
		this.std = std;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public double getMin() {
		return this.min;
	}
	
	public double getMax() {
		return this.max;
	}

	public double getMean() {
		return this.mean;
	}

	public double getSTD() {
		return this.std;
	}
	
	public String toString() {
		return "Count: " + this.count + ", " + "Min: " + this.min + ", " + "Max: " + this.max + ", " + "Mean: " + this.mean + ", " + "STD: " + this.std;
	}
}
