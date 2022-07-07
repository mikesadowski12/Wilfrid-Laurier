package ec.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GradeApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("GradeBeans.xml");
		GradeI grade1 = (GradeI) context.getBean("grade-bean");
		Grade grade2 = (Grade) context.getBean("grade-bean");	
		int[] gradeBoundary = { 100, 90, 85, 80, 77, 73, 70, 67, 63, 60, 57, 53, 50, 0 };
		String[] gradeLetter = { "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F" };
		
		grade2.setGradeBoundary(gradeBoundary);
		grade2.setGradeLetter(gradeLetter);
		
		System.out.println();
		grade1.printLetterGrades(66,  100);
		System.out.println();
		grade2.printLetterGrades(46,  100);
	}
}
