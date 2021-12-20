package stats.osgi.impl;

import stats.osgi.StatsOSGiI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;

public class StatsOSGiImpl implements StatsOSGiI {   	
   	private StatsSummary sso;
   
	// https://www.tutorialspoint.com/java/java_serialization.htm
	public StatsSummary loadModel() {
		final String DIR_NAME = "C:\'enterprise\'tmp\'model\'";
		final String FILE_NAME = "stats.bin";
				
		StatsSummary stats = null;
		
		try {
			FileInputStream fileIn = new FileInputStream(DIR_NAME + FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			stats = (StatsSummary) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("StatsSummary object not found");
			c.printStackTrace();
			return null;
		}
	      
		return stats;
	}
	
   	public StatsOSGiImpl() throws Exception {
   		System.out.println("Fetching stats summary object from stats.bin file ...");
   		this.sso = this.loadModel();
   		
   		if (this.sso == null) {
   			System.out.println("stats.bin file not found");
   			System.out.println("Loading preset data");
   			this.sso = new StatsSummary(10, 1, 10, 5.5, 2.872281323269);
   		}

   		System.out.println("Fetched Object - " + sso.toString());
   	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.sso.getCount();
	}

	@Override
	public double getMin() {
		// TODO Auto-generated method stub
		return  this.sso.getMin();
	}

	@Override
	public double getMax() {
		// TODO Auto-generated method stub
		return  this.sso.getMax();
	}

	@Override
	public double getMean() {
		// TODO Auto-generated method stub
		return  this.sso.getMax();
	}

	@Override
	public double getSTD() {
		// TODO Auto-generated method stub
		return  this.sso.getMean();
	}
	
	public String toJSON() {
		String count = "\"count\": " + sso.getCount() + ", ";
		String min = "\"min\": " + sso.getMin() + ", ";
		String max = "\"max\": " + sso.getMax() + ", ";
		String mean = "\"mean\": " + sso.getMean() + ", ";
		String std = "\"std\": " + sso.getSTD();
		
		return "{" + count + min + max + mean + std + "}";
	}
}
