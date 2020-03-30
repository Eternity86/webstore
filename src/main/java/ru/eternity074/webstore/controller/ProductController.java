package ru.eternity074.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ru.eternity074.webstore.entity.Product;
import ru.eternity074.webstore.exception.NoProductsFoundUnderCategoryException;
import ru.eternity074.webstore.exception.ProductNotFoundException;
import ru.eternity074.webstore.service.ProductService;
import ru.eternity074.webstore.validator.UnitsInStockValidator;

@Controller
@RequestMapping("market")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UnitsInStockValidator unitsInStockValidator;

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
		List<Product> products = productService.getProductsByCategory(productCategory);
		if (null == products || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", products);

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
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, HttpServletRequest request) {
		String fileSeparator = System.getProperty("file.separator");
		String[] suppressedFields = result.getSuppressedFields();
		String rootDirectory = request.getSession().getServletContext().getRealPath(fileSeparator);

		if (result.hasErrors()) {
			return "addProduct";
		}

		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		MultipartFile productImage = newProduct.getProductImage();
		if (productImage != null && !productImage.isEmpty()) {
			try {
				File file = new File(rootDirectory + fileSeparator + "resources" + fileSeparator + "images"
						+ fileSeparator + newProduct.getProductId() + ".png");
				productImage.transferTo(file);
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		MultipartFile productManual = newProduct.getProductManual();
		if (productManual != null && !productManual.isEmpty()) {
			try {
				File file = new File(rootDirectory + fileSeparator + "resources" + fileSeparator + "pdfs"
						+ fileSeparator + newProduct.getProductId() + ".pdf");
				productManual.transferTo(file);
			} catch (Exception e) {
				throw new RuntimeException("Product Manual saving failed", e);
			}
		}

		productService.addProduct(newProduct);

		return "redirect:/market/products";
	}

	@RequestMapping("/products/invalidPromoCode")
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category",
				"unitsInStock", "condition", "productImage", "productManual", "language");
		binder.setValidator(unitsInStockValidator);
	}

//	@InitBinder
//	public void initialiseBinder(WebDataBinder binder) {
//		DateFormat dateFormat = new SimpleDateFormat("MMM d, YYYY");
//		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
//		binder.registerCustomEditor(Date.class, orderDateEditor);
//	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("productNotFound");

		return mav;
	}

}
