package com.website.springmvc.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.entities.User;
import com.website.springmvc.entities.UserRole;
import com.website.springmvc.service.UserRoleService;
import com.website.springmvc.service.UserService;

@Controller
@RequestMapping(value = "/register")
@Transactional
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	// @RequestMapping(value = "/", method = RequestMethod.GET)
	// public String loadRegister() {
	// return "register";
	// }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@Transactional
	@ResponseBody
	public String checkUser(@RequestParam("email") String email, @RequestParam("userName") String username,
			HttpServletResponse response, HttpServletRequest request) {

		String kq = "";
		if (email.isEmpty() == false) {
			if (userService.validateEmail(email) != null) {
				kq = "You can use this email";
			} else {
				kq = "You cannot use this email";
			}
		}
		if (username.isEmpty() == false) {
			if (userService.validateUser(username)) {
				kq = "You can use this username";
			} else {
				kq = "You cannot use this username";
			}
		}
		
		
		return kq;
	}
	
	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user) {
		user.setActive(true);
		UserRole userRole = new UserRole();
		userRole.setRoleName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<UserRole>();
		userRoles.add(userRole);
		user.setRoles(userRoles);
		user.setUserRole("user");
		if (user.getId() == null) {
			userService.add(user);
		}
		userRole.setUser(user);
		userRoleService.add(userRole);
		return "redirect:/";
	}

}
