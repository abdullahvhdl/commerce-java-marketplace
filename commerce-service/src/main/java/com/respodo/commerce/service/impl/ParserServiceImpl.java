package com.respodo.commerce.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.respodo.commerce.service.ParserService;
import com.respodo.commerce.service.csvparser.CSVParser;
import com.respodo.commerce.service.csvparser.CSVParserFactory;

@Service
@Transactional
public class ParserServiceImpl implements ParserService {
	private final Logger log = LoggerFactory.getLogger(ParserServiceImpl.class);
	
	@Autowired
	private CSVParserFactory csvParserFactory;

	@Override
	public void parseCSVFile(MultipartFile file) {
		CSVReader csvReader = null;
		try {
			InputStreamReader is=new InputStreamReader(
					file.getInputStream());
			
			csvReader = new CSVReader(is);
			String [] header1=csvReader.readNext();
			
			if(header1.length>0){
				CSVParser csvParser =csvParserFactory.getCsvParser(header1[0]);
				if(csvParser!=null){
					csvParser.parseCSVFile(csvReader);
				}
			}
			csvReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}

	}

	@Override
	public void parseCSVFileForStore(Long storeId, MultipartFile file) {
		CSVReader csvReader = null;
		try {
			InputStreamReader is=new InputStreamReader(
					file.getInputStream());
			
			csvReader = new CSVReader(is);
			String [] header1=csvReader.readNext();
			
			if(header1.length>0){
				CSVParser csvParser =csvParserFactory.getCsvParser(header1[0]);
				if(csvParser!=null){
					csvParser.parseCSVFileForStore(storeId,csvReader);
				}
			}
			csvReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}

	@Override
	public void parseCSVFileForCatalog(Long storeId, Long catalogId,
			MultipartFile file) {
		CSVReader csvReader = null;
		try {
			InputStreamReader is=new InputStreamReader(
					file.getInputStream());
			
			csvReader = new CSVReader(is);
			String [] header1=csvReader.readNext();
			
			if(header1.length>0){
				CSVParser csvParser =csvParserFactory.getCsvParser(header1[0]);
				if(csvParser!=null){
					csvParser.parseCSVFileForCatalog(storeId,catalogId,csvReader);
				}
			}
			csvReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}

	@Override
	public <T> List<T> parseCSVFile(MultipartFile file, Class<T> type) {
		CSVReader csvReader = null;
		List<T> list=new ArrayList<T>();
		try {
			InputStreamReader is=new InputStreamReader(
					file.getInputStream());
			csvReader = new CSVReader(is);
			String [] header1=csvReader.readNext();
			String[] header2 = csvReader.readNext();
			CsvToBean<T> csv = new CsvToBean<T>();
			ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<T>();
			strategy.setType(type);
			String[] columns = header2;
			strategy.setColumnMapping(columns);

			list = csv.parse(strategy, csvReader);
			csvReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return list;
	}

}
