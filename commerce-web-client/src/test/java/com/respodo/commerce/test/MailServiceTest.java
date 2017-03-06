package com.respodo.commerce.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.respodo.commerce.config.MvcConfig;
import com.respodo.commerce.config.SecurityConfiguration;
import com.respodo.commerce.repository.config.JPAConfig;
import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.User;
import com.respodo.commerce.service.MailService;
import com.respodo.commerce.service.config.ActivitiConfig;
import com.respodo.commerce.service.config.MailConfiguration;
import com.respodo.commerce.service.config.ServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfig.class,MailConfiguration.class,ServiceConfig.class, SecurityConfiguration.class,ActivitiConfig.class,MvcConfig.class})
@WebAppConfiguration
@Transactional
public class MailServiceTest {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailService mailService;
	
	private User user;
	private String base;
	
	
	
	@Before
	public void setup(){
		user=userRepository.findOneByLogin("ibr.yasn@gmail.com");
		base="localhost:8080/commerce-web-client";
		
	}
	@Test
	public void testActivationMail(){
		mailService.sendActivationEmail(user);
	}

}
