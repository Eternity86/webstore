package ru.eternity074.webstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.eternity074.webstore.entity.Product;
import ru.eternity074.webstore.entity.repository.ProductRepository;
import ru.eternity074.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void updateAllStock() {
		List<Product> allProducts = productRepository.getAllProducts();
		for (Product product : allProducts) {
			if (product.getUnitsInStock() < 500)
				productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000);
		}
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.getAllProducts();

		return allProducts;
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}

}
