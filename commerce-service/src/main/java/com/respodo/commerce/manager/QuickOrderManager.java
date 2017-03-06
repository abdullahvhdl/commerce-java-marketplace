package com.respodo.commerce.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.respodo.commerce.common.exception.UserNameAlreadyExistsException;
import com.respodo.commerce.dto.QuickOrderDTO;
import com.respodo.commerce.dto.RegisterdOrderDTO;
import com.respodo.commerce.dto.UserDTO;
import com.respodo.commerce.repository.domain.QuickOrder;
import com.respodo.commerce.repository.domain.User;
import com.respodo.commerce.service.MailService;
import com.respodo.commerce.service.QuickOrderService;
import com.respodo.commerce.service.UserService;

@Component
public class QuickOrderManager {
	@Autowired
	private QuickOrderService quickOrderService;

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private IdentityService identityService;

	public Optional<QuickOrder> quickOrder(QuickOrderDTO quickOrderDTO) {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(quickOrderDTO.getFirstName());
		userDTO.setLastName(quickOrderDTO.getLastName());
		userDTO.setPhoneNumber(quickOrderDTO.getPhoneNumber());
		userDTO.setEmail(quickOrderDTO.getEmail());
		userDTO.setPassword("passw0rd");
		userDTO.setAddress1("");
		userDTO.setCity("");
		userDTO.setCountry("");
		userDTO.setState("");
		userDTO.setZipCode("");
		User dbUser = userService.getUserbyLogin(userDTO.getEmail());
		if (dbUser != null && dbUser.getActivated()) {
			throw new UserNameAlreadyExistsException(
					"Username is Already registered: " + dbUser.getLogin());
		}
		RegisterdOrderDTO registeredOrderDTO = new RegisterdOrderDTO();
		if (dbUser != null) {
			registeredOrderDTO.setLogin(dbUser.getLogin());
			registeredOrderDTO.setQuantity(quickOrderDTO.getQuantity());
			registeredOrderDTO.setStoreId(quickOrderDTO.getStoreId());
			registeredOrderDTO.setProductId(quickOrderDTO.getProductId());
			if (dbUser.getAddress() != null
					&& dbUser.getAddress().getAddrProfile() != null) {
				dbUser.getAddress().getAddrProfile()
						.setPhone1(quickOrderDTO.getPhoneNumber());
				userService.updateAccount(dbUser);
			}
		} else {
			User user = userService.registerAccount(userDTO);
			mailService.sendActivationEmail(user);
			registeredOrderDTO.setLogin(user.getLogin());
			registeredOrderDTO.setQuantity(quickOrderDTO.getQuantity());
			registeredOrderDTO.setStoreId(quickOrderDTO.getStoreId());
			registeredOrderDTO.setProductId(quickOrderDTO.getProductId());
		}
		Optional<QuickOrder> quickOrder = quickOrderService
				.orderItem(registeredOrderDTO);
		quickOrder.ifPresent(order -> {
			Map<String, Object> variables = new HashMap<String, Object>();
			identityService.setAuthenticatedUserId("Ibrahim");
			variables.put("orderId", order.getId());
			ProcessInstance instance = runtimeService
					.startProcessInstanceByKey("quickOrderProcess", variables);
		});
		return quickOrder;
	}

	public Optional<QuickOrder> orderItem(RegisterdOrderDTO registeredOrderDTO) {
		User loggedInUser = userService.getUserWithAuthorities();
		if (loggedInUser == null) {
			throw new AccessDeniedException("User is Not Authenticated");

		}

		User dbUser = userService.getUserbyLogin(registeredOrderDTO.getLogin());
		if (dbUser != null) {
			registeredOrderDTO.setLogin(dbUser.getLogin());
			if (dbUser.getAddress() != null
					&& dbUser.getAddress().getAddrProfile() != null) {
				dbUser.getAddress().getAddrProfile()
						.setPhone1(registeredOrderDTO.getPhoneNumber());
				userService.updateAccount(dbUser);
			}
		}

		Optional<QuickOrder> quickOrder = quickOrderService
				.orderItem(registeredOrderDTO);
		quickOrder.ifPresent(order -> {
			Map<String, Object> variables = new HashMap<String, Object>();
			identityService.setAuthenticatedUserId("Ibrahim");
			variables.put("orderId", order.getId());
			ProcessInstance instance = runtimeService
					.startProcessInstanceByKey("quickOrderProcess", variables);
		});
		return quickOrder;

	}
}
