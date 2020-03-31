package ru.eternity074.webstore.validator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import ru.eternity074.webstore.config.WebAppConfig;
import ru.eternity074.webstore.entity.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
@WebAppConfiguration
public class ProductValidatorTest {

	@Autowired
	private ProductValidator productValidator;

	@Test
	public void product_without_UnitPrice_should_be_invalid() {
		// Arrange
		Product product = new Product();
		BindException bindException = new BindException(product, " product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getLocalizedMessage().contains("Цена за единицу недействительна. Она не может быть пустой."));
	}

	@Test
	public void product_with_existing_productId_invalid() {
		// Arrange
		Product product = new Product("P1234", "iPhone X", new BigDecimal(500));
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, " product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(
				bindException.getLocalizedMessage().contains("Уже существует товар с таким идентификатором товара."));
	}

	@Test
	public void a_valid_product_should_not_get_any_error_during_validation() {
		// Arrange
		Product product = new Product("P9876", "iPhone X", new BigDecimal(500));
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, " product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(0, bindException.getErrorCount());
	}
}
