package com.respodo.commerce.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.UserDTO;
import com.respodo.commerce.manager.AccountManager;
import com.respodo.commerce.mapper.UserMapper;
import com.respodo.commerce.repository.config.SecurityUtils;
import com.respodo.commerce.repository.domain.User;
import com.respodo.commerce.service.MailService;
import com.respodo.commerce.service.UserService;

@Component
public class AccountManagerImpl implements AccountManager {

	@Autowired
	private UserService userServicel;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserMapper userMapper;
	
	

	@Override
	public void registerAccount(UserDTO userDTO) {
		User user = userServicel.registerAccount(userDTO);
		if(user==null){
			throw new RuntimeException("Registration Failed");
		}
		mailService.sendActivationEmail(user);

	}

	@Override
	public void activateRegistration(String key) {
		User user = userServicel.activateRegistration(key);
		if(user==null){
			throw new RuntimeException("Activation Failed");
		}
		
	}

	@Override
	public UserDTO getUserWithAuthorities() {
		User user = userServicel.getUserWithAuthorities();
		UserDTO userDTO=userMapper.toUserDTO(user);
		return userDTO;
	}

	@Override
	public UserDTO updateAccount(UserDTO userDTO) {
		User user= userServicel.getUserbyLogin(userDTO.getEmail().toLowerCase().trim());
		if(user !=null && user.getLogin().equals(
				SecurityUtils.getCurrentLogin())){
			//userServicel.updateAccount(userDTO);
		}
		UserDTO updatedUser=userMapper.toUserDTO(user);
		return updatedUser;
	}

	@Override
	public void changePassword(String password) {
		userServicel.changePassword(password);
		
	}

	@Override
	public void requestPasswordReset(String mail) {
		User user=userServicel.requestPasswordReset(mail);
		if(user==null){
			throw new RuntimeException("Password Reset Faild");
		}
		mailService.sendPasswordResetMail(user);
	}

	@Override
	public void finishPasswordReset(String key, String newPassword) {
		User user=userServicel.completePasswordReset(newPassword, key);
		if(user==null){
			throw new RuntimeException("Password Reset Faild");
		}
	}

}
