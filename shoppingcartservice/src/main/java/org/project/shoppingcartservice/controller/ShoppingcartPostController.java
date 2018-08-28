package org.project.shoppingcartservice.controller;

import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.service.ProductService;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/add-to-cart")
public class ShoppingcartPostController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ShoppingcartPostController.class);
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/customer/{customerId}/product/{productId}/quantity/{quantity}", method = RequestMethod.POST)
	public ShoppingCart addProductToCartByProductId(@PathVariable("customerId") Long customerId, @PathVariable("productId") Long productId, @PathVariable("quantity") int quantity) {

		LOG.debug("addProductToCartByProductId method called with customerId= "+customerId + " , productId = " + productId);
		
		Product tempProduct = productService.getProductById(productId);
		LOG.debug(tempProduct.toString());
		
		ShoppingCart addedCart = shoppingCartService.addProductByCustomerId(tempProduct, customerId, quantity);
		LOG.debug("addedCart is : " + addedCart.toString());
		
		return addedCart;
	}
	
	/* Don't think this is needed anymore
	 * 
	@RequestMapping(value = "/prices", method = RequestMethod.POST)
	public List<Product> addPricesToCartAndRespond(@RequestBody List<Product> productListWithPrices ) {

		LOG.debug("addPricesToCartAndRespond method called with" + productListWithPrices);
		
		return productListWithPrices;
	}
	*/
	
}
