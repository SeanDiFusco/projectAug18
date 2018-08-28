package org.project.shoppingcartservice.controller;

import java.io.IOException;
import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/delete", method = RequestMethod.DELETE)
public class ShoppingcartDeleteController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ShoppingcartDeleteController.class);
	
	@Autowired 
	ShoppingCartService shoppingCartService;

	@RequestMapping(value = "/customer/{customerId}/product/{productId}",method = RequestMethod.DELETE)
	public ShoppingCart deleteProductFromCustomerCartbyProductId(@PathVariable("customerId")Long customerId, @PathVariable("productId") Long productId) throws IOException{
		
		if(shoppingCartService.findCartByCustomerId(customerId) != null) {
			
			return shoppingCartService.deleteItemByProductIdAndCustomerId(customerId, productId);
			
		} else {
			
			LOG.error("ShoppingCart for customerId {} not found",customerId);
			throw new IOException ("Shopping Cart was not found in database") ;
		}
	}
	
	@RequestMapping(value = "/customer/{customerId}",method = RequestMethod.DELETE)
	public Long deleteCartByCustomerId(@PathVariable("customerId")Long customerId) throws IOException{
		
		if(shoppingCartService.findCartByCustomerId(customerId) != null) {
			
			return shoppingCartService.deleteCartByCustomerId(customerId);
			
		} else {
			
			LOG.error("ShoppingCart for customerId {} not found",customerId);
			throw new IOException ("Shopping Cart was not found in database") ;
		}
	}
}
	
	

