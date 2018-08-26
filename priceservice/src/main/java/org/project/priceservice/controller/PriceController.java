package org.project.priceservice.controller;

import java.util.List;

import org.project.priceservice.entity.Product;
import org.project.priceservice.service.PriceService;
import org.project.priceservice.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PriceController.class);
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	PriceService priceService;
	
	@RequestMapping(value="/cart",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public List<Product> getPriceInfoForList(@RequestBody List<Product> productList) {
		
		LOG.debug("getPriceInfoForList method called with List {}.", productList);

		List<Product> requestedPriceList= priceService.getPricesByList(productList);
		List<Product> requestedListIncPrices = shoppingCartService.postPriceListResponse(requestedPriceList);
		//return list calling feignclient through shoppingCartSerive as this was received as post
		return requestedListIncPrices;
	}
	
	@RequestMapping(value="/product/{productId}", method = RequestMethod.GET)
	float getPriceForSingleProduct(@PathVariable("productId") Long productId) {
		LOG.debug("getPriceForSingleProduct method called with productId :  {}.", productId);
		float requestedPrice =  priceService.findPriceByProductId(productId);
		return requestedPrice;
	}
}
