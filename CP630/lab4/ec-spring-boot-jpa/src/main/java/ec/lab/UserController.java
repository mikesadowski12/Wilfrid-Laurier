package ec.lab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class UserController {
	
	
    @Autowired
    private UserRepository repository;


	@RequestMapping("/list-user")
	public String listUser(Model model) {		
		List<User> userList = repository.findAll();
		model.addAttribute("users", userList);			
		return "user";
	}
}
