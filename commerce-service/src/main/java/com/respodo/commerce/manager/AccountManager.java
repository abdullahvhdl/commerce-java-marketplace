package com.respodo.commerce.manager;

import com.respodo.commerce.dto.UserDTO;

public interface AccountManager {

	void registerAccount(UserDTO userDTO);
	
	UserDTO updateAccount(UserDTO userDTO);

	void activateRegistration(String key);

	UserDTO getUserWithAuthorities();

	void changePassword(String password);
	
	void requestPasswordReset(String email);
	
	void finishPasswordReset(String key, String newPassword);

}
