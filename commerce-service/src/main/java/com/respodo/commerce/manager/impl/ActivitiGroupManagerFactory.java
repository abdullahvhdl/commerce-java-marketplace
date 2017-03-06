package com.respodo.commerce.manager.impl;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiGroupManagerFactory implements SessionFactory{

	@Autowired
	private ActivitiGroupManager activitiGroupManager;
	
	@Override
	public Class<?> getSessionType() {
		// TODO Auto-generated method stub
		return org.activiti.engine.impl.persistence.entity.GroupIdentityManager.class;
	}

	@Override
	public Session openSession() {
		// TODO Auto-generated method stub
		return activitiGroupManager;
	}

}
