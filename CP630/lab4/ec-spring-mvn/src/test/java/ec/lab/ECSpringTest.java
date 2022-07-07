package ec.lab;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ec.lab.bean.ECSpring;

public class ECSpringTest {
	static ApplicationContext context;
	static ECSpring obj ;
	String name = "EC";

	@BeforeClass
	public static void initSpring()  {
		context = new ClassPathXmlApplicationContext("SpringBeans.xml");
		obj = (ECSpring) context.getBean("ec-spring-bean");		
	}

	@Before
	public void beforeEachTest()  {
		System.out.println("This is executed before each Test");
	}
	@After
	public void afterEachTest() {
		System.out.println("This is exceuted after each Test");
	}

	@Test
	public void test() {
		obj.setName(name);
		assertEquals(name, obj.getName());
	}
}

