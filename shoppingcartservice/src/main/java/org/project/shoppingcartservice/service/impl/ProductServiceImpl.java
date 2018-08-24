package org.project.shoppingcartservice.service.impl;

import org.project.shoppingcartservice.client.ProductFeignClient;
import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductFeignClient productFeignClient;

	@Override
	public Product getProductById(Long productId) {
		return productFeignClient.getProductById(productId);
	}

}
