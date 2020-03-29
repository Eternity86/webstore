package ru.eternity074.webstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.eternity074.webstore.entity.Product;
import ru.eternity074.webstore.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());

		return "products";
	}

	@GetMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();

		return "redirect:/market/products";
	}

	@GetMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));

		return "products";
	}

	@GetMapping("/products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams,
			Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));

		return "products";
	}

////	http://localhost:8080/webstore/market/products/filter/params;brands=Google,Dell;categories=Tablet,Laptop/specification;dimention=10,20,15;color=red,green,blue
//	@GetMapping("/products/filter/{params}/{specification}")
//	public String filter(@MatrixVariable(pathVar = "params") Map<String, List<String>> criteriaFilter,
//			@MatrixVariable(pathVar = "specification") Map<String, List<String>> specFilter, Model model) {
//		return null;
//	}

	@GetMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));

		return "product";
	}

////	http://localhost:8080/webstore/product?category=laptop&price=700
//	@GetMapping("/products")
//	public String getProducts(@RequestParam String category, @RequestParam String price) {
//		return null;
//	}

//	@GetMapping(value = "/products/add")
//	public String getAddNewProductForm(Model model) {
//		Product newProduct = new Product();
//		model.addAttribute("newProduct", newProduct);
//
//		return "addProduct";
//	}

	@GetMapping(value = "/products/add")
	public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct) {
		return "addProduct";
	}

	@PostMapping("/products/add")
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		productService.addProduct(newProduct);

		return "redirect:/market/products";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category",
				"unitsInStock", "condition");
	}
	
//	@InitBinder
//	public void initialiseBinder(WebDataBinder binder) {
//		DateFormat dateFormat = new SimpleDateFormat("MMM d, YYYY");
//		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
//		binder.registerCustomEditor(Date.class, orderDateEditor);
//	}

}
