package com.respodo.commerce.service.csvparser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.respodo.commerce.common.CatalogTypeConstants;
import com.respodo.commerce.dto.csv.CatalogCSVDTO;
import com.respodo.commerce.dto.csv.CategoryCSVDTO;
import com.respodo.commerce.dto.csv.ProductCSVDTO;
import com.respodo.commerce.repository.dao.CatalogRepository;
import com.respodo.commerce.repository.dao.CategoryDescRepository;
import com.respodo.commerce.repository.dao.CategoryRepository;
import com.respodo.commerce.repository.dao.ImageRepository;
import com.respodo.commerce.repository.dao.LanguageRepository;
import com.respodo.commerce.repository.dao.ProductDescRepository;
import com.respodo.commerce.repository.dao.ProductRepository;
import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.Category;
import com.respodo.commerce.repository.domain.CategoryDesc;
import com.respodo.commerce.repository.domain.Image;
import com.respodo.commerce.repository.domain.Product;
import com.respodo.commerce.repository.domain.ProductDesc;
import com.respodo.commerce.repository.domain.Store;

@Component
public class ProductCSVParser implements CSVParser {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductDescRepository productDescRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public void parseCSVFile(CSVReader reader) throws IOException {
		String[] header2 = reader.readNext();
		CsvToBean<ProductCSVDTO> csv = new CsvToBean<ProductCSVDTO>();
		ColumnPositionMappingStrategy<ProductCSVDTO> strategy = new ColumnPositionMappingStrategy<ProductCSVDTO>();
		String[] columns = header2;
		strategy.setType(ProductCSVDTO.class);
		strategy.setColumnMapping(columns);

		List<ProductCSVDTO> list = csv.parse(strategy, reader);
		for (ProductCSVDTO bean : list) {
			Optional<Product> optionalProduct = productRepository
					.findOneByUniqueId(bean.getUniqueId());
			if (!optionalProduct.isPresent()) {
				Product product = new Product();
				product.setUniqueId(bean.getUniqueId());
				productRepository.findOneByUniqueId(bean.getParentId())
						.ifPresent(
								parentProduct -> product
										.setProductParent(parentProduct));
				product.setProdType(bean.getType());
				Image image = new Image();
				image.setImage1(bean.getImage1());
				image.setImage2(bean.getImage2());
				image.setImage3(bean.getImage3());
				image.setThumbnail(bean.getThumbnail());
				product.setImage(image);

				ProductDesc description = new ProductDesc();
				description.setName(bean.getName());
				description.setDescription(bean.getDescription());
				description.setActive(bean.getActive());
				description.setLanguage(languageRepository.findOneByLocale(bean
						.getLanguage()));
				description.setProduct(product);
				imageRepository.save(image);
				productRepository.save(product);
				productDescRepository.save(description);

			} else {
				Product product = optionalProduct.get();
				if (bean.getType() != null && !bean.getType().isEmpty()) {
					product.setProdType(bean.getType());
				}
				Image image = product.getImage();
				if (bean.getImage1() != null && !bean.getImage1().isEmpty()) {
					image.setImage1(bean.getImage1());
				}
				if (bean.getImage2() != null && !bean.getImage2().isEmpty()) {
					image.setImage2(bean.getImage2());
				}
				if (bean.getImage3() != null && !bean.getImage3().isEmpty()) {
					image.setImage3(bean.getImage3());
				}
				if (bean.getThumbnail() != null
						&& !bean.getThumbnail().isEmpty()) {
					image.setThumbnail(bean.getThumbnail());
				}
				ProductDesc description = productDescRepository
						.findOneByProductIdAndLanguageLocale(product.getId(),
								bean.getLanguage());
				if (description == null) {
					description = new ProductDesc();
				}
				if (bean.getName() != null && !bean.getName().isEmpty()) {
					description.setName(bean.getName());
				}
				if (bean.getDescription() != null
						&& !bean.getDescription().isEmpty()) {
					description.setDescription(bean.getDescription());
				}
				if (bean.getActive() != null) {
					description.setActive(bean.getActive());
				}
				description.setLanguage(languageRepository.findOneByLocale(bean
						.getLanguage()));
				description.setProduct(product);
				product.setImage(image);
				imageRepository.save(image);
				productRepository.save(product);
				productDescRepository.save(description);
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
