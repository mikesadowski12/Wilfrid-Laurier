package ec.lab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ECSpringController {

	@RequestMapping("/ec-spring")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="EC Spring") String name, Model model) 
    {
        model.addAttribute("name", name);
        return "ecspring";
    }
	
    @GetMapping("/ec-spring2")
    public ModelAndView Dispaly(@RequestParam(name="name", required=false, defaultValue="EC Spring") String name) 
    {  
        ModelAndView model = new ModelAndView("ecspring2");   
        model.addObject("name", name);
        return model;
    }
}
