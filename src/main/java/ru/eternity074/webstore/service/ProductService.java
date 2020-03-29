package ru.eternity074.webstore.service;

import java.util.List;

import ru.eternity074.webstore.entity.Product;

public interface ProductService {

	void updateAllStock();

	List<Product> getAllProducts();
	
	List<Product> getProductsByCategory(String category);

}
