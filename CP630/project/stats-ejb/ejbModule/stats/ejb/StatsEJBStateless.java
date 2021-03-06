package stats.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.io.*;
import java.util.List;

/**
 * Session Bean implementation class StatsEJBStateless
 */
@Stateless
@LocalBean
public class StatsEJBStateless implements StatsEJBStatelessRemote, StatsEJBStatelessLocal {
	@Override
	public String getSeasonResult(String team1, String team2) {
		SoccerStatsSummary stats = loadModel();         
		return stats.getSeasonResult(team1, team2);

	}

	// https://www.tutorialspoint.com/java/java_serialization.htm
	public SoccerStatsSummary loadModel() {
		final String DIR_NAME = "C:\'enterprise\'tmp\'model\'";
		final String FILE_NAME = "stats.bin";
				
		SoccerStatsSummary stats = null;
		
		try {
			FileInputStream fileIn = new FileInputStream(DIR_NAME + FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			stats = (SoccerStatsSummary) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("SoccerStatsSummary object not found");
			c.printStackTrace();
			return null;
		}
	      
		return stats;
	}

	@Override
	public String summaryString() {
		SoccerStatsSummary stats = loadModel();

//		final String count = "Count: " + stats.getCount();
//		final String min = "Min: " + stats.getMin();
//		final String max = "Max: " + stats.getMax();
//		final String mean = "Mean: " + stats.getMean();
//		final String std = "Standard Deviation: " + stats.getSTD();

//		return count + ", " + min + ", " + max + ", " + mean + ", " + std;
		return "implement summary string for matches.....";
	}
}
