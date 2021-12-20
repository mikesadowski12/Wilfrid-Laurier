package ec.bean;

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
	
	// Function modified from ms-spring-boot example project linked in project description:
	// ms-spring-boot\src\main\java\ec\lab\Grade.java
	// https://stackoverflow.com/questions/64810976/is-there-a-way-to-use-binary-search-to-get-a-specific-outcome
	public String getLetterGrade(int numerical_grade) {
		// TODO Auto-generated method stub
		int start = 0;
		int end = count - 1;
		int mid = (start + end) / 2;
		
		while (start < end - 1) {
			mid = (start + end) / 2;
			
			if (this.gradeBoundary[mid] <= numerical_grade) {
				end = mid;
			} else {
				start = mid;
			}	
		}
		
		if (start == end - 1) mid = start;
		
		return gradeLetter[mid];
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
