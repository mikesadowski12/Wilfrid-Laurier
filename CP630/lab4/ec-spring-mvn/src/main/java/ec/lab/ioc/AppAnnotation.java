package ec.lab.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ec.lab.bean.ECSpring;

public class AppAnnotation {
	public static void main(String[] args) {
		
        AbstractApplicationContext context2 = new AnnotationConfigApplicationContext(ECSpringConfig.class);
        ECSpring obj = (ECSpring) context2.getBean("ec-spring-bean");
        obj.printHello();
        context2.close();
	}
}
