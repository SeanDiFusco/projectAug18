package org.project.shoppingcartservice.controller;

import java.io.IOException;
import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/delete", method = RequestMethod.DELETE)
public class ShoppingcartDeleteController {
	
	@Autowired 
	ShoppingCartService shoppingCartService;

	@RequestMapping(value = "/customer/{customerId}/product/{productId}",method = RequestMethod.DELETE)
	public ShoppingCart deleteProductFromCustomerCartbyProductId(@PathVariable("customerId")Long customerId, @PathVariable("productId") Long productId){
		
		//get customer cart
		ShoppingCart tempCart = shoppingCartService.findByCustomerId(customerId);
		List<Product> tempList = tempCart.getCustomerCartProductList();
		int tempListSize = tempList.size();
		
		//delete product from list
		for(int i=0; i< tempListSize; i++){
			Long currentProductId = tempList.get(i).getProductId();
			if(currentProductId == productId) {
				tempList.remove(i);
			}
		}
		//save updated list
		tempCart.setCustomerCartProductList(tempList);
		
		try {
			shoppingCartService.updateShoppingCart(tempCart);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempCart;
	}
}
