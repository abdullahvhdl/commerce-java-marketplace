package com.respodo.commerce.service.csvparser;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.respodo.commerce.common.AuthoritiesConstants;
import com.respodo.commerce.dto.csv.UserCSVDTO;
import com.respodo.commerce.repository.dao.AddrProfileRepository;
import com.respodo.commerce.repository.dao.AddressRepository;
import com.respodo.commerce.repository.dao.AuthorityRepository;
import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.AddrProfile;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.Authority;
import com.respodo.commerce.repository.domain.User;

@Component
public class UserCSVParser implements CSVParser {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddrProfileRepository addrProfileRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public void parseCSVFile(CSVReader reader) throws IOException {
		String[] header2 = reader.readNext();
		CsvToBean<UserCSVDTO> csv = new CsvToBean<UserCSVDTO>();
		ColumnPositionMappingStrategy<UserCSVDTO> strategy = new ColumnPositionMappingStrategy<UserCSVDTO>();
		String[] columns = header2;
		strategy.setType(UserCSVDTO.class);
		strategy.setColumnMapping(columns);

		List<UserCSVDTO> list = csv.parse(strategy, reader);
		for (UserCSVDTO bean : list) {
			User optionaUser = userRepository.findOneByLogin(bean
					.getEmail());

			if (optionaUser ==null) {
				User user = new User();
				user.setActivated(true);
				user.setLogin(bean.getEmail());
				user.setEmail(bean.getEmail());
				user.setFirstName(bean.getFirstName());
				user.setLastName(bean.getLastName());
				Authority authority = authorityRepository
						.findOne(AuthoritiesConstants.ADMIN);
				Set<Authority> authorities = new HashSet<>();
				authorities.add(authority);
				user.setPassword(passwordEncoder.encode(bean.getPassword()));
				user.setAuthorities(authorities);
				Address address = new Address();
				address.setAddress1(bean.getAddress1());
				address.setAddress2(bean.getAddress2());
				address.setAddressType(bean.getAddressType());
				address.setCity(bean.getCity());
				address.setCountry(bean.getCountry());
				address.setState(bean.getState());
				address.setZipCode(bean.getZipCode());
				AddrProfile addrProfile = new AddrProfile();
				addrProfile.setEmail1(bean.getEmail());
				addrProfile.setFirstName(bean.getFirstName());
				addrProfile.setLastName(bean.getLastName());
				addrProfile.setPhone1(bean.getPhoneNumber());
				address.setUser(user);
				address.setAddrProfile(addrProfile);
				userRepository.save(user);
				addrProfileRepository.save(addrProfile);
				addressRepository.save(address);
			} else {
				User user = optionaUser;
				if (bean.getEmail() != null && !bean.getEmail().isEmpty()) {
					user.setLogin(bean.getEmail());

					user.setEmail(bean.getEmail());
				}
				if (bean.getFirstName() != null
						&& !bean.getFirstName().isEmpty()) {
					user.setFirstName(bean.getFirstName());
				}
				if (bean.getLastName() != null && !bean.getLastName().isEmpty()) {
					user.setLastName(bean.getLastName());
				}
				if (bean.getPassword() != null && !bean.getPassword().isEmpty()) {
					user.setPassword(passwordEncoder.encode(bean.getPassword()));
				}
				Address address = user.getAddress();
				if (address == null) {
					address = new Address();
				}
				if (bean.getAddress1() != null && !bean.getAddress1().isEmpty()) {
					address.setAddress1(bean.getAddress1());
				}
				if (bean.getAddress2() != null && !bean.getAddress2().isEmpty()) {
					address.setAddress2(bean.getAddress2());
				}
				if (bean.getAddressType() != null
						&& !bean.getAddressType().isEmpty()) {
					address.setAddressType(bean.getAddressType());
				}
				if (bean.getCity() != null && !bean.getCity().isEmpty()) {
					address.setCity(bean.getCity());
				}
				if (bean.getCountry() != null && !bean.getCountry().isEmpty()) {
					address.setCountry(bean.getCountry());
				}
				if (bean.getState() != null && !bean.getState().isEmpty()) {
					address.setState(bean.getState());
				}
				if (bean.getZipCode() != null && !bean.getZipCode().isEmpty()) {
					address.setZipCode(bean.getZipCode());
				}
				AddrProfile addrProfile = address.getAddrProfile();
				if (addrProfile == null) {
					addrProfile = new AddrProfile();
				}
				if (bean.getEmail() != null && !bean.getEmail().isEmpty()) {
					addrProfile.setEmail1(bean.getEmail());
				}
				if (bean.getFirstName() != null
						&& !bean.getFirstName().isEmpty()) {
					addrProfile.setFirstName(bean.getFirstName());
				}
				if (bean.getLastName() != null && !bean.getLastName().isEmpty()) {
					addrProfile.setLastName(bean.getLastName());
				}
				if (bean.getPhoneNumber() != null
						&& !bean.getPhoneNumber().isEmpty()) {
					addrProfile.setPhone1(bean.getPhoneNumber());
				}
				address.setUser(user);
				address.setAddrProfile(addrProfile);
				userRepository.save(user);
				addrProfileRepository.save(addrProfile);
				addressRepository.save(address);
			}
		}
	}

	@Override
	public void parseCSVFileForStore(Long storeId, CSVReader csvReader) {
		
	}

	@Override
	public void parseCSVFileForCatalog(Long storeId, Long catalogId,
			CSVReader csvReader) {
		// TODO Auto-generated method stub
		
	}

}
