package ec.lab.ioc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ec.lab.bean.ECSpring;

public class AppBeanFactory {
	private static final Logger logger = LogManager.getLogger(AppBeanFactory.class.getName()); 
	public static void main(String[] args) {
		BeanFactory beanfactory = new ClassPathXmlApplicationContext("SpringBeans.xml");
		ECSpring obj = (ECSpring) beanfactory.getBean("ec-spring-bean");
		obj.printHello();
	}
}
