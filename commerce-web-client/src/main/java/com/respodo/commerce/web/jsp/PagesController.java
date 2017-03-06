package com.respodo.commerce.web.jsp;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PagesController {
	private final Logger log = LoggerFactory.getLogger(PagesController.class);

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(){
		log.debug("accessing home");
		return "index";
	}
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(){
		
		return "page/home";
	}
	
	@RequestMapping(value="/stores",method=RequestMethod.GET)
	public String stores(){
		
		return "page/stores";
	}
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(){
		
		return "page/search";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		
		return "page/login";
	}
	@RequestMapping(value="/account",method=RequestMethod.GET)
	public String account(){
		
		return "page/account";
	}
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(){
		
		return "page/register";
	}
	@RequestMapping(value="/activate",method=RequestMethod.GET)
	public String actovate(){
		
		return "page/activate";
	}
	@RequestMapping(value="/{storeName}-{storeId}",method=RequestMethod.GET)
	public String storeHome(){
		
		return "page/store/storeHome";
	}
	@RequestMapping("/{storeName}-{storeId}/category/{categoryName}-{categoryId}")
	public String category(Model model, @PathVariable Long storeId,
			@PathVariable Long categoryId, Locale locale) {
		
		return "page/store/category";
	}
	@RequestMapping("/{storeName}-{storeId}/product/{productName}-{productId}")
	public String product(Model model, @PathVariable Long storeId,
			@PathVariable Long productId, Locale locale) {
		
		return "page/store/product";
	}
}
