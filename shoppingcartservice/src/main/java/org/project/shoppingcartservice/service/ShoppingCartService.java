package org.project.shoppingcartservice.service;

import java.io.IOException;
import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;

public interface ShoppingCartService {
	List<ShoppingCart> findAll();
	ShoppingCart findCartByCustomerId(Long customerId) throws IOException;
	
	//CartId being removed (legacy code)
	//ShoppingCart findShoppingCartByCartId(Long id);
	
	ShoppingCart addProductByCustomerId(Product product, Long customerId, int quantity);
	ShoppingCart addAShoppingCart(ShoppingCart cart);

	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) throws IOException;
	ShoppingCart deleteItemByProductIdAndCustomerId(Long customerId, Long productId) throws IOException;
	Long deleteCartByCustomerId(Long customerId);
}
