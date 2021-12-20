package ec.bean;

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

	// Function modified from ms-spring-boot example project linked in project description:
	// ms-spring-boot\src\main\java\ec\lab\Rank.java
	// https://stackoverflow.com/questions/64810976/is-there-a-way-to-use-binary-search-to-get-a-specific-outcome
	public int getRank(int s) {
		// TODO Auto-generated method stub
		int start = 0;
		int end = count - 1;
		int mid = (start + end) / 2;
		
		while (start < end - 1) {
			if (this.scores[start] == s) {
				mid = start;
				break;
			} else if (this.scores[end] == s) {
				mid = end;
				break;
			}

			mid = (start + end) / 2;
			
			if (this.scores[mid] < s) {
				end = mid;
			} else {
				start = mid;
			}
		}

		if (start == end - 1) {
			if (this.scores[end] < s && this.scores[start] > s ) {
				mid = end;
			} else {
				mid = start;
			}
		}

		while (mid > 0 && scores[mid - 1] == scores[mid]) {
			mid = mid - 1;
		}
		
		return mid + 1;
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
