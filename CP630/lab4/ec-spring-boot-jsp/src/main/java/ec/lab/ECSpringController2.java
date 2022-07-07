package ec.lab;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ECSpringController2 {     
	@RequestMapping(value = "/post-form", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, ECSpring ecs) {
		ModelAndView model = new ModelAndView("postform");
		model.addObject("ECSpring",  ecs);
		return model;
	}
	@RequestMapping(value = "/ec-spring3", method = RequestMethod.POST)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("ecspring3");	
		String name = request.getParameter("name");
		model.addObject("name", name);
		request.setAttribute("localinfo", name + " in action with post method.");
		return model;
	}
}