package com.respodo.commerce.manager.impl;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiUserManagerFactory implements SessionFactory {

	@Autowired
	ActivitiUserManager activitiUserManager;
	
	@Override
	public Class<?> getSessionType() {
		// TODO Auto-generated method stub
		return org.activiti.engine.impl.persistence.entity.UserIdentityManager.class;
	}

	@Override
	public Session openSession() {
		// TODO Auto-generated method stub
		return activitiUserManager;
	}

}
