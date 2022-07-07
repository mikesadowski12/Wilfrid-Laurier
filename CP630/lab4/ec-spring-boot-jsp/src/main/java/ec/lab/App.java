package ec.lab;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

@RestController
class ControllerJson {
	@RequestMapping("/json/{name}")    // RESTful API  like http://localhost:8080/json/EC
	String hello(@PathVariable String name, HttpServletResponse response) {
		// the following two lines are for cross origin access
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, DELETE, HEAD, OPTIONS");
		return "{\"name\":\"" + name + "\"}";
	}
}

@RestController
class ControllerXML {
	@RequestMapping("/xml/{name}")
	String hello(@PathVariable String name) {
		return "<name>" + name + "</name>";
	}
}
