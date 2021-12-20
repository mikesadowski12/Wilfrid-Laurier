package ec.lab;

import org.springframework.stereotype.Service;

@Service
public class Grade implements GradeI {
	private String name;
	private int[] gradeBoundary = { 100, 90, 85, 80, 77, 73, 70, 0 };
	private String[] gradeLetter = { "A+", "A", "A-", "B+", "B", "B-", "F" };
	private int count;
	
	Grade () {
		this.setCount();
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}
	
	public void setGradeBoundary(int[] gradeBoundary) {
		this.gradeBoundary = gradeBoundary;
		this.setCount();
	}
	
	public int[] getGradeBoundary() {
		return gradeBoundary;
	}
	
	public void setGradeLetter(String[] gradeLetter) {
		this.gradeLetter = gradeLetter;
	}
	
	public String[] getGradeLetter() {
		return gradeLetter;
	}
	
	public void setCount() {
		this.count = this.gradeBoundary.length;
	}
	
	public int getCount() {
		return count;
	}
	
	// TODO FIX THISSSSSSSSSSSSSSSSSSSS
	public String getLetterGrade(int numerical_grade) {
		// TODO Auto-generated method stub
		int l = 0;
		int r = count - 1;
		int m = (l + r) / 2;
		while (l < r - 1) {
			m = (l + r) / 2;
			if (this.gradeBoundary[m] <= numerical_grade)
				r = m;
			else
				l = m;
		}
		if (l == r - 1) m = l;
		return gradeLetter[m];
	}

	public void printLetterGrades(int start, int end) {
		for (int i = start; i <= end; i = i + 1) {
			System.out.print(i + ":" + this.getLetterGrade(i) + " ");

			if (i % 5 == 0) {
				System.out.println(); 
			}
		}
	}
}
