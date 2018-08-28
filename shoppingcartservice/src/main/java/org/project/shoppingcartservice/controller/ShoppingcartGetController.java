package org.project.shoppingcartservice.controller;

import java.io.IOException;

import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/get-cart", method = RequestMethod.GET)
public class ShoppingcartGetController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ShoppingcartPostController.class);
	
	@Autowired
	ShoppingCartService shoppingCartService;

	@RequestMapping(value="/customer/{customerId}", method = RequestMethod.GET)
	public ShoppingCart getCartByUserId(@PathVariable("customerId") Long customerId) throws IOException {
		LOG.debug("getting shoppingcart for customerId : {}",customerId);
		
		ShoppingCart requestCart = shoppingCartService.findCartByCustomerId(customerId);
		return requestCart;
	}
}
