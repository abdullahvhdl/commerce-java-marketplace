package com.respodo.commerce.admin.web.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/")
	public String index(){
		return "general/index";
	}
	
	@RequestMapping("/attributes")
	public String attributes(){
		return "general/attributes";
	}
	
	@RequestMapping("/editAttribute/{attributeId}")
	public String editAttribute(Model model,@PathVariable Long attributeId){
		model.addAttribute("attributeId", attributeId);
		return "general/editAttribute";
	}
	
	
	
	
}
