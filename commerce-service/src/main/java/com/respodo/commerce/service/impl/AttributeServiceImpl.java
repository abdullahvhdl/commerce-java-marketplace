package com.respodo.commerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.common.AttributeTypeConstants;
import com.respodo.commerce.dto.AttributeDTO;
import com.respodo.commerce.dto.AttributeDescDTO;
import com.respodo.commerce.dto.AttributeValueDTO;
import com.respodo.commerce.dto.AttributeValueDescDTO;
import com.respodo.commerce.dto.ProdAssignedAttrDTO;
import com.respodo.commerce.mapper.AttributeDescMapper;
import com.respodo.commerce.mapper.AttributeMapper;
import com.respodo.commerce.mapper.AttributeValueDescMapper;
import com.respodo.commerce.mapper.AttributeValueMapper;
import com.respodo.commerce.repository.dao.AttributeDescRepository;
import com.respodo.commerce.repository.dao.AttributeRepository;
import com.respodo.commerce.repository.dao.AttributeTypeRepository;
import com.respodo.commerce.repository.dao.AttributeValueDescRepository;
import com.respodo.commerce.repository.dao.AttributeValueRepository;
import com.respodo.commerce.repository.dao.LanguageRepository;
import com.respodo.commerce.repository.dao.ProdAssignedAttrRepository;
import com.respodo.commerce.repository.dao.ProductRepository;
import com.respodo.commerce.repository.domain.Attribute;
import com.respodo.commerce.repository.domain.AttributeDesc;
import com.respodo.commerce.repository.domain.AttributeType;
import com.respodo.commerce.repository.domain.AttributeValue;
import com.respodo.commerce.repository.domain.AttributeValueDesc;
import com.respodo.commerce.repository.domain.Language;
import com.respodo.commerce.repository.domain.ProdAssignedAttr;
import com.respodo.commerce.repository.domain.Product;
import com.respodo.commerce.service.AttributeService;

@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {

	private final Logger log = LoggerFactory
			.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProdAssignedAttrRepository prodAssignedAttrRepository;

	@Autowired
	private AttributeRepository attributeRepository;

	@Autowired
	private AttributeValueRepository attributeValueRepository;

	@Autowired
	private AttributeDescRepository attributeDescRepository;

	@Autowired
	private AttributeValueDescRepository attributeValueDescRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private AttributeTypeRepository attributeTypeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private AttributeMapper attributeMapper;

	@Autowired
	private AttributeValueMapper attributeValueMapper;

	@Autowired
	private AttributeDescMapper attributeDescMapper;

	@Autowired
	private AttributeValueDescMapper attributeValueDescMapper;

	@Override
	public List<AttributeDescDTO> getAttributeDescription(Long attributeId) {
		List<AttributeDesc> descriptions = attributeDescRepository
				.findByAttributeId(attributeId);
		List<AttributeDescDTO> descriptionDTOs = new ArrayList<AttributeDescDTO>();
		descriptions.forEach(description -> {
			AttributeDescDTO descriptionDTO = attributeDescMapper
					.toAttributeDescDTO(description);
			descriptionDTOs.add(descriptionDTO);
		});
		return descriptionDTOs;
	}

	@Override
	public List<AttributeValueDescDTO> getAttributeValueDescription(
			Long attributeValueId) {
		List<AttributeValueDesc> descriptions = attributeValueDescRepository
				.findByAttributeValueId(attributeValueId);
		List<AttributeValueDescDTO> descriptionDTOs = new ArrayList<AttributeValueDescDTO>();
		descriptions.forEach(description -> {
			AttributeValueDescDTO descriptionDTO = attributeValueDescMapper
					.toAttributeValueDescDTO(description);
			descriptionDTOs.add(descriptionDTO);
		});
		return descriptionDTOs;
	}

	@Override
	public List<ProdAssignedAttrDTO> getProductBuyableAttributes(Long productId) {
		List<ProdAssignedAttrDTO> productAttrDTOs = new ArrayList<ProdAssignedAttrDTO>();
		List<ProdAssignedAttr> attributes = prodAssignedAttrRepository
				.findByProductIdAndAttribute_AttributeType_Type(productId,
						AttributeTypeConstants.BUYABLE);
		attributes
				.forEach(assignedAttribute -> {
					ProdAssignedAttrDTO productAttrDTO = new ProdAssignedAttrDTO();
					productAttrDTO.setId(assignedAttribute.getId());
					Attribute attribute = assignedAttribute.getAttribute();
					if (attribute != null) {
						AttributeDTO attributeDTO = attributeMapper
								.toAttributeDTO(attribute);
						List<AttributeDescDTO> descriptions = getAttributeDescription(attribute
								.getId());
						attributeDTO.setDescription(descriptions);
						productAttrDTO.setAttribute(attributeDTO);
					}
					AttributeValue attributeValue = assignedAttribute
							.getAttributeValue();
					if (attributeValue != null) {
						AttributeValueDTO attributeValueDTO = attributeValueMapper
								.toAttributeValueDTO(attributeValue);
						List<AttributeValueDescDTO> descriptions = getAttributeValueDescription(attributeValue
								.getId());
						attributeValueDTO.setDescription(descriptions);
						productAttrDTO.setAttributeValue(attributeValueDTO);
					}
					productAttrDTOs.add(productAttrDTO);
				});
		return productAttrDTOs;
	}

	@Override
	public List<ProdAssignedAttrDTO> getProductDisplayableAttributes(
			Long productId) {
		List<ProdAssignedAttrDTO> productAttrDTOs = new ArrayList<ProdAssignedAttrDTO>();
		List<ProdAssignedAttr> attributes = prodAssignedAttrRepository
				.findByProductIdAndAttribute_AttributeType_Type(productId,
						AttributeTypeConstants.DISPLAYABLE);
		attributes
				.forEach(assignedAttribute -> {
					ProdAssignedAttrDTO productAttrDTO = new ProdAssignedAttrDTO();
					productAttrDTO.setId(assignedAttribute.getId());
					Attribute attribute = assignedAttribute.getAttribute();
					if (attribute != null) {
						AttributeDTO attributeDTO = attributeMapper
								.toAttributeDTO(attribute);
						List<AttributeDescDTO> descriptions = getAttributeDescription(attribute
								.getId());
						attributeDTO.setDescription(descriptions);
						productAttrDTO.setAttribute(attributeDTO);
					}
					AttributeValue attributeValue = assignedAttribute
							.getAttributeValue();
					if (attributeValue != null) {
						AttributeValueDTO attributeValueDTO = attributeValueMapper
								.toAttributeValueDTO(attributeValue);
						List<AttributeValueDescDTO> descriptions = getAttributeValueDescription(attributeValue
								.getId());
						attributeValueDTO.setDescription(descriptions);
						productAttrDTO.setAttributeValue(attributeValueDTO);
					}
					productAttrDTOs.add(productAttrDTO);
				});
		return productAttrDTOs;
	}

	@Override
	public List<AttributeDTO> getAttributes(String attributeType) {
		List<Attribute> attributes = attributeRepository
				.findByAttributeType_Type(attributeType);
		List<AttributeDTO> attributeDTOs = new ArrayList<AttributeDTO>();
		attributes
				.forEach(attribute -> {
					AttributeDTO attributeDTO = attributeMapper
							.toAttributeDTO(attribute);
					List<AttributeDescDTO> description = getAttributeDescription(attribute
							.getId());
					attributeDTO.setDescription(description);
					attributeDTOs.add(attributeDTO);
				});
		return attributeDTOs;
	}

	@Override
	public AttributeDTO getAttributeById(Long attributeId) {
		Attribute attribute = attributeRepository.findOne(attributeId);
		if (attribute == null) {
			return null;
		}
		AttributeDTO attributeDTO = attributeMapper.toAttributeDTO(attribute);
		List<AttributeDescDTO> description = getAttributeDescription(attribute
				.getId());
		attributeDTO.setDescription(description);
		return attributeDTO;
	}

	@Override
	public AttributeDTO saveAttribute(AttributeDTO attribute,
			String attributeType) {

		Attribute attributeDomain = attributeMapper
				.toAttributeDomain(attribute);
		if (attributeDomain == null) {
			return null;
		}

		AttributeType type = attributeTypeRepository
				.findOneByType(attributeType);
		attributeDomain.setAttributeType(type);
		attributeRepository.save(attributeDomain);

		for (AttributeDescDTO description : attribute.getDescription()) {
			AttributeDesc descriptionDomain = attributeDescMapper
					.toAttributeDescDomain(description);
			descriptionDomain.setAttribute(attributeDomain);
			if (description.getLanguage() != null) {
				Language language = languageRepository
						.findOneByLocale(description.getLanguage().getLocale());
				descriptionDomain.setLanguage(language);
			}
			attributeDescRepository.save(descriptionDomain);
		}

		return getAttributeById(attributeDomain.getId());
	}

	@Override
	public AttributeDTO updateAttribute(AttributeDTO attribute, Long attributeId) {
		Attribute dbAttribute = attributeRepository.findOne(attributeId);
		if (attribute == null || dbAttribute == null) {
			return null;
		}
		dbAttribute.setUniqueId(attribute.getUniqueId());
		attributeRepository.save(dbAttribute);
		for (AttributeDescDTO description : attribute.getDescription()) {
			if (description.getLanguage() != null) {
				AttributeDesc descriptionDomain = attributeDescRepository
						.findOneByAttributeIdAndLanguageLocale(attributeId,
								description.getLanguage().getLocale());
				if (descriptionDomain != null) {
					descriptionDomain.setName(description.getName());
				} else {
					descriptionDomain = attributeDescMapper
							.toAttributeDescDomain(description);
					descriptionDomain.setAttribute(dbAttribute);
					Language language = languageRepository
							.findOneByLocale(description.getLanguage()
									.getLocale());
					descriptionDomain.setLanguage(language);
				}
				attributeDescRepository.save(descriptionDomain);
			}

		}
		return getAttributeById(attributeId);
	}

	@Override
	public Boolean deleteAttribute(Long attributeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttributeValueDTO> getAttributeValues(Long attributeId) {
		List<AttributeValue> attributeValues = attributeValueRepository
				.findByAttributeId(attributeId);
		List<AttributeValueDTO> attributeValueDTOs = new ArrayList<AttributeValueDTO>();
		attributeValues
				.forEach(value -> {
					AttributeValueDTO valueDTO = attributeValueMapper
							.toAttributeValueDTO(value);
					List<AttributeValueDescDTO> description = getAttributeValueDescription(value
							.getId());
					valueDTO.setDescription(description);
					attributeValueDTOs.add(valueDTO);
				});
		return attributeValueDTOs;
	}

	@Override
	public AttributeValueDTO getAttributeValue(Long attributeId,
			Long attributeValueId) {
		AttributeValue attributeValue = attributeValueRepository
				.findOne(attributeValueId);
		AttributeValueDTO attributeValueDTO = attributeValueMapper
				.toAttributeValueDTO(attributeValue);
		if (attributeValueDTO != null) {
			List<AttributeValueDescDTO> description = getAttributeValueDescription(attributeValueId);
			attributeValueDTO.setDescription(description);
		}
		return attributeValueDTO;
	}

	@Override
	public AttributeValueDTO saveAttributeValue(Long attributeId,
			AttributeValueDTO attributeValue) {
		AttributeValue attributeValueDomain = attributeValueMapper
				.toAttributeValueDomain(attributeValue);
		if (attributeValueDomain == null) {
			return null;
		}
		Attribute attribute = attributeRepository.findOne(attributeId);
		if (attribute == null) {
			return null;
		}
		attributeValueDomain.setAttribute(attribute);
		attributeValueRepository.save(attributeValueDomain);
		for (AttributeValueDescDTO description : attributeValue
				.getDescription()) {
			AttributeValueDesc descriptionDomain = attributeValueDescMapper
					.toAttributeValueDescDomain(description);
			descriptionDomain.setAttributeValue(attributeValueDomain);
			if (description.getLanguage() != null) {
				Language language = languageRepository
						.findOneByLocale(description.getLanguage().getLocale());
				descriptionDomain.setLanguage(language);
			}
			attributeValueDescRepository.save(descriptionDomain);
		}
		return getAttributeValue(attributeId, attributeValueDomain.getId());
	}

	@Override
	public AttributeValueDTO updateAttributeValue(Long attributeId,
			AttributeValueDTO attributeValue, Long attributeValueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAttributeValue(Long attributeId, Long attributeValueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdAssignedAttrDTO updateProductAssignedAttr(Long productId,
			ProdAssignedAttrDTO assignedAttr) {
		Product product = productRepository.findOne(productId);
		if (product == null) {
			return null;
		}
		ProdAssignedAttr assignedAttrDomain = null;
		if (assignedAttr.getId() != null) {
			assignedAttrDomain = prodAssignedAttrRepository
					.findOne(assignedAttr.getId());
		}

		if (assignedAttrDomain == null) {
			assignedAttrDomain = new ProdAssignedAttr();
			assignedAttrDomain.setProduct(product);
		}
		if (assignedAttr.getAttribute() != null) {
			Attribute attribute = attributeRepository.findOne(assignedAttr
					.getAttribute().getId());
			assignedAttrDomain.setAttribute(attribute);
		}
		if (assignedAttr.getAttributeValue() != null) {
			AttributeValue value = attributeValueRepository
					.findOne(assignedAttr.getAttributeValue().getId());
			assignedAttrDomain.setAttributeValue(value);
		}
		prodAssignedAttrRepository.save(assignedAttrDomain);
		return null;
	}

	@Override
	public void saveProductAssignedAttr(Long productId,
			ProdAssignedAttrDTO assignedAttr) {
		Product product = productRepository.findOne(productId);
		if (product == null) {
			return;
		}
		ProdAssignedAttr assignedAttrDomain = null;
		
		assignedAttrDomain = new ProdAssignedAttr();
		assignedAttrDomain.setProduct(product);
		
		if (assignedAttr.getAttribute() != null) {
			Attribute attribute = attributeRepository.findOne(assignedAttr
					.getAttribute().getId());
			assignedAttrDomain.setAttribute(attribute);
		}
		if (assignedAttr.getAttributeValue() != null) {
			AttributeValue value = attributeValueRepository
					.findOne(assignedAttr.getAttributeValue().getId());
			assignedAttrDomain.setAttributeValue(value);
		}
		prodAssignedAttrRepository.save(assignedAttrDomain);
		
	}
}
