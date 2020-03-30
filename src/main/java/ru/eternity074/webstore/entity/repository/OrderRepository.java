package ru.eternity074.webstore.entity.repository;

import ru.eternity074.webstore.entity.Order;

public interface OrderRepository {

	long saveOrder(Order order);

}
