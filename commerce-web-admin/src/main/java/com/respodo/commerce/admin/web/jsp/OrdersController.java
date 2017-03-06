package com.respodo.commerce.admin.web.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@RequestMapping("/")
	public String home() {
		return "orders/index";
	}

	@RequestMapping("/unassigned")
	public String unassigned() {
		return "orders/unassigned";

	}

	@RequestMapping("/assigned")
	public String assigned() {
		return "orders/assigned";
	}

	@RequestMapping("/task/{taskId}")
	public String viewTask(Model model, @PathVariable Long taskId) {
		model.addAttribute("taskId", taskId);
		return "orders/taskView";
	}
}
