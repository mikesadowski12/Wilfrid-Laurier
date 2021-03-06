package stats.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.*;

/**
 * Session Bean implementation class StatsEJBSingleton
 */
@Singleton
@LocalBean
public class StatsEJBSingleton implements MyStatsSingletonRemote, MyStatsSingletonLocal {
	ArrayList<Double> data;
	
	private int count; // number of elements
	private double min; // minimum element
	private double max; // maximum element
	private double mean; // mean
	private double std; // standard deviation
	
	private double totalSum; // total sum of elements

    public StatsEJBSingleton() {
    	this.data = new ArrayList<Double>();
    }

	@Override
	public void addData(double d) {
		this.data.add(d);
		this.count++;
	}

	@Override
	public int getCount() {
		return this.data.size();
	}

	@Override
	public void stats() {
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

	// https://www.tutorialspoint.com/java/java_serialization.htm
	@Override
	public void saveModel() {
	    final String DIR_NAME = "C:\'enterprise\'tmp\'model\'";
	    final String FILE_NAME = "stats.bin";
	    File directory = new File(DIR_NAME);
	    File file = new File(FILE_NAME);
	    StatsSummary stats = new StatsSummary(this.count, this.min, this.max, this.mean, this.std);

	    if (!directory.exists()) {
    		directory.mkdir();
	    }

	    try {
	    	file.createNewFile();
	    	FileOutputStream fileOut = new FileOutputStream(DIR_NAME + FILE_NAME);
	    	ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    	out.writeObject(stats);
	    	out.close();
	    	fileOut.close();
	    	System.out.printf("Serialized data is saved in " + DIR_NAME + FILE_NAME);
	    } catch (IOException i) {
	    	i.printStackTrace();
	    }
	}

	private double computeTotalSTD() {
        double totalStd = 0;
        
        for (double n: this.data) {
        	totalStd += Math.pow(n - mean, 2);
        }
        
        return totalStd;
	}
}
