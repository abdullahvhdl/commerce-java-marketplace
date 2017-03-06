package com.respodo.commerce.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.dto.UserDTO;
import com.respodo.commerce.manager.AccountManager;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

	private final Logger log = LoggerFactory.getLogger(AccountResource.class);

	@Autowired
	private AccountManager accountManager;

	/**
	 * POST /register -> register the user.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> registerAccount(
			@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {
		accountManager.registerAccount(userDTO);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * GET /activate -> activate the registered user.
	 */
	@RequestMapping(value = "/activate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> activateAccount(
			@RequestParam(value = "key") String key) {
		accountManager.activateRegistration(key);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * GET /authenticate -> check if the user is authenticated, and return its
	 * login.
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String isAuthenticated(HttpServletRequest request) {
		log.debug("REST request to check if the current user is authenticated");
		return request.getRemoteUser();
	}

	/**
	 * GET /account -> get the current user.
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getAccount() {
		UserDTO user = accountManager.getUserWithAuthorities();
		if(user==null){
			return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * POST /account -> update the current user information.
	 */
	@RequestMapping(value = "/account", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveAccount(@RequestBody UserDTO userDTO) {
		accountManager.updateAccount(userDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * POST /change_password -> changes the current user's password
	 */
	@RequestMapping(value = "/account/change_password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changePassword(@RequestBody String password) {
		if (password == null || password.length() < 5 || password.length() > 50) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		accountManager.changePassword(password);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/account/reset_password/init", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> requestPasswordReset(@RequestBody String mail) {

		accountManager.requestPasswordReset(mail);
		return new ResponseEntity<>("e-mail was sent", HttpStatus.OK);

	}

	@RequestMapping(value = "/account/reset_password/finish", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> finishPasswordReset(
			@RequestParam(value = "key") String key,
			@RequestParam(value = "newPassword") String newPassword) {
		accountManager.finishPasswordReset(newPassword, key);
		return  new ResponseEntity<String>(HttpStatus.OK);
	}
}
