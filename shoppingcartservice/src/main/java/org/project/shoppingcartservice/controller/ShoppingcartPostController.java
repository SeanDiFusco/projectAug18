package org.project.shoppingcartservice.controller;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.service.ProductService;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/add-to-cart")
public class ShoppingcartPostController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShoppingcartPostController.class);
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/customer/{customerId}/product/{productId}", method = RequestMethod.POST)
	public ShoppingCart addProductToCartByProductId(@PathVariable("customerId") Long customerId, @PathVariable("productId") Long productId) {

		logger.debug("addProductToCartByProductId method called with customerId= "+customerId + " , productId = " + productId);
		
		Product tempProduct = productService.getProductById(productId);
		System.out.println(tempProduct);
		ShoppingCart addedCart = shoppingCartService.addProductByCustomerId(tempProduct, customerId);
		System.out.println(addedCart);
		return addedCart;
	}
	
}
