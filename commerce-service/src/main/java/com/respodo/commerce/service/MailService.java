package com.respodo.commerce.service;

import org.springframework.scheduling.annotation.Async;

import com.respodo.commerce.repository.domain.User;

public interface MailService {

	@Async
	void sendEmail(String to, String subject, String content,
			boolean isMultipart, boolean isHtml);

	@Async
	void sendActivationEmail(User user);

	@Async
	void sendPasswordResetMail(User user);

}
