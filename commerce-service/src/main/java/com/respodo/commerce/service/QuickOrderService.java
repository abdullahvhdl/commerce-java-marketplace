package com.respodo.commerce.service;

import java.util.Optional;

import org.activiti.engine.delegate.DelegateExecution;

import com.respodo.commerce.dto.RegisterdOrderDTO;
import com.respodo.commerce.repository.domain.QuickOrder;

public interface QuickOrderService {

	Optional<QuickOrder> orderItem(RegisterdOrderDTO registeredOrderDTO);

	Optional<QuickOrder> getOrder(Long orderId);

	public void getOrderDetails(DelegateExecution execution);

	public void updateOrder(DelegateExecution execution);
}
