package org.project.priceservice.service;

import java.util.List;

import org.project.priceservice.entity.Product;

public interface ShoppingCartService {
	List<Product> postPriceListResponse(List<Product>productList);
}
