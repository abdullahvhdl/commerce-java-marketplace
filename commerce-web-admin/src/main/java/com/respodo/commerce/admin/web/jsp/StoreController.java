package com.respodo.commerce.admin.web.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.respodo.commerce.manager.CSVUoloadManager;
import com.respodo.commerce.manager.StoreManager;

@Controller
public class StoreController {

	@Autowired
	private CSVUoloadManager csvUoloadManager;
	
	

	@RequestMapping("/stores")
	public String manageStores(Model model) {

		return "stores/index";
	}

	@RequestMapping(value = "/stores/csv", method = RequestMethod.GET)
	public String uploadStoresCSV(Model model) {

		return "stores/csvUpload";
	}

	@RequestMapping(value = "/stores/csv", method = RequestMethod.POST)
	public String SubmitUploadStoresCSV(Model model,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		csvUoloadManager.uploadStoresCSV(file);

		return "stores/index";
	}

	@RequestMapping(value = "/newStore", method = RequestMethod.GET)
	public String newStore(Model model) {

		return "stores/newStore";
	}

	@RequestMapping(value = "/editStore/{storeId}", method = RequestMethod.GET)
	public String editStore(Model model, @PathVariable Long storeId) {
		model.addAttribute("storeId", storeId);
		return "stores/editStore";
	}

}
