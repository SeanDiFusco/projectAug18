package org.project.shoppingcartservice.service;

import java.util.List;

import org.project.shoppingcartservice.entity.Product;

public interface PriceService {
	List<Product> getPricesForCustomerCartList(List<Product> customerCartProductList);
	float getPriceForSingleProduct(Long productId);
}
