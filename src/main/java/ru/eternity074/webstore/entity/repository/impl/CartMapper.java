package ru.eternity074.webstore.entity.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ru.eternity074.webstore.entity.CartItem;
import ru.eternity074.webstore.entity.repository.Cart;
import ru.eternity074.webstore.service.ProductService;

public class CartMapper implements RowMapper<Cart> {

	private CartItemMapper cartItemMapper;
	private NamedParameterJdbcTemplate jdbcTemplate;

	public CartMapper(NamedParameterJdbcTemplate jdbcTempleate, ProductService productService) {
		this.jdbcTemplate = jdbcTempleate;
		cartItemMapper = new CartItemMapper(productService);
	}

	@Override
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString("ID");
		Cart cart = new Cart(id);
		String SQL = String.format("SELECT * FROM CART_ITEM WHERE CART_ID = '%s'", id);
		List<CartItem> cartItems = jdbcTemplate.query(SQL, cartItemMapper);
		cart.setCartItems(cartItems);
		
		return cart;
	}

}
