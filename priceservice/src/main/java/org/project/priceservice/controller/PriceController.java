package org.project.priceservice.controller;

import java.util.List;

import org.project.priceservice.entity.Product;
import org.project.priceservice.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/prices")
public class PriceController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PriceController.class);
	
	@Autowired
	PriceService priceService;
	
	@RequestMapping(value="/cart",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public List<Product> getPriceInfoForList(@RequestBody List<Product> productList) {
		
		LOG.debug("getPriceInfoForList method called with List {}.", productList.toString());
		List<Product> requestedPriceList= priceService.getPricesByList(productList);
		return requestedPriceList;
	}
	
	@RequestMapping(value="/product/{productId}", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity<Float> getPriceForSingleProduct(@PathVariable("productId") Long productId) {
		
		LOG.debug("getPriceForSingleProduct method called with productId :  {}.", productId);
		Float requestedPrice =  priceService.findPriceByProductId(productId);
		return new ResponseEntity<Float>(requestedPrice, HttpStatus.OK);
	}
}
