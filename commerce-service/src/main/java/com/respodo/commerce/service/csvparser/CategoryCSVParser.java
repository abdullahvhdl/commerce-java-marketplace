package com.respodo.commerce.service.csvparser;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.respodo.commerce.dto.csv.CategoryCSVDTO;
import com.respodo.commerce.repository.dao.CatalogRepository;
import com.respodo.commerce.repository.dao.CategoryDescRepository;
import com.respodo.commerce.repository.dao.CategoryRepository;
import com.respodo.commerce.repository.dao.LanguageRepository;
import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.Category;
import com.respodo.commerce.repository.domain.CategoryDesc;

@Component
public class CategoryCSVParser implements CSVParser {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryDescRepository categoryDescRepository;
	@Autowired
	private CatalogRepository catalogRepository;
	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public void parseCSVFile(CSVReader reader) throws IOException {

	}

	@Override
	public void parseCSVFileForStore(Long storeId, CSVReader reader)
			throws IOException {

	}

	@Override
	public void parseCSVFileForCatalog(Long storeId, Long catalogId,
			CSVReader reader) throws IOException {
		String[] header2 = reader.readNext();
		CsvToBean<CategoryCSVDTO> csv = new CsvToBean<CategoryCSVDTO>();
		ColumnPositionMappingStrategy<CategoryCSVDTO> strategy = new ColumnPositionMappingStrategy<CategoryCSVDTO>();
		String[] columns = header2;
		strategy.setType(CategoryCSVDTO.class);
		strategy.setColumnMapping(columns);

		Catalog catalog = catalogRepository.findOne(catalogId);
		if (catalog == null) {
			return;
		}
		List<CategoryCSVDTO> list = csv.parse(strategy, reader);
		for (CategoryCSVDTO bean : list) {

			Category category = categoryRepository
					.findOneByUniqueId(bean.getUniqueId());
			if (category==null) {
				category = new Category();
				category.setUniqueId(bean.getUniqueId());
				category.setCatalog(catalog);
				CategoryDesc categoryDesc = new CategoryDesc();
				categoryDesc.setName(bean.getName());
				categoryDesc.setDescription(bean.getDescription());
				categoryDesc.setCategory(category);
				categoryDesc.setLanguage(languageRepository
						.findOneByLocale(bean.getLanguage()));
				categoryDesc.setActive(true);
				if (bean.getParentId() != null && !bean.getParentId().isEmpty()) {
					Category parentCategory = categoryRepository
							.findOneByUniqueId(bean.getParentId());
					if (parentCategory !=null) {
						category.setParentCategory(parentCategory);
						category.setCatLevel(parentCategory.getCatLevel() + 1);
					} else {
						category.setCatLevel(1);
					}

				} else {
					category.setCatLevel(1);
				}
				categoryRepository.save(category);
				categoryDescRepository.save(categoryDesc);
			} else {
				CategoryDesc categoryDesc = categoryDescRepository
						.findOneByCategoryIdAndLanguageLocale(category.getId(),
								bean.getLanguage());
				if (categoryDesc !=null) {
					if (bean.getName() != null && !bean.getName().isEmpty()) {
						categoryDesc.setName(bean.getName());
					}
					if (bean.getName() != null
							&& !bean.getName().isEmpty()) {
						categoryDesc.setDescription(bean.getDescription());
					}
					categoryDesc.setCategory(category);
					categoryDesc.setLanguage(languageRepository
							.findOneByLocale(bean.getLanguage()));
					categoryDesc.setActive(true);
				}
				else{
					categoryDesc=new CategoryDesc();
					categoryDesc.setName(bean.getName());
					categoryDesc.setDescription(bean.getDescription());
					categoryDesc.setCategory(category);
					categoryDesc.setLanguage(languageRepository
							.findOneByLocale(bean.getLanguage()));
					categoryDesc.setActive(true);
				}
				if (bean.getParentId() != null && !bean.getParentId().isEmpty()) {
					Category parentCategory = categoryRepository
							.findOneByUniqueId(bean.getParentId());
					if (parentCategory !=null) {
						category.setParentCategory(parentCategory);
						category.setCatLevel(parentCategory.getCatLevel() + 1);
					} else {
						category.setCatLevel(1);
					}

				} else {
					category.setCatLevel(1);
				}
				categoryRepository.save(category);
				categoryDescRepository.save(categoryDesc);
			}
		}

	}

}
