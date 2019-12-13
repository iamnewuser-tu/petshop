package com.website.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.entities.Category;
import com.website.springmvc.entities.Order;
import com.website.springmvc.entities.Product;
import com.website.springmvc.service.CategoryService;
import com.website.springmvc.service.OrderService;
import com.website.springmvc.service.UserService;

@Controller
@RequestMapping(value="adminorder")
public class AdminOrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getProducts(@RequestParam(defaultValue = "0") Integer page, Integer offset,
			Principal principal) {
		ModelAndView model = new ModelAndView();

//		String userName = principal.getName();
		// System.out.println("User Name: " + userName);
//		User user = userService.getUserByUserName(userName);

		offset = (page - 1) * 5;
		List<Order> list = orderService.getAll(offset);
		Long count = orderService.count();
		Long pages = count / 5;
		model.addObject("pages", pages);
		model.addObject("count", count);
		model.addObject("offset", offset);

//		model.addObject("user", user);
		model.setViewName("adminorder");
		model.addObject("orders", list);
//		model.addObject("categories", categoryService.getAll());
		return model;
	}

	// @RequestMapping(value = "/viewProduct", method = RequestMethod.GET)
	// public ModelAndView showProductByCategory(HttpSession session,
	// @RequestParam(value = "name", defaultValue = "") String name,
	// @RequestParam(defaultValue = "0") Integer page, Integer offset) {
	// ModelAndView model = new ModelAndView("category");
	// Category category = categoryService.getByName(name);
	// offset = (page - 1) * 6;
	// List<Product> products = (List<Product>)
	// productService.getByCategory(category, offset);
	// Iterator<Product> productIter = products.iterator();
	// while (productIter.hasNext()) {
	// Product product = productIter.next();
	// Long id = product.getUser().getId();
	// User user = userService.getById(id);
	// product.setUser(user);
	// }
	//
	// Long count = productService.count();
	//
	// Long pages = (long) products.size() / 6;
	//
	// model.addObject("pages", pages);
	// model.addObject("count", count);
	// model.addObject("offset", offset);
	//
	// model.addObject("products", products);
	// model.addObject("category", category);
	// model.addObject("categories", categoryService.getAll());
	// return model;
	// }

	@RequestMapping(value = "/getOrder", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam("id") Long id, @RequestParam("mode") String mode,
			@ModelAttribute("order") Order order , ModelAndView model, HttpServletRequest request)
			throws UnsupportedEncodingException {
		model.setViewName("adminOrderDetail");
		model.addObject("order", orderService.getById(id));
		model.addObject("mode", mode);
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		orderService.deleteById(id);
	}

	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public ModelAndView SearchProduct(@RequestParam(value="txtSearch") Long txtSearch) {
		ModelAndView model = new ModelAndView();
		model.setViewName("adminorder");
		List<Order> list = orderService.getOrderByUserId(txtSearch);
		model.addObject("orders", list);
		return model;
	}

	

}
