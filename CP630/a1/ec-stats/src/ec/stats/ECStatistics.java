package ec.stats;

import java.util.ArrayList;

/** 
* @class ECStatistics
* @brief This class implements the Statistics interface and methods to calculate statistics on input data
* @file  ECStatistics.java
*/ 
public class ECStatistics implements  Statistics {
	ArrayList<Double> data;
	
	private int count; // number of elements
	private double min; // minimum element
	private double max; // maximum element
	private double mean; // mean
	private double std; // standard deviation
	
	private double totalSum; // total sum of elements
	
	/**
	  * @brief Compute and return the difference of two operands
	  */ 
	public ECStatistics() {
		this.data = new ArrayList<Double>();
		this.count = 0;
		this.min = Integer.MAX_VALUE;
		this.max = Integer.MIN_VALUE;
		this.mean = 0;
		this.std = 0;
	}
	
	/**
	  * @brief Compute the count/min/max/mean/standard deviation
	  */ 
	@Override
	public void stats() {
		this.count = this.data.size(); // compute count
		this.min = Integer.MAX_VALUE;
		this.max = Integer.MIN_VALUE;
		
		this.totalSum = 0;
        for (double d : this.data) {
        	this.totalSum += d;
        	
    		this.min = this.min <= d ? this.min : d; // compute minimum element
    		this.max = this.max >= d ? this.max : d; // compute maximum element
        }
        
        this.mean = this.totalSum/this.count; // compute mean
        
        final double totalStd = computeTotalSTD();
        this.std = Math.sqrt(totalStd/this.count); // compute standard deviation
	}
	
	/**
	  * @brief Add double to the data array and re-compute the count/min/max/mean/standard deviation
	  * 
	  * @param d 
	  */ 
	@Override
	public void addData(double d) {
		this.data.add(d);
		
		this.count++; // compute count
		this.min = this.min <= d ? this.min : d; // compute minimum element
		this.max = this.max >= d ? this.max : d; // compute maximum element

		this.totalSum += d;
        this.mean = this.totalSum/this.count; // compute mean

        final double totalStd = computeTotalSTD();
        this.std = Math.sqrt(totalStd/this.count); // compute standard deviation
	}
	
	/**
	  * @brief Getter for the count property
	  * 
	  * @return count
	  *  	  
	  */ 
	@Override
	public int getCount() {
		return this.count;
	}
	
	/**
	  * @brief Getter for the min property
	  * 
	  * @return min
	  *  	  
	  */ 
	@Override
	public double getMin() {
		return this.min;
	}
	
	/**
	  * @brief Getter for the max property
	  * 
	  * @return max
	  *  	  
	  */ 
	@Override
	public double getMax() {
		return this.max;
	}
	
	/**
	  * @brief Getter for the mean property
	  * 
	  * @return mean
	  *  	  
	  */ 
	@Override
	public double getMean() {
		return this.mean;
	}
	
	/**
	  * @brief Getter for the std property
	  * 
	  * @return std
	  *  	  
	  */ 
	@Override
	public double getSTD() {
		return this.std;
	}
	
	/**
	  * @brief Compute the total standard deviation for all values in the data array
	  * 
	  * @return totalStd
	  *  	  
	  */ 
	private double computeTotalSTD() {
        double totalStd = 0;
        
        for (double n: this.data) {
        	totalStd += Math.pow(n - mean, 2);
        }
        
        return totalStd;
	}
}
