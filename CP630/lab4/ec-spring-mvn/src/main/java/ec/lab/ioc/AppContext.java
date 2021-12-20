package ec.lab.ioc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ec.lab.bean.ECSpring;

public class AppContext {
	
	private static final Logger logger = LogManager.getLogger(AppContext.class.getName()); 
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
		ECSpring obj = (ECSpring) context.getBean("ec-spring-bean");
		obj.printHello();
		
		logger.info("Applied Spring applicationContext");
	}
}
