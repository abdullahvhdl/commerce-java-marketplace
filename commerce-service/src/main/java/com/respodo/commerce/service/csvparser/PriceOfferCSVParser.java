package com.respodo.commerce.service.csvparser;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.respodo.commerce.common.AuthoritiesConstants;
import com.respodo.commerce.common.CatalogTypeConstants;
import com.respodo.commerce.dto.csv.CatalogCSVDTO;
import com.respodo.commerce.dto.csv.PriceOfferCSVDTO;
import com.respodo.commerce.repository.dao.CatalogRepository;
import com.respodo.commerce.repository.dao.CurrencyPriceRepository;
import com.respodo.commerce.repository.dao.PriceOfferRepository;
import com.respodo.commerce.repository.dao.PriceOfferTypeRepository;
import com.respodo.commerce.repository.dao.ProductRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.domain.AddrProfile;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.Authority;
import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.CurrencyPrice;
import com.respodo.commerce.repository.domain.PriceOffer;
import com.respodo.commerce.repository.domain.PriceOfferType;
import com.respodo.commerce.repository.domain.Product;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.repository.domain.User;

@Component
public class PriceOfferCSVParser implements CSVParser {

	@Autowired
	private PriceOfferRepository priceOfferRepository;
	@Autowired
	private CurrencyPriceRepository currencyPriceRepository;
	@Autowired
	private PriceOfferTypeRepository priceOfferTypeRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public void parseCSVFile(CSVReader reader) throws IOException {
		
	}

	@Override
	public void parseCSVFileForStore(Long storeId, CSVReader reader) throws IOException {
		String[] header2 = reader.readNext();
		CsvToBean<PriceOfferCSVDTO> csv = new CsvToBean<PriceOfferCSVDTO>();
		ColumnPositionMappingStrategy<PriceOfferCSVDTO> strategy = new ColumnPositionMappingStrategy<PriceOfferCSVDTO>();
		String[] columns = header2;
		strategy.setType(PriceOfferCSVDTO.class);
		strategy.setColumnMapping(columns);
		
		Store store=storeRepository.findOne(storeId);
		if(store ==null){
			return;
		}
		List<PriceOfferCSVDTO> list = csv.parse(strategy, reader);
		for (PriceOfferCSVDTO bean : list) {
			Optional<Product> optionalProduct=productRepository.findOneByUniqueId(bean.getProductId());
			if(!optionalProduct.isPresent()){
				continue;
			}
			PriceOfferType type=priceOfferTypeRepository.findOneByType(bean.getType());
			PriceOffer priceOffer=priceOfferRepository.findOneByProductAndStoreAndPriceOfferType(optionalProduct.get(),store,type);
			if(priceOffer==null){
				priceOffer.setPriceOfferType(type);
				priceOffer.setProduct(optionalProduct.get());
				priceOffer.setStore(store);
				CurrencyPrice price =new CurrencyPrice();
				price.setCurrency(bean.getCurrency());
				price.setPrice(new BigDecimal(bean.getPrice()));
				price.setPriceOffer(priceOffer);
				priceOfferRepository.save(priceOffer);
				currencyPriceRepository.save(price);
				
			}
			else{
				CurrencyPrice price=currencyPriceRepository.findOneByPriceOfferAndCurrency(priceOffer,bean.getCurrency());
				if(price!=null){
					price.setPrice(new BigDecimal(bean.getPrice()));
				}
				else{
					price =new CurrencyPrice();
					price.setCurrency(bean.getCurrency());
					price.setPrice(new BigDecimal(bean.getPrice()));
					price.setPriceOffer(priceOffer);
					
				}
				priceOfferRepository.save(priceOffer);
				currencyPriceRepository.save(price);
			}
			
		}
		
	}

	@Override
	public void parseCSVFileForCatalog(Long storeId, Long catalogId,
			CSVReader csvReader) {
		// TODO Auto-generated method stub
		
	}

}
