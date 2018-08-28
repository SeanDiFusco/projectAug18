package org.project.productservice.service.impl;

import java.io.IOException;

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
	public Product getProductById(Long productId) throws IOException {
		if(productRepository.findProductByProductId(productId)!=null) {
			Product getProduct = productRepository.findProductByProductId(productId);
			return getProduct;
		} else {
			LOG.error("product with productId {} not found in database",productId);
			return null;
		}
	}
		
	
	@Override
	public Product addProduct(Product product) throws IOException {
		Long productId = product.getProductId();
		
		if(getProductById(productId)!=null) {
			LOG.error("product with productId {} already found when in database",productId);
			return null;
		} else {
			if(product.getProductId()!= null) {
				return productRepository.save(product);
			} else {
				LOG.error("product without a productId cannot be added to database");
				throw new IOException ("product without productId cannot be added to database");
			}
		}
	}	
}
