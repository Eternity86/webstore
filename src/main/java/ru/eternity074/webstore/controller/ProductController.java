package ru.eternity074.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.eternity074.webstore.entity.repository.ProductRepository;
import ru.eternity074.webstore.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productRepository.getAllProducts());

		return "products";
	}

	@GetMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();

		return "redirect:/products";
	}

}
