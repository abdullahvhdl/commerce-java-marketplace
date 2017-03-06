package com.respodo.commerce.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.Authority;
import com.respodo.commerce.repository.domain.User;

@Component
public class ActivitiGroupManager extends GroupEntityManager {

	@Autowired
	private UserRepository userRepository;


	@Override
	public List<Group> findGroupsByUser(String userId) {
		List<Group> groups = new ArrayList<Group>();
		User user = userRepository.findOneByLogin(userId);
		if (user != null) {
			for (Authority auth : user.getAuthorities()) {
				Group group = new GroupEntity();
				group.setId(auth.getName());
				group.setName(auth.getName());
				groups.add(group);
			}
		}
		return groups;
	}


}
