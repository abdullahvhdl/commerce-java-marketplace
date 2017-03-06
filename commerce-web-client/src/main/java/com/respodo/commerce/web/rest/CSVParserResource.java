package com.respodo.commerce.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.respodo.commerce.service.ParserService;

@RestController
@RequestMapping("/api")
public class CSVParserResource {
	
	@Autowired
	private ParserService parserService;

	private final Logger log = LoggerFactory.getLogger(CSVParserResource.class);

	@RequestMapping(value = "/uploadCSV", method = RequestMethod.POST)
	public void uploadCSVFile(
			@RequestParam(value = "file", required = true) MultipartFile file) {
		parserService.parseCSVFile(file);

	}
	
	@RequestMapping(value = "/uploadCSV/{storeId}", method = RequestMethod.POST)
	public void uploadCSVFileForStore(@PathVariable Long storeId,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		parserService.parseCSVFileForStore(storeId,file);

	}
	
	@RequestMapping(value = "/uploadCSV/{storeId}/{catalogId}", method = RequestMethod.POST)
	public void uploadCSVFileForCatalog(@PathVariable Long storeId,@PathVariable Long catalogId,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		parserService.parseCSVFileForCatalog(storeId,catalogId,file);

	}

}
