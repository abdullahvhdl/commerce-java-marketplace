package com.respodo.commerce.admin.web.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatalogController {

	@RequestMapping("/catalog")
	public String home(){
		
		return "catalog/index";
	}
}
