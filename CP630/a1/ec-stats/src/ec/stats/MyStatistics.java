package ec.stats;

/** 
* @mainpage ec-stats 
* 
* This project implements the Statistics interface methods to calculate the count/min/max/mean/standard deviation from a data set (arrayList). It store all values in corresponding properties in the class.
* When a data value is added (a double) to the set, the class automatically re calculates the new count/min/max/mean/standard deviation values using the new data. The function stats() can be run individually as well to compute the values.
* The MyStatistics class is the driver for the ECStatistics method. This main function adds the integers 1..10000 to the data set, computes thecount/min/max/mean/standard deviation values and prints them to the console.
*  
* @author Mike Sadowski  
*/ 

/** 
* @file MyStatistics.java 
* @brief This file class contains the main function for ECStatistics class/methods
* @author Mike Sadowski 
*/ 

/** 
* @class MyStatistics 
* 
* @brief MyStatistics class for the main function.
* 
* <ul> 
* 	<li>main function creates ECStatistics instance</li> 
* 	<li>Add data 1..10000 with method addData()</li> 
* 	<li>Print the result of count/min/max/mean/standard deviation</li> 
* </ul>
*/ 
public class MyStatistics {
	public static void main(String[] args) {
		final ECStatistics ecstats = new ECStatistics();
		
		for (int i = 1; i < 10001; i++) {
			ecstats.addData(i);
		}
		
		System.out.println("Count: " + ecstats.getCount());
		System.out.println("Min: " + ecstats.getMin());
		System.out.println("Max: " + ecstats.getMax());
		System.out.println("Mean: " + ecstats.getMean());
		System.out.println("Standard Deviation: " + ecstats.getSTD());
    }
}
