package org.project.priceservice.service.impl;

import java.util.List;

import org.project.priceservice.controller.PriceController;
import org.project.priceservice.entity.Product;
import org.project.priceservice.repository.PriceRepository;
import org.project.priceservice.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService{
	private static final Logger LOG = LoggerFactory.getLogger(PriceServiceImpl.class);
	
	@Autowired
	PriceRepository	priceRepository;
	
	public List<Product> getPricesByList(List<Product> productList){
		LOG.debug("getPricesByList method called with productList : {}", productList.toString());
		
		//for each product in product list
		//find the productId using priceRepository
		//add the price to the product in the list
		for(Product item : productList) {
			Product currentProduct= priceRepository.findProductByProductId(item.getProductId());
			LOG.debug("currentProduct is : {}", currentProduct.toString());
			item.setPrice(currentProduct.getPrice());
			LOG.debug("item price is : {}", item.getPrice().toString());
		}
		LOG.debug("productList in getPricesByList is : {}", productList.toString());
		return productList;
	}

	@Override
	public Float findPriceByProductId(Long productId) {
		Product requestedProduct = priceRepository.findProductByProductId(productId);
		if(requestedProduct != null ) {
			return requestedProduct.getPrice();
		} else {
			LOG.debug("requestedProduct method called with productId: {} which cannot be found in pricedb", productId);
			return null;
		}
	}
	
	
}
