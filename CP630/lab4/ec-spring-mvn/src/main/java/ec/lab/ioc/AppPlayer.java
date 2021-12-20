package ec.lab.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ec.lab.bean.ECSpring;
import ec.lab.bean.Player;

public class AppPlayer {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("PlayerBeans.xml");
		Player obj = (Player) context.getBean("player1");
		System.out.println(obj.getFirstName());
		System.out.println(obj.getLastName());
		System.out.println(obj.getScore());
		
		Player obj1= (Player) context.getBean("player2");
		System.out.println(obj1.getFirstName());
		System.out.println(obj1.getLastName());
		System.out.println(obj1.getScore());
		
		ECSpring obj2 = (ECSpring) context.getBean("ec-spring");
		obj2.printHello();
	}
}
