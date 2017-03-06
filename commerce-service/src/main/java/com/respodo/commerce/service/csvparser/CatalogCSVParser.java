package com.respodo.commerce.service.csvparser;

import java.io.IOException;
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
import com.respodo.commerce.repository.dao.CatalogRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.domain.AddrProfile;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.Authority;
import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.repository.domain.User;

@Component
public class CatalogCSVParser implements CSVParser {

	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private CatalogRepository catalogRepository;

	@Override
	public void parseCSVFile(CSVReader reader) throws IOException {
		
	}

	@Override
	public void parseCSVFileForStore(Long storeId, CSVReader reader) throws IOException {
		String[] header2 = reader.readNext();
		CsvToBean<CatalogCSVDTO> csv = new CsvToBean<CatalogCSVDTO>();
		ColumnPositionMappingStrategy<CatalogCSVDTO> strategy = new ColumnPositionMappingStrategy<CatalogCSVDTO>();
		String[] columns = header2;
		strategy.setType(CatalogCSVDTO.class);
		strategy.setColumnMapping(columns);
		
		Store store = storeRepository.findOne(storeId);
		if(store==null){
			return;
		}
		Set<Catalog> catalogs = store.getCatalogs();
		List<CatalogCSVDTO> list = csv.parse(strategy, reader);
		for (CatalogCSVDTO bean : list) {

			Optional<Catalog> optionalCatalog=catalogRepository.findOneByUniqueId(bean.getUniqueId());
			if (!optionalCatalog.isPresent()) {
				Catalog catalog=new Catalog();
				catalog.setName(bean.getName());
				catalog.setUniqueId(bean.getUniqueId());
				catalog.setType(CatalogTypeConstants.SALE_CATALOG);
				catalogRepository.save(catalog);
				catalogs.add(catalog);
				if(bean.getActive()){
					store.setActiveCatalog(catalog);
					storeRepository.save(store);
				}
			} else {
				Catalog catalog = optionalCatalog.get();
				if(catalog.getType().equals(CatalogTypeConstants.MASTER_CATALOG)){
					continue;
				}
				if(bean.getName()!=null && !bean.getName().isEmpty()){
					catalog.setName(bean.getName());
				}
				catalogRepository.save(catalog);
				Catalog storeCatalog=catalogRepository.findOneByIdAndStores_Id(catalog.getId(),storeId);
				if(storeCatalog==null){
					catalogs.add(catalog);
				}
				if(bean.getActive()){
					store.setActiveCatalog(catalog);
					storeRepository.save(store);
				}
			}
		}
		store.setCatalogs(catalogs);
		storeRepository.save(store);
		
	}

	@Override
	public void parseCSVFileForCatalog(Long storeId, Long catalogId,
			CSVReader csvReader) {
		// TODO Auto-generated method stub
		
	}

}
