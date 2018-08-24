package org.project.shoppingcartservice.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.repository.ShoppingCartRepository;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Override
	public ShoppingCart addProductByCustomerId(Product product, Long customerId) {
		ShoppingCart temp = new ShoppingCart();
		
		if(shoppingCartRepository.findByCustomerId(customerId) != null) {
			temp = shoppingCartRepository.findByCustomerId(customerId);
			product.setInShoppingCartId(customerId);
			temp.getCustomerCartProductList().add(product);
			shoppingCartRepository.delete(temp.getCartId());
		}
		else {
			temp.setCustomerId(customerId);
			List<Product> newList = new ArrayList<Product>();
			product.setInShoppingCartId(customerId);
			newList.add(product);
			temp.setCustomerCartProductList(newList);
		}
		shoppingCartRepository.save(temp);

		return temp;
	}

	@Override
	public List<ShoppingCart> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart findByCustomerId(Long customerId) {
		return shoppingCartRepository.findByCustomerId(customerId);
	}

	@Override
	public ShoppingCart findShoppingCartByCartId(Long id) {
		return shoppingCartRepository.findOne(id);
	}

	public ShoppingCart addAShoppingCart(ShoppingCart cart) {
		ShoppingCart tempCart = shoppingCartRepository.findByCustomerId(cart.getCustomerId());
		
		if(tempCart != null) {
			LOG.info("ShoppingCart with CustomerId {} already exists. Updating cartId {}", cart.getCustomerId(), cart.getCartId());
			//merges with existing entry.
			//TODO test if this works ok as merge may cause issues and will then need to be done manually (eg. delete and then save)
			return shoppingCartRepository.save(tempCart);
			} 
		else 
			{
			ShoppingCart newCart = new ShoppingCart();
			newCart.setCustomerId(cart.getCustomerId());
			newCart.setCustomerCartProductList(cart.getCustomerCartProductList());
			
			return shoppingCartRepository.save(newCart);
			}
	}
	
	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) throws IOException {
		ShoppingCart tempCart = findByCustomerId(shoppingCart.getCustomerId());
		
		if(tempCart != null) {
			tempCart.setCustomerId(shoppingCart.getCustomerId());
			tempCart.setCustomerCartProductList(shoppingCart.getCustomerCartProductList());
		} else {
			throw new IOException ("Shopping Cart was not found in database");
		}
		
		return (ShoppingCart) shoppingCartRepository.save(tempCart);
	}


	@Override
	public void deleteItemById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
