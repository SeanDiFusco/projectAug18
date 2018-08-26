package org.project.priceservice.service;

import java.util.List;

import org.project.priceservice.entity.Product;

public interface PriceService {
	List<Product> getPricesByList(List<Product> productList);
	float findPriceByProductId(Long productId);
}
