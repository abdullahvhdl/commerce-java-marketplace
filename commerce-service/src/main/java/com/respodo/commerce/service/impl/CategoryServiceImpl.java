package com.respodo.commerce.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.dto.CategoryDTO;
import com.respodo.commerce.dto.CategoryDescDTO;
import com.respodo.commerce.mapper.CategoryDescriptionMapper;
import com.respodo.commerce.mapper.CategoryMapper;
import com.respodo.commerce.repository.dao.CatalogRepository;
import com.respodo.commerce.repository.dao.CategoryDescRepository;
import com.respodo.commerce.repository.dao.CategoryRepository;
import com.respodo.commerce.repository.dao.LanguageRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.domain.Catalog;
import com.respodo.commerce.repository.domain.Category;
import com.respodo.commerce.repository.domain.CategoryDesc;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private CategoryDescRepository categoryDescRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CatalogRepository catalogRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private CategoryDescriptionMapper categoryDescriptionMapper;

	@Override
	public CategoryDTO getCategoryById(Long id) {
		Category domainCategory = categoryRepository.findOne(id);
		if (domainCategory == null) {
			return null;
		}
		CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(domainCategory);
		List<CategoryDescDTO> description = getCategoryDescription(domainCategory
				.getId());
		categoryDTO.setDescription(description);
		List<CategoryDTO> subCategories = getSubCategories(domainCategory
				.getId());
		categoryDTO.setSubCategories(subCategories);
		return categoryDTO;
	}

	@Override
	public List<CategoryDTO> getStoreCategories(Long storeId) {
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		Store store = storeRepository.findOne(storeId);
		if (store != null) {
			Catalog activeCatalog = store.getActiveCatalog();
			if (activeCatalog != null) {
				categoryRepository
						.findByCatalogIdAndCatLevel(activeCatalog.getId(), 1)
						.forEach(
								category -> {
									CategoryDTO categoryDTO = categoryMapper
											.toCategoryDTO(category);
									List<CategoryDescDTO> description = getCategoryDescription(category
											.getId());
									categoryDTO.setDescription(description);
									List<CategoryDTO> subCategories = getSubCategories(category
											.getId());
									categoryDTO.setSubCategories(subCategories);
									categoryDTOs.add(categoryDTO);

								});
			}
		}

		return categoryDTOs;
	}

	@Override
	public List<CategoryDTO> getCatalogCategories(Long catalogId) {
		Catalog catalog = catalogRepository.findOne(catalogId);
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		if (catalog != null) {
			categoryRepository
					.findByCatalogIdAndCatLevel(catalog.getId(), 1)
					.forEach(
							category -> {
								CategoryDTO categoryDTO = categoryMapper
										.toCategoryDTO(category);
								List<CategoryDescDTO> description = getCategoryDescription(category
										.getId());
								categoryDTO.setDescription(description);
								List<CategoryDTO> subCategories = getSubCategories(category
										.getId());
								categoryDTO.setSubCategories(subCategories);
								categoryDTOs.add(categoryDTO);

							});
		}

		return categoryDTOs;
	}

	@Override
	public List<CategoryDTO> getSubCategories(Long categoryId) {
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		List<Category> subCategories = categoryRepository
				.findByParentCategoryId(categoryId);
		subCategories.forEach(category -> {
			CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);
			List<CategoryDescDTO> description = getCategoryDescription(category
					.getId());
			categoryDTO.setDescription(description);
			List<CategoryDTO> subCategories2 = getSubCategories(category
					.getId());
			categoryDTO.setSubCategories(subCategories2);
			categoryDTOs.add(categoryDTO);

		});

		return categoryDTOs;
	}

	@Override
	public List<CategoryDescDTO> getCategoryDescription(Long categoryId) {
		List<CategoryDescDTO> categoryDescDTOs = new ArrayList<CategoryDescDTO>();
		List<CategoryDesc> categoryDescDomain = categoryDescRepository
				.findByCategoryId(categoryId);
		categoryDescDomain.forEach(categoryDesc -> {
			CategoryDescDTO categoryDescDTO = categoryDescriptionMapper
					.toCategoryDescriptionDTO(categoryDesc);
			categoryDescDTOs.add(categoryDescDTO);
		});

		return categoryDescDTOs;
	}

	@Override
	public CategoryDTO saveCategory(Long catalogId, CategoryDTO category) {
		if (category == null) {
			return null;
		}
		Catalog catalog = catalogRepository.findOne(catalogId);
		Category categoryDomain = categoryMapper.toCategoryDomain(category);
		categoryDomain.setCatalog(catalog);
		if (category.getParentId() != null) {
			Category parentCategory = categoryRepository.findOne(category
					.getParentId());
			categoryDomain.setParentCategory(parentCategory);
			categoryDomain.setCatLevel(parentCategory.getCatLevel() + 1);
		} else {
			categoryDomain.setCatLevel(1);
		}
		Set<CategoryDesc> categoryDesc = new HashSet<CategoryDesc>();
		for (CategoryDescDTO description : category.getDescription()) {
			CategoryDesc descriptionDomain = categoryDescriptionMapper
					.toCategoryDescriptionDomain(description);
			descriptionDomain.setActive(true);
			if (description.getLanguage() != null) {

				descriptionDomain
						.setLanguage(languageRepository
								.findOneByLocale(description.getLanguage()
										.getLocale()));
			}
			descriptionDomain.setCategory(categoryDomain);
			categoryDescRepository.save(descriptionDomain);
			categoryDesc.add(descriptionDomain);
		}
		categoryDomain.setCategoryDescs(categoryDesc);
		categoryRepository.save(categoryDomain);
		return getCategoryById(categoryDomain.getId());
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO category, Long categoryId) {
		if (category == null) {
			return null;
		}
		Category dbCategory = categoryRepository.getOne(categoryId);
		if (category.getParentId() != null) {
			Category parentCategory = categoryRepository.findOne(category
					.getParentId());
			dbCategory.setParentCategory(parentCategory);
			dbCategory.setCatLevel(parentCategory.getCatLevel() + 1);
		}
		dbCategory.setUniqueId(category.getUniqueId());
		for (CategoryDescDTO description : category.getDescription()) {
			if (description.getLanguage() != null) {
				CategoryDesc descriptionDomain = categoryDescRepository
						.findOneByCategoryIdAndLanguageLocale(categoryId,
								description.getLanguage().getLocale());
				if (descriptionDomain != null) {
					descriptionDomain.setName(description.getName());
					descriptionDomain.setDescription(description
							.getDescription());
					categoryDescRepository.save(descriptionDomain);
				}
			}
		}
		categoryRepository.save(dbCategory);
		return getCategoryById(categoryId);
	}

	@Override
	public CategoryDTO getCategoryByCatalogIdAndCategoryId(Long catalogId,
			Long categoryId) {
		Category domainCategory = categoryRepository.findOneByCatalogIdAndId(catalogId,categoryId);
		CategoryDTO categoryDTO=categoryMapper.toCategoryDTO(domainCategory);
		return categoryDTO;
	}
}
