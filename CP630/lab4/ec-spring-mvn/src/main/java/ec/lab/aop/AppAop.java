package ec.lab.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ec.lab.bean.Player;


public class AppAop {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("PlayerAop.xml");
		
		//Player Player = (Player) context.getBean("Player");
		
		Player Player = (Player) context.getBean("PlayerProxy");
		//Player.setScore(10);
		
		System.out.println("*************************");
		System.out.println(Player.getFirstName());
		
		System.out.println("*************************");
		System.out.println(Player.getLastName());
		
		System.out.println("*************************");
		System.out.println(Player.getScore());
		
		System.out.println("*************************");
		Player.printName();
		
		try {
			System.out.println("*************************");
			Player.checkScore();
			
			System.out.println("*************************");
			Player.printThrowException();
			
		} catch (Exception e) {
			//e.printStackTrace();		
		}

	}
}