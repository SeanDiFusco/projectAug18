package org.project.productservice.service;

import org.project.productservice.entity.Product;

public interface ProductService {
	Product getProductById(Long productId);
	Product addProduct(Product product);
}
