package org.project.productservice.controller;

import java.io.IOException;

import org.project.productservice.entity.Product;
import org.project.productservice.entity.ProductRequest;
import org.project.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/{productId}")
public class ProductController {
	
	@Autowired
	ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public Product getProductInfo(@PathVariable Long productId) throws IOException {
		
		logger.debug("getProductInfo method called with productId : {}.", productId);
		Product requestedProduct= productService.getProductById(productId);
		return requestedProduct;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) throws Exception {
		
		logger.debug("addProduct method called with product : {}.", product.toString());
			return productService.addProduct(product);
	}
}
