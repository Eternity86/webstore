package ru.eternity074.webstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.eternity074.webstore.dto.CartDto;
import ru.eternity074.webstore.entity.repository.Cart;
import ru.eternity074.webstore.service.CartService;

@RestController
@RequestMapping(value = "rest/cart")
public class CartRestController {
	
	@Autowired
	private CartService cartService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody CartDto cartDto) {
		cartService.create(cartDto);
	}
	
	@GetMapping(value = "/{cartId}")
	public Cart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}
	
	@PutMapping(value = "/{cartId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody CartDto cartDto) {
		cartDto.setId(cartId);
		cartService.update(cartId, cartDto);
	}
	
	@DeleteMapping(value = "/{cartId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable(value = "cartId") String cartId) {
		cartService.delete(cartId);
	}
	
	@PutMapping(value = "/add/{productId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void addItem(@PathVariable String productId, HttpSession session) {
		cartService.addItem(session.getId(), productId);
	}
	
	@PutMapping(value = "/remove/{productId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void removeItem(@PathVariable String productId, HttpSession session) {
		cartService.removeItem(session.getId(), productId);
	}
}
