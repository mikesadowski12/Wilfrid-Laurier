package ec.lab;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.stereotype.Service;

@Service
public class Rank implements RankI {
	private String name;
	private Integer[] scores = {71,71,85,70,85,99,70,79,89,83,96,85,82,84,96,77,89,81,71,90,89,71,99,99,84,74,90,75,73,86};
	private int count;
	private Grade grade; 
	
	Rank() {
		this.setCount();
		Arrays.sort(scores, Collections.reverseOrder());
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}
	
	public void setScores(Integer[] scores) {
		this.scores = scores;
		Arrays.sort(scores, Collections.reverseOrder());
		this.setCount();
	}
	
	public Integer[] getScores() {
		return scores;
	}
	
	public void setCount() {
		this.count = this.scores.length;
	}
	
	public int getCount() {
		return count;
	}
	
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getGrade(int s) {
		// TODO Auto-generated method stub
		return grade.getLetterGrade(s);
	}

	// TODO FIX THISSSSSSSSSSSSSSSSSSSS
	public int getRank(int s) {
		// TODO Auto-generated method stub
		int l = 0;
		int r = count - 1;
		int m = (l + r) / 2;
		while (l < r - 1) {
			if (this.scores[l] == s) {
				m = l;
				break;
			} 
			else if (this.scores[r] == s) {
				m = r;
				break;
			}
			
			
			m = (l + r) / 2;
			if (this.scores[m] < s)
				r = m;
			else
				l = m;
		}

		if (l == r - 1) {
			if (this.scores[r] < s && this.scores[l] > s ) {
				m = r;
			} else 
			m = l;
		}

		while (m > 0 && scores[m - 1] == scores[m]) {
			m = m - 1;
			//System.out.println(m + scores[m]);
		}
		return m+1;
	}
	
	void printScoreRankGrade() {
		for (int i = 0; i < this.getCount(); i++) {
			Integer score = this.getScores()[i];
			Integer rank = this.getRank(this.getScores()[i]);
			String grade = this.getGrade(this.getScores()[i]);
			
			System.out.println("score: " + score + ", rank: " + rank + ", grade: " + grade);
		}
	}
	
	void printPrediction(int s) {
		Integer rank = this.getRank(s);
		String grade =  this.getGrade(s);
		
		System.out.println("Prediction for score: " + s);
		System.out.println("rank: " + rank + ", grade: " + grade);
	}
}
