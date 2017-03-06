package com.respodo.commerce.service.csvparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.respodo.commerce.constants.CommerceTables;

@Component
public class CSVParserFactory {

	@Autowired
	private StoreCSVParser storeCsvParser;
	
	@Autowired
	private UserCSVParser userCSVParser;
	
	@Autowired
	private CatalogCSVParser catalogCSVParser;
	
	@Autowired
	private CategoryCSVParser categoryCSVParser;
	
	@Autowired
	private AttributeCSVParser attributeCSVParser;
	
	@Autowired
	private AttributeValueCSVParser attributeValueCSVParser;
	
	@Autowired
	private ProductCSVParser productCSVParser;
	
	@Autowired
	private PriceOfferCSVParser priceOfferCSVParser;
	
	public CSVParser getCsvParser(String tableName) {
		
		if (tableName.equalsIgnoreCase(CommerceTables.STORE_TABLE)) {
			return storeCsvParser;
		}
		else if(tableName.equalsIgnoreCase(CommerceTables.USER_TABLE)){
			return userCSVParser;
		}
		else if(tableName.equalsIgnoreCase(CommerceTables.CATALOG_TABLE)){
			return catalogCSVParser;
		}
		else if(tableName.equalsIgnoreCase(CommerceTables.CATEGORY_TABLE)){
			return categoryCSVParser;
		}
		else if(tableName.equalsIgnoreCase(CommerceTables.ATTRIBUTE_TABLE)){
			return attributeCSVParser;
		}
		else if(tableName.equalsIgnoreCase(CommerceTables.ATTRIBUTE_VALUE_TABLE)){
			return attributeValueCSVParser;
		}
		else if(tableName.equalsIgnoreCase(CommerceTables.PRODUCT_TABLE)){
			return productCSVParser;
		}
		else if(tableName.equalsIgnoreCase(CommerceTables.PRICE_OFFER_TABLE)){
			return priceOfferCSVParser;
		}
		return null;

	}
}
