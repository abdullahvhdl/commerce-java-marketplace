package com.respodo.commerce.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.UserDTO;
import com.respodo.commerce.repository.domain.AddrProfile;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.User;

@Component
public class UserMapper {

	public User toUserDomain(UserDTO userDTO) {
		if(userDTO==null){
			return null;
		}
		User domainUser = new User();
		domainUser.setFirstName(userDTO.getFirstName());
		domainUser.setLastName(userDTO.getLastName());
		domainUser.setEmail(userDTO.getEmail());
		domainUser.setActivated(Boolean.FALSE);
		domainUser.setLogin(userDTO.getEmail().toLowerCase().trim());
		return domainUser;

	}

	public Address toAddressDomain(UserDTO userDTO) {
		if(userDTO == null){
			return null;
		}
		Address domainAddress = new Address();
		domainAddress.setAddress1(userDTO.getAddress1());
		domainAddress.setAddress2(userDTO.getAddress2());
		domainAddress.setCity(userDTO.getCity());
		domainAddress.setZipCode(userDTO.getZipCode());
		domainAddress.setState(userDTO.getState());
		domainAddress.setCountry(userDTO.getCountry());

		return domainAddress;
	}

	public AddrProfile toAddressProfileDomain(UserDTO userDTO) {
		if(userDTO==null){
			return null;
		}
		AddrProfile addressProfile = new AddrProfile();
		addressProfile.setEmail1(userDTO.getEmail());
		addressProfile.setFirstName(userDTO.getFirstName());
		addressProfile.setMidName(userDTO.getMiddleName());
		addressProfile.setLastName(userDTO.getLastName());
		addressProfile.setPhone1(userDTO.getPhoneNumber());
		return addressProfile;
	}

	public UserDTO toUserDTO(User user) {
		if(user == null){
			return null;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getLogin());
		List<String> roles = new ArrayList<String>();
		user.getAuthorities().forEach(
				autherity -> roles.add(autherity.getName()));
		userDTO.setRoles(roles);
		if (user.getAddress() != null) {
			Address address=user.getAddress();
			if(address.getAddrProfile() !=null){
				AddrProfile profile=address.getAddrProfile();
				userDTO.setPhoneNumber(profile.getPhone1());
				userDTO.setFirstName(profile.getFirstName());
				userDTO.setMiddleName(profile.getMidName());
				userDTO.setLastName(profile.getLastName());
			}
			
			userDTO.setAddress1(address.getAddress1());
			userDTO.setAddress2(address.getAddress2());
			userDTO.setCity(address.getCity());
			userDTO.setCountry(address.getCountry());
			userDTO.setState(address.getState());
			userDTO.setZipCode(address.getZipCode());
		}
		return userDTO;
	}

}
