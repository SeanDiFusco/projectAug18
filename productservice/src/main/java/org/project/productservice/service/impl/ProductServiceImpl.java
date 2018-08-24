package org.project.productservice.service.impl;

import org.project.productservice.controller.ProductController;
import org.project.productservice.entity.Product;
import org.project.productservice.repository.ProductRepository;
import org.project.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product getProductById(Long productId) {
		Product getProduct = productRepository.findProductByProductId(productId);
		return getProduct;
	}
	
	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
		
}
