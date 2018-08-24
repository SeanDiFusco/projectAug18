package org.project.shoppingcartservice.service;

import org.project.shoppingcartservice.entity.Product;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductService {
	Product getProductById(Long productId);
}
