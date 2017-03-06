package com.respodo.commerce.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.common.AddressTypeConstants;
import com.respodo.commerce.common.AuthoritiesConstants;
import com.respodo.commerce.common.exception.EmailAlreadyExistsException;
import com.respodo.commerce.dto.UserDTO;
import com.respodo.commerce.mapper.UserMapper;
import com.respodo.commerce.repository.config.SecurityUtils;
import com.respodo.commerce.repository.dao.AddrProfileRepository;
import com.respodo.commerce.repository.dao.AddressRepository;
import com.respodo.commerce.repository.dao.AuthorityRepository;
import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.AddrProfile;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.Authority;
import com.respodo.commerce.repository.domain.User;
import com.respodo.commerce.service.UserService;
import com.respodo.commerce.service.util.RandomUtil;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddrProfileRepository addressProfileRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public User activateRegistration(String key) {
		log.debug("Activating user for activation key {}", key);
		User user = userRepository.findOneByActivationKey(key);
		if (user != null) {
			user.setActivated(true);
			user.setActivationKey(null);
			userRepository.save(user);
			log.debug("Activated user: {}", user);
		}
		return user;
	}

	@Override
	public User completePasswordReset(String newPassword, String key) {
		log.debug("Reset user password for reset key {}", key);
		User user = userRepository.findOneByResetKey(key);
		DateTime oneDayAgo = DateTime.now().minusHours(24);
		if (user != null
				&& user.getResetDate().isAfter(
						oneDayAgo.toInstant().getMillis())) {
			user.setActivated(true);
			user.setPassword(passwordEncoder.encode(newPassword));
			user.setResetKey(null);
			user.setResetDate(null);
			userRepository.save(user);
		}
		return user;
	}

	@Override
	public User requestPasswordReset(String mail) {
		User user = userRepository.findOneByLogin(mail);
		if (user != null) {
			user.setResetKey(RandomUtil.generateResetKey());
			user.setResetDate(DateTime.now());
			userRepository.save(user);
		}
		return user;
	}

	@Override
	public User changePassword(String password) {
		User user = userRepository.findOneByLogin(SecurityUtils
				.getCurrentLogin());
		if (user != null) {
			String encryptedPassword = passwordEncoder.encode(password);
			user.setPassword(encryptedPassword);
			userRepository.save(user);
			log.debug("Changed password for User: {}", user);
		}
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserWithAuthorities() {
		String login = SecurityUtils.getCurrentLogin();
		User user = userRepository.findOneByLogin(login);
		if(user == null){
			return null;
		}
		for(Authority a:user.getAuthorities()){
			
		}
		return user;
	}

	@Override
	public void removeNotActivatedUsers() {
		DateTime now = new DateTime();
		List<User> users = userRepository
				.findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
		for (User user : users) {
			log.debug("Deleting not activated user {}", user.getLogin());
			userRepository.delete(user);
		}
	}

	@Override
	public User registerAccount(UserDTO userDTO) {
		User registeredUser= userRepository.findOneByLogin(userDTO.getEmail().toLowerCase()
				.trim());
		if ( registeredUser != null) {
			throw new EmailAlreadyExistsException(
					"Email is Already registered: " + registeredUser.getLogin());

		}

		User user = userMapper.toUserDomain(userDTO);
		Address address = userMapper.toAddressDomain(userDTO);
		AddrProfile addressProfile = userMapper.toAddressProfileDomain(userDTO);

		Authority authority = authorityRepository
				.findOne(AuthoritiesConstants.CUSTOMER);
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authority);
		String encryptedPassword = passwordEncoder
				.encode(userDTO.getPassword());
		user.setPassword(encryptedPassword);
		user.setAuthorities(authorities);
		user.setActivationKey(RandomUtil.generateActivationKey());
		userRepository.save(user);
		addressProfileRepository.save(addressProfile);
		address.setUser(user);
		address.setAddressType(AddressTypeConstants.PILLING);
		address.setAddrProfile(addressProfile);
		addressRepository.save(address);

		return user;

	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public User getUserbyLogin(String login) {
		return userRepository.findOneByLogin(login);
	}

	@Override
	public User updateAccount(User newUser) {
		userRepository.save(newUser);
		addressRepository.save(newUser.getAddress());
		addressProfileRepository.save(newUser.getAddress().getAddrProfile());
		return newUser;
	}
}
