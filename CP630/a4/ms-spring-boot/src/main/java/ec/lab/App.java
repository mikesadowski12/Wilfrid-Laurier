package ec.lab;

import org.springframework.beans.factory.annotation.Autowired;
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
class ControllerGrade {
    @Autowired
    private GradeI grade;
	
	@RequestMapping("/grade/{s}")
	String hello(@PathVariable String s) {
		int score = Integer.parseInt(s);
		
		return "{\"grade\": " + grade.getLetterGrade(score) + "}";
	}
}

@RestController
class ControllerRank {
    @Autowired
    private RankI rank;
	
	@RequestMapping("/rank/{s}")
	String hello(@PathVariable String s) {
		int score = Integer.parseInt(s);
		
		return "{\"rank\": " + rank.getRank(score) + "}";
	}
}

@RestController
class ControllerGradeRank {
    @Autowired
    private GradeI grade;
	
    @Autowired
    private RankI rank;
	
	@RequestMapping("/grade_rank/{s}")
	String hello(@PathVariable String s) {
		int score = Integer.parseInt(s);
		String res = "{\"rank\": " + rank.getRank(score) + ", \"grade\": " + grade.getLetterGrade(score) + "}";
		return res;
	}
}

