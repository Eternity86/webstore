package ru.eternity074.webstore.entity.repository;

import java.util.List;

import ru.eternity074.webstore.entity.Product;

public interface ProductRepository {
	
	List<Product> getAllProducts();
	
	void updateStock(String productId, long noOfUnits);

}
