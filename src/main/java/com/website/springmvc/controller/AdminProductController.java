package com.website.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.website.springmvc.entities.Category;
import com.website.springmvc.entities.Product;
import com.website.springmvc.entities.User;
import com.website.springmvc.service.CategoryService;
import com.website.springmvc.service.ProductService;
import com.website.springmvc.service.UserService;

@Controller
@RequestMapping(value = "/adminproduct")
public class AdminProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;

	// @RequestMapping(value="/",method = RequestMethod.GET)
	public String onload() {
		return "adminproduct";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getProducts(@RequestParam(defaultValue = "0") Integer page, Integer offset,
			Principal principal) {
		ModelAndView model = new ModelAndView();

		String userName = principal.getName();
		// System.out.println("User Name: " + userName);
		User user = userService.getUserByUserName(userName);

		offset = (page - 1) * 5;
		List<Product> list = productService.getAll(offset);
		Long count = productService.count();
		Long pages = count / 5;
		model.addObject("pages", pages);
		model.addObject("count", count);
		model.addObject("offset", offset);
		model.addObject("user", user);
		model.setViewName("adminproduct");
		model.addObject("products", list);
		model.addObject("categories", categoryService.getAll());
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

	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam("id") Long id, @RequestParam("mode") String mode,
			@ModelAttribute("product") Product product, ModelAndView model, HttpServletRequest request)
			throws UnsupportedEncodingException {
		model.setViewName("adminProductDetail");
		model.addObject("product", productService.getById(id));
		model.addObject("mode", mode);
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		productService.deleteById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product, HttpServletRequest request,
			@RequestParam("fileUpload") CommonsMultipartFile[] fileUpload) throws Exception {
		if (product.getId() == null) {
			if (fileUpload != null && fileUpload.length > 0) {
				for (CommonsMultipartFile aFile : fileUpload) {

					System.out.println("Saving file: " + aFile.getOriginalFilename());

					product.setImages(aFile.getBytes());
					byte[] encodeBase64 = Base64.getEncoder().encode(aFile.getBytes());
					String base64Encoded = new String(encodeBase64, "UTF-8");
					product.setBase64ImageFile(base64Encoded);
					productService.add(product);
				}
			}
		} else {
			if (fileUpload != null && fileUpload.length > 0) {
				for (CommonsMultipartFile aFile : fileUpload) {

					System.out.println("Saving file: " + aFile.getOriginalFilename());

					product.setImages(aFile.getBytes());
					byte[] encodeBase64 = Base64.getEncoder().encode(aFile.getBytes());
					String base64Encoded = new String(encodeBase64, "UTF-8");
					product.setBase64ImageFile(base64Encoded);
				}
			}
			productService.update(product);
		}

		return "redirect:/adminproduct/";
	}

	@RequestMapping(value="/search", method = RequestMethod.POST)
	public ModelAndView SearchProduct(@RequestParam(value="txtSearch") Long txtSearch) {
		ModelAndView model = new ModelAndView();
		model.setViewName("adminproduct");
		List<Product> list = productService.getProductByUserId(txtSearch);
		model.addObject("products", list);
		return model;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView addProduct(Principal principal) {

		User user = userService.getUserByUserName(principal.getName());

		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		model.setViewName("adminProductDetail");
		model.addObject("product", new Product());
		model.addObject("categories", categoryService.getAll());

		return model;
	}

}
