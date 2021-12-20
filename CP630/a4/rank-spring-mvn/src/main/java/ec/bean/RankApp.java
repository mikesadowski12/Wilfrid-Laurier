package ec.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RankApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("RankBeans.xml");
		Rank rank = (Rank) context.getBean("rank-bean");
		
		System.out.println();
		rank.printScoreRankGrade();
		System.out.println();
		rank.printPrediction(76);
	}
}