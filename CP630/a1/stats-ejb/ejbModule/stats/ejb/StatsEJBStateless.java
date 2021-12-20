package stats.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.io.*;

/**
 * Session Bean implementation class StatsEJBStateless
 */
@Stateless
@LocalBean
public class StatsEJBStateless implements StatsEJBStatelessRemote, StatsEJBStatelessLocal {
	@Override
	public int getCount() {
		StatsSummary stats = loadModel();
		return stats.getCount();
	}

	@Override
	public double getMin() {
		StatsSummary stats = loadModel();
		return stats.getMin();
	}

	@Override
	public double getMax() {
		StatsSummary stats = loadModel();
		return stats.getMax();
	}

	@Override
	public double getMean() {
		StatsSummary stats = loadModel();
		return stats.getMean();
	}

	@Override
	public double getSTD() {
		StatsSummary stats = loadModel();
		return stats.getSTD();
	}

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

	@Override
	public String summaryString() {
		StatsSummary stats = loadModel();

		final String count = "Count: " + stats.getCount();
		final String min = "Min: " + stats.getMin();
		final String max = "Max: " + stats.getMax();
		final String mean = "Mean: " + stats.getMean();
		final String std = "Standard Deviation: " + stats.getSTD();

		return count + ", " + min + ", " + max + ", " + mean + ", " + std;
	}
}
