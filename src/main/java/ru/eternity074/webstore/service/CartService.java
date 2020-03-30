package ru.eternity074.webstore.service;

import ru.eternity074.webstore.dto.CartDto;
import ru.eternity074.webstore.entity.repository.Cart;

public interface CartService {
	void create(CartDto cartDto);

	Cart read(String cartId);

	void update(String cartId, CartDto cartDto);

	void delete(String id);

	void addItem(String cartId, String productId);

	void removeItem(String cartId, String productId);
}
