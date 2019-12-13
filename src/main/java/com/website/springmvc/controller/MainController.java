package com.website.springmvc.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.entities.Order;
import com.website.springmvc.service.CategoryService;
import com.website.springmvc.service.ProductService;
import com.website.springmvc.service.UserService;

@Controller
@Transactional
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session) {
		ModelAndView model = new ModelAndView("index");
		Order order =  (Order) session.getAttribute("order");
		model.addObject("categories", categoryService.getAll());
		model.addObject("order", order);
		return model;
	}

	// @RequestMapping(value = "/", method = RequestMethod.POST)
	// public ModelAndView index(@RequestParam("userName") String userName,
	// @RequestParam("passWord") String passWord, Principal pricipal) {
	// ModelAndView model = new ModelAndView("index");
	// System.out.println(pricipal);
	// System.out.println(userName);
	// return model;
	// }

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView getStudents(ModelAndView model, Principal principal) {

		String userName = principal.getName();
		System.out.println("User Name: " + userName);

		model.setViewName("userInfoPage");
		model.addObject("user", userService.getUserByUserName(userName));

		return model;
	}

	// @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	// public ModelAndView welcomePage() {
	// ModelAndView model = new ModelAndView();
	//
	// model.setViewName("welcomePage");
	// model.addObject("title", "Welcome");
	// model.addObject("message", "This is welcome page!");
	//
	// return model;
	// }

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		return "adminPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {

		return "login";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			model.addAttribute("message",
					"Hi " + principal.getName() + "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "403Page";
	}


}
