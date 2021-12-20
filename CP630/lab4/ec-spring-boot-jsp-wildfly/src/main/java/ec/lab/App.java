package ec.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="ec.lab")
@SpringBootApplication
public class App extends SpringBootServletInitializer{
	
	@Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

	public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

@RestController
class ControllerJson {
	@RequestMapping("/json/{name}")
	String hello(@PathVariable String name) {
		return "{\"name\": " + name + "}";
	}
}

@RestController
class ControllerXML {
	@RequestMapping("/xml/{name}")
	String hello(@PathVariable String name) {
		return "<name>" + name + "</name>";
	}
}
