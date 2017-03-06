package com.respodo.commerce.service.csvparser;


import java.io.IOException;

import com.opencsv.CSVReader;


public interface CSVParser {

	public void parseCSVFile(CSVReader csvReader) throws IOException;

	public void parseCSVFileForStore(Long storeId, CSVReader csvReader) throws IOException;

	public void parseCSVFileForCatalog(Long storeId, Long catalogId,
			CSVReader csvReader) throws IOException;

}
