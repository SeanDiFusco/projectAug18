package org.project.productservice.service;

import java.io.IOException;

import org.project.productservice.entity.Product;

public interface ProductService {
	Product getProductById(Long productId) throws IOException;
	Product addProduct(Product product) throws Exception;
}
