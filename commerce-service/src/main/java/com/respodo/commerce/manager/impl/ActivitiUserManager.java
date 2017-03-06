package com.respodo.commerce.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.Authority;

@Component
public class ActivitiUserManager extends UserEntityManager{
	
	@Autowired
	private UserRepository userRepository;

	
	@Override
	public User findUserById(String userId) {
		com.respodo.commerce.repository.domain.User user=userRepository.findOneByLogin(userId);
		User activitiUser=null;
		if(user !=null){
			activitiUser=new UserEntity();
			activitiUser.setEmail(user.getEmail());
			activitiUser.setId(user.getLogin());
			activitiUser.setFirstName(user.getFirstName());
			activitiUser.setLastName(user.getLastName());
			activitiUser.setPassword(user.getPassword());
		}
		return activitiUser;
	}

	
	@Override
	public List<Group> findGroupsByUser(String userId) {
		com.respodo.commerce.repository.domain.User user=userRepository.findOneByLogin(userId);
		List<Group> groups = new ArrayList<Group>();
		if (user != null) {
			for (Authority auth : user.getAuthorities()) {
				Group group = new GroupEntity();
				group.setName(auth.getName());
				groups.add(group);
			}
		}
		return groups;
	}


}
