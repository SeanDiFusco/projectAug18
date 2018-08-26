package org.project.priceservice.service.impl;

import java.util.List;

import org.project.priceservice.entity.Product;
import org.project.priceservice.repository.PriceRepository;
import org.project.priceservice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService{
	@Autowired
	PriceRepository	priceRepository;
	
	public List<Product> getPricesByList(List<Product> productList){
		//for each product in product list
		//find the productId using priceRepository
		//add the price to the product in the list
		//move to the next one until finish
		for(Product item : productList) {
			Product currentProduct= priceRepository.findProductByProductId(item.getProductId());
			item.setPrice(currentProduct.getPrice());
		}
		return productList;
	}

	@Override
	public Float findPriceByProductId(Long productId) {
		Product requestedProduct = priceRepository.findProductByProductId(productId);
		return requestedProduct.getPrice();
	}
	
	
}
