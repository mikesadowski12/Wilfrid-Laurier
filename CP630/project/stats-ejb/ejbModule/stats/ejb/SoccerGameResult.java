package stats.ejb;

import java.sql.Timestamp;

public class SoccerGameResult implements java.io.Serializable {
	private String div;
	private Timestamp date;
	private String homeTeam;
	private String awayTeam;
	private int fthg;
	private int ftag;

	public SoccerGameResult(String div, Timestamp date, String homeTeam, String awayTeam, int fthg, int ftag) {
		this.div = div;
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.fthg = fthg;
		this.ftag = ftag;
	}
	
	public String getDiv() {
		return this.div;
	}
	
	public Timestamp getDate() {
		return this.date;
	}
	
	public String getHomeTeam() {
		return this.homeTeam;
	}
	
	public String getAwayTeam() {
		return this.awayTeam;
	}
	
	public int getFthg() {
		return this.fthg;
	}
	
	public int getFtag() {
		return this.ftag;
	}
}
