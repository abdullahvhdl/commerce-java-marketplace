package com.respodo.commerce.service;

import org.springframework.scheduling.annotation.Scheduled;

import com.respodo.commerce.dto.UserDTO;
import com.respodo.commerce.repository.domain.User;

public interface UserService {
	public User activateRegistration(String key);

	public User completePasswordReset(String newPassword, String key);

	public User requestPasswordReset(String mail);

	public User changePassword(String password);

	public User getUserWithAuthorities();

	/**
	 * Not activated users should be automatically deleted after 3 days.
	 * <p/>
	 * <p>
	 * This is scheduled to get fired everyday, at 01:00 (am).
	 * </p>
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers();

	public User registerAccount(UserDTO userDTO);

	public User getUserById(Long userId);

	public User getUserbyLogin(String login);

	public User updateAccount(User newUser);

}
