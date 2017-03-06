package com.respodo.commerce.service.csvparser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.respodo.commerce.dto.csv.AttributeValueCSVDTO;
import com.respodo.commerce.repository.dao.AttributeRepository;
import com.respodo.commerce.repository.dao.AttributeValueDescRepository;
import com.respodo.commerce.repository.dao.AttributeValueRepository;
import com.respodo.commerce.repository.dao.LanguageRepository;
import com.respodo.commerce.repository.domain.Attribute;
import com.respodo.commerce.repository.domain.AttributeDesc;
import com.respodo.commerce.repository.domain.AttributeValue;
import com.respodo.commerce.repository.domain.AttributeValueDesc;

@Component
public class AttributeValueCSVParser implements CSVParser {

	@Autowired
	private AttributeRepository attributeRepository;
	@Autowired
	private AttributeValueDescRepository attributeValueDescRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private AttributeValueRepository attributeValueRepository;
	
	@Override
	public void parseCSVFile(CSVReader reader) throws IOException {
		String[] header2 = reader.readNext();
		CsvToBean<AttributeValueCSVDTO> csv = new CsvToBean<AttributeValueCSVDTO>();
		ColumnPositionMappingStrategy<AttributeValueCSVDTO> strategy = new ColumnPositionMappingStrategy<AttributeValueCSVDTO>();
		String[] columns = header2;
		strategy.setType(AttributeValueCSVDTO.class);
		strategy.setColumnMapping(columns);
		List<AttributeValueCSVDTO> list = csv.parse(strategy, reader);
		for (AttributeValueCSVDTO bean : list) {

			Optional<AttributeValue> optionalAttributeValue = attributeValueRepository
					.findOneByUniqueId(bean.getUniqueId());
			if (!optionalAttributeValue.isPresent()) {
				AttributeValue attributeValue = new AttributeValue();
				attributeValue.setUniqueId(bean.getUniqueId());
				AttributeValueDesc description = new AttributeValueDesc();
				description.setName(bean.getName());
				description.setAttributeValue(attributeValue);
				description.setLanguage(languageRepository.findOneByLocale(bean
						.getLanguage()));

				attributeValue.setAttribute(attributeRepository
						.findOneByUniqueId(bean.getAttribute()).get());
				attributeValueRepository.save(attributeValue);
				attributeValueDescRepository.save(description);
			} else {
				AttributeValue attributeValue = optionalAttributeValue.get();
				Optional<AttributeValueDesc> optionalAttributeDesc = attributeValueDescRepository
						.findOneByAttributeValueIdAndLanguageLocale(
								attributeValue.getId(), bean.getLanguage());
				if (bean.getUniqueId() != null && !bean.getUniqueId().isEmpty()) {
					attributeValue.setUniqueId(bean.getUniqueId());
				}
				AttributeValueDesc description;
				if (optionalAttributeDesc.isPresent()) {
					description = optionalAttributeDesc.get();
					if (bean.getName() != null && !bean.getName().isEmpty()) {
						description.setName(bean.getName());
					}
					description.setAttributeValue(attributeValue);
				} else {

					description = new AttributeValueDesc();
					description.setName(bean.getName());
					description.setAttributeValue(attributeValue);
					description.setLanguage(languageRepository
							.findOneByLocale(bean.getLanguage()));
				}
				if (bean.getAttribute() != null && !bean.getAttribute().isEmpty()) {
					attributeValue.setAttribute(attributeRepository
							.findOneByUniqueId(bean.getAttribute()).get());
				}
				attributeValueRepository.save(attributeValue);
				attributeValueDescRepository.save(description);
			}
		}
	}

	@Override
	public void parseCSVFileForStore(Long storeId, CSVReader reader)
			throws IOException {

	}

	@Override
	public void parseCSVFileForCatalog(Long storeId, Long catalogId,
			CSVReader reader) throws IOException {

	}

}
