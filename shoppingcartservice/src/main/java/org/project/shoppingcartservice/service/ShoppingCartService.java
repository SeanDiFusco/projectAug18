package org.project.shoppingcartservice.service;

import java.io.IOException;
import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;

public interface ShoppingCartService {
	ShoppingCart findByCustomerId(Long customerId);
	
	ShoppingCart addProductByCustomerId(Product product, Long customerId);
	List<ShoppingCart> findAll();
	ShoppingCart findShoppingCartByCartId(Long id);
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) throws IOException;
	void deleteItemById(Long id);
	ShoppingCart addAShoppingCart(ShoppingCart cart);
}
