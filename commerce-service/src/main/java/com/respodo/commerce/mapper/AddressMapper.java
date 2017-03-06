package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.AddressDTO;
import com.respodo.commerce.repository.domain.Address;

@Component
public class AddressMapper {

	public AddressDTO toAddressDTO(Address address) {
		if(address ==null){
			return null;
		}
		AddressDTO addressDTO=new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setAddress1(address.getAddress1());
		addressDTO.setAddress2(address.getAddress2());
		addressDTO.setAddressType(address.getAddressType());
		addressDTO.setCity(address.getCity());
		addressDTO.setCountry(address.getCountry());
		addressDTO.setState(address.getState());
		addressDTO.setZipCode(address.getZipCode());
		return addressDTO;
	}

	public Address toAddressDomain(AddressDTO address) {
		if(address ==null){
			return null;
		}
		Address domainAddress=new Address();
		
		domainAddress.setAddress1(address.getAddress1());
		domainAddress.setAddress2(address.getAddress2());
		domainAddress.setAddressType(address.getAddressType());
		domainAddress.setCity(address.getCity());
		domainAddress.setCountry(address.getCountry());
		domainAddress.setState(address.getState());
		domainAddress.setZipCode(address.getZipCode());
		
		
		return domainAddress;
	}

}
