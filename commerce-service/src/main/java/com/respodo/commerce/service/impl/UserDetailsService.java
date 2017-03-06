package com.respodo.commerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.activiti.engine.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.common.exception.UserNotActivatedException;
import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.User;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory
			.getLogger(UserDetailsService.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private IdentityService identityService;

	@Override
	public UserDetails loadUserByUsername(final String login) {
		log.debug("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase();
		User userFromDatabase = userRepository.findOneByLogin(lowercaseLogin
				.toLowerCase().trim());
		if (userFromDatabase == null) {
			throw new UsernameNotFoundException("User " + lowercaseLogin
					+ " was not found in the database");
		}
		if (!userFromDatabase.getActivated()) {
			throw new UserNotActivatedException("User " + lowercaseLogin
					+ " was not activated");
		}
		List<GrantedAuthority> grantedAuthorities = userFromDatabase
				.getAuthorities()
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority
						.getName())).collect(Collectors.toList());
		identityService.setAuthenticatedUserId(login);
		return new org.springframework.security.core.userdetails.User(
				lowercaseLogin, userFromDatabase.getPassword(), grantedAuthorities);
	}
}
