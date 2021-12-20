package ec.lab;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@RequestMapping("/list-user")
	public String listContact(Model model) {
		UserData userdata = new UserData();
		List<User> userList = userdata.getUserList();
		model.addAttribute("users", userList);			
		return "user";
	}
}
