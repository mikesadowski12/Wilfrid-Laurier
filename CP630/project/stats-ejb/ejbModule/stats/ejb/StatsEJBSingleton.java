package stats.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Import the File class
// Import the IOException class to handle errors
import java.io.*;

/**
 * Session Bean implementation class StatsEJBSingleton
 */
@Singleton
@LocalBean
public class StatsEJBSingleton implements MyStatsSingletonRemote, MyStatsSingletonLocal {
	List<SoccerGameResult> data;

    public StatsEJBSingleton() {
    	this.data = new ArrayList<SoccerGameResult>();
    }

	@Override
	public void addData(SoccerGameResult d) {
		this.data.add(d);
	}
	
	public List<SoccerGameResult> getData() {
		return this.data;
	}
//	public ArrayList<Double> getData() {
//		return this.data;
//	}
//
//	@Override
//	public int getCount() {
//		return this.data.size();
//	}

//	@Override
//	public void stats() {
//		this.min = Integer.MAX_VALUE;
//		this.max = Integer.MIN_VALUE;
//		
//		this.totalSum = 0;
//        for (double d : this.data) {
//        	this.totalSum += d;
//        	
//    		this.min = this.min <= d ? this.min : d; // compute minimum element
//    		this.max = this.max >= d ? this.max : d; // compute maximum element
//        }
//        
//        this.mean = this.totalSum/this.count; // compute mean
//        
//        final double totalStd = computeTotalSTD();
//        this.std = Math.sqrt(totalStd/this.count); // compute standard deviation
//	}

	// https://www.tutorialspoint.com/java/java_serialization.htm
	@Override
	public void saveModel() {
	    final String DIR_NAME = "C:\'enterprise\'tmp\'model\'";
	    final String FILE_NAME = "stats.bin";
	    File directory = new File(DIR_NAME);
	    File file = new File(FILE_NAME);
	    SoccerStatsSummary stats = new SoccerStatsSummary(this.data);

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
	
	@Override
	public void load() {
		String fileName = "data/2018-2019.arff";
		ArrayList<List<String>> fileContents = new ArrayList();
		
    	// https://www.journaldev.com/709/java-read-file-line-by-line
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while (line != null) {
//				System.out.println(line);
				
				List<String> attributes = Arrays.asList(line.split(","));
				fileContents.add(attributes);

				
				// read next line
				line = reader.readLine();
			}
			reader.close();
			
			for (int i = 9; i < fileContents.size(); i++) {
				List<String> lineContents = fileContents.get(i);

				SoccerGameResult result = new SoccerGameResult(lineContents.get(0), null, lineContents.get(1), lineContents.get(2), Integer.parseInt(lineContents.get(3)), Integer.parseInt(lineContents.get(4)));
				this.data.add(result);				
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private double computeTotalSTD() {
//        double totalStd = 0;
//        
//        for (double n: this.data) {
//        	totalStd += Math.pow(n - mean, 2);
//        }
//        
//        return totalStd;
//	}
}
