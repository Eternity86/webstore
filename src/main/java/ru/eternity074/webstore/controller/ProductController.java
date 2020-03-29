package ru.eternity074.webstore.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.eternity074.webstore.entity.Product;

@Controller
public class ProductController {

	@GetMapping("/products")
	public String list(Model model) {
		Product iphone = new Product("P1234", "iPhone 11 Pro", new BigDecimal(1140));
		iphone.setDescription("Apple iPhone 11 Pro smartphone with 5.80-inch 1125 x 2436 pixels display, 64GB 4GB RAM, 12MP/12MP/12MP Triple main camera and 12MP/SL 3D Dual selfie camera ");
		iphone.setCategory("Smartphone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		model.addAttribute("product", iphone);
		
		return "products";
	}

}
