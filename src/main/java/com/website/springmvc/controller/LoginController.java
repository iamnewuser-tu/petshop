//package com.website.springmvc.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.website.springmvc.DAO.PaginationResult;
//import com.website.springmvc.entities.Order;
//import com.website.springmvc.service.UserService;
//
//@Controller
//public class LoginController {
//
//	@Autowired
//	private UserService userService;
//
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String onload() {
//		return "homepage";
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login() {
//		return "login";
//	}
//
//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
//	public ModelAndView welcomeUser(@PathVariable("name") String name, @RequestParam("mode") String mode) {
//		ModelAndView model = new ModelAndView();
//		model.setViewName("homepage");
//		model.addObject("name", name);
//		model.addObject("mode", mode);
//		return model;
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@RequestParam String userName, @RequestParam String passWord, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, @RequestParam(value = "error", required = false) String error) {
////		if (userService.login(userName, passWord) == true) {
////			return "redirect:/product/";
////		}
//		return "login";
//
//	}
//
//	@RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
//	public String accountInfo(Model model) {
//
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(userDetails.getPassword());
//		System.out.println(userDetails.getUsername());
//		System.out.println(userDetails.isEnabled());
//
//		model.addAttribute("userDetails", userDetails);
//		return "accountInfo";
//	}
//
//}
