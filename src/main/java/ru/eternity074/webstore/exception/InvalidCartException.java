package ru.eternity074.webstore.exception;

public class InvalidCartException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5212177834006584452L;

	private String cartId;

	public InvalidCartException(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}
}
