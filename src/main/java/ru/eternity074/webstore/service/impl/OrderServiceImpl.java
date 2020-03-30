package ru.eternity074.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.eternity074.webstore.entity.Order;
import ru.eternity074.webstore.entity.repository.OrderRepository;
import ru.eternity074.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Long saveOrder(Order order) {
		return orderRepository.saveOrder(order);
	}

}
