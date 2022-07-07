package ec.lab.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ec.lab.bean.ECSpring;

public class AppContextConfig {
	public static void main(String[] args) {        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ECSpringConfig.class);
        ECSpring obj = (ECSpring) context.getBean("ec-spring-bean");
        obj.printHello();
        context.close();
	}
}
