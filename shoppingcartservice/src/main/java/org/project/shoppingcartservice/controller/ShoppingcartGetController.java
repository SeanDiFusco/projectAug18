package org.project.shoppingcartservice.controller;

import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/get-cart", method = RequestMethod.GET)
public class ShoppingcartGetController {
	
	@Autowired
	ShoppingCartService shoppingCartService;

	@RequestMapping(value="/customer/{customerId}", method = RequestMethod.GET)
	public ShoppingCart getCartByUserId(@PathVariable("customerId") Long customerId) {
		System.out.println("getting shoppingcart for customerId " + customerId);
		
		ShoppingCart requestCart = shoppingCartService.findByCustomerId(customerId);
		System.out.println(requestCart);
		return requestCart;
	}
}
