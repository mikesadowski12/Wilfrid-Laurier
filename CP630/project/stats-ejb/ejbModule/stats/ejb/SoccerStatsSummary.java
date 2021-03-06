package stats.ejb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoccerStatsSummary implements java.io.Serializable {
	private static final long serialVersionUID = 6529685098267757690L;
	private List<SoccerGameResult> results;
	 
	public SoccerStatsSummary() {
		this.results = new ArrayList<SoccerGameResult>();
	}
	
	public SoccerStatsSummary(List<SoccerGameResult> results) {
		this.results = results;
	}
	
	public List<SoccerGameResult> getResults() {
		return this.results;
	}
	
	public void addResult(SoccerGameResult result) {
		this.results.add(result);
	}
	
	public String getSeasonResult(String team1, String team2) {		
		boolean flag = false;
		
		Map<String, Integer> map = new HashMap<>();
		map.put(team1, 0);
		map.put(team2, 0);
		
		for (SoccerGameResult result : results) {
			String resultHomeTeam = result.getHomeTeam();
			String resultAwayTeam = result.getAwayTeam();

			if (resultHomeTeam.equals(team1) && resultAwayTeam.equals(team2) || (resultHomeTeam.equals(team2) && resultAwayTeam.equals(team1))) {
				int countTeam1 = map.containsKey(team1) ? map.get(team1) : 0;
				map.put(team1, countTeam1 + result.getFthg());
				
				int countTeam2 = map.containsKey(team2) ? map.get(team2) : 0;
				map.put(team2, countTeam2 + result.getFtag());
				
				if (!flag) {
					flag = true;
				}
			}
		}
		
		if (!flag) {
			return "Error: no result found for " + team1 + " vs " + team2;
		} 
		
		int countTeam1 = map.containsKey(team1) ? map.get(team1) : 0;
		int countTeam2 = map.containsKey(team2) ? map.get(team2) : 0;
		
		return "{ \'prediction\': " 
			+ "{ \'" + team1 + "\': " + countTeam1/2
			+ ", \'" + team2 + "\': " + countTeam2/2
			+ " }}" ;
	}
}
