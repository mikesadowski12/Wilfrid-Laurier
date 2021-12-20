package ec.lab.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ec.lab.bean.ECSpring;

@Configuration
@PropertySource("classpath:ECSpring.properties")
public class ECSpringConfig {
	@Autowired
	private Environment env;

	@Bean(name = "ec-spring-bean")
	@Description("This is an sample of creating ECSpring Bean by annotation configuration")
	public ECSpring ECSpring() {
		ECSpring hw = new ECSpring();
		hw.setName(env.getProperty("name"));
		return hw;
	}
}

