package com.respodo.commerce.service.csvparser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.respodo.commerce.dto.csv.AttributeCSVDTO;
import com.respodo.commerce.repository.dao.AttributeDescRepository;
import com.respodo.commerce.repository.dao.AttributeRepository;
import com.respodo.commerce.repository.dao.AttributeTypeRepository;
import com.respodo.commerce.repository.dao.LanguageRepository;
import com.respodo.commerce.repository.domain.Attribute;
import com.respodo.commerce.repository.domain.AttributeDesc;

@Component
public class AttributeCSVParser implements CSVParser {

	@Autowired
	private AttributeRepository attributeRepository;
	@Autowired
	private AttributeDescRepository attributeDescRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private AttributeTypeRepository attributeTypeRepository;

	@Override
	public void parseCSVFile(CSVReader reader) throws IOException {
		String[] header2 = reader.readNext();
		CsvToBean<AttributeCSVDTO> csv = new CsvToBean<AttributeCSVDTO>();
		ColumnPositionMappingStrategy<AttributeCSVDTO> strategy = new ColumnPositionMappingStrategy<AttributeCSVDTO>();
		String[] columns = header2;
		strategy.setType(AttributeCSVDTO.class);
		strategy.setColumnMapping(columns);
		List<AttributeCSVDTO> list = csv.parse(strategy, reader);
		for (AttributeCSVDTO bean : list) {

			Optional<Attribute> optionalAttribute = attributeRepository
					.findOneByUniqueId(bean.getUniqueId());
			if (!optionalAttribute.isPresent()) {
				Attribute attribute = new Attribute();
				attribute.setUniqueId(bean.getUniqueId());
				AttributeDesc description = new AttributeDesc();
				description.setName(bean.getName());
				description.setAttribute(attribute);
				description.setLanguage(languageRepository.findOneByLocale(bean
						.getLanguage()));

				attribute.setAttributeType(attributeTypeRepository
						.findOneByType(bean.getType()));
				attributeRepository.save(attribute);
				attributeDescRepository.save(description);
			} else {
				Attribute attribute = optionalAttribute.get();
				AttributeDesc description = attributeDescRepository
						.findOneByAttributeIdAndLanguageLocale(
								attribute.getId(), bean.getLanguage());
				if (bean.getUniqueId() != null && !bean.getUniqueId().isEmpty()) {
					attribute.setUniqueId(bean.getUniqueId());
				}
				if (description !=null) {
					if (bean.getName() != null && !bean.getName().isEmpty()) {
						description.setName(bean.getName());
					}
					description.setAttribute(attribute);
				} else {

					description = new AttributeDesc();
					description.setName(bean.getName());
					description.setAttribute(attribute);
					description.setLanguage(languageRepository
							.findOneByLocale(bean.getLanguage()));
				}
				if (bean.getType() != null && !bean.getType().isEmpty()) {
					attribute.setAttributeType(attributeTypeRepository
							.findOneByType(bean.getType()));
				}
				attributeRepository.save(attribute);
				attributeDescRepository.save(description);
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
