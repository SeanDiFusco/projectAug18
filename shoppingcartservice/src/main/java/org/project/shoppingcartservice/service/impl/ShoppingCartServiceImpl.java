package org.project.shoppingcartservice.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.repository.ShoppingCartRepository;
import org.project.shoppingcartservice.service.PriceService;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
	
	@Autowired
	PriceService priceService;
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Override
	public List<ShoppingCart> findAll() {
		return shoppingCartRepository.findAll();
	}
	
	@Override
	public ShoppingCart findCartByCustomerId(Long customerId) throws IOException {
		if(shoppingCartRepository.findByCustomerId(customerId) != null) {
			ShoppingCart cart = shoppingCartRepository.findByCustomerId(customerId);
			LOG.debug("cart in findCartByCustomerId is : {}", cart.toString());
			List<Product> cartList = cart.getCustomerCartProductList();
			LOG.debug("cartList in findCartByCustomerId is : {}", cartList.toString());
			List<Product> updatedCartList = priceService.getPricesForCustomerCartList(cartList);
			LOG.debug("updatedCartList in findCartByCustomerId is : {}", updatedCartList.toString());
			cart.setCustomerCartProductList(updatedCartList);
			return cart;
		} else {
			LOG.error("Shopping cart with customerId = {} not found",customerId);
			throw new IOException ("Shopping Cart was not found in database") ;
		}
	}

	/* I will be removing cart Id (Legacy code)
	@Override
	public ShoppingCart findShoppingCartByCartId(Long id) {
		System.out.println("Finding shopping car by cartId : "+id);
		ShoppingCart requestCart = shoppingCartRepository.findOne(id);
		System.out.println("RequestCart productlist is : "+ requestCart.getCustomerCartProductList().toString());
		return requestCart;
	}*/

	@Override
	public ShoppingCart addProductByCustomerId(Product product, Long customerId, int quantity) {
		ShoppingCart tempCart = new ShoppingCart();
		
		//If customer cart already exists add Product to their cart
		if(shoppingCartRepository.findByCustomerId(customerId) != null) {
			LOG.debug("customer cart exists and adding Product with productId {} to cart customerId {}",product.getProductId(),customerId);
			tempCart = shoppingCartRepository.findByCustomerId(customerId);
			LOG.debug("customer cart exists: {} ", tempCart.toString());
			
			Float price = priceService.getPriceForSingleProduct(product.getProductId());
			product.setPrice(price);
			product.setInCartOfCustomerId(customerId);
			product.setQuantity(quantity);
			List<Product> newList = tempCart.getCustomerCartProductList();
			newList.add(product);
			tempCart.setCustomerCartProductList(newList);
			
			LOG.debug("tempCart.getCustomerCartProductList(): {} ", tempCart.getCustomerCartProductList());
			//quick fix for the fact that repository.save(item) seems to create a new item if customerId already exists rather than updating or replacing it.
			//shoppingCartRepository.deleteByCustomerId(customerId);
			shoppingCartRepository.save(tempCart);
		}
		else {
			// Else Create new cart for the customer and add the product to it.
			LOG.debug("customer cart doesnt exist and adding Product with productId {} , creating cart customerId {}",product.getProductId(),customerId);
			tempCart.setCustomerId(customerId);
			List<Product> newList = new ArrayList<Product>();
			
			//issue with detached entity quick fixed with new object call
			Product productToAdd = new Product();
			Float price = priceService.getPriceForSingleProduct(product.getProductId());
			productToAdd.setPrice(price);
			productToAdd.setProductId(product.getProductId());
			productToAdd.setName(product.getName());
			productToAdd.setDescription(product.getDescription());
			productToAdd.setInCartOfCustomerId(customerId);
			productToAdd.setQuantity(quantity);
			
			newList.add(productToAdd);
			LOG.debug("Adding product in addProductByCustomerId method. Product is : {}", product.toString());
			tempCart.setCustomerCartProductList(newList);
			LOG.debug("tempCart productList is : {}", tempCart.getCustomerCartProductList().toString());
			shoppingCartRepository.save(tempCart);
		}

		return tempCart;
	}

	public ShoppingCart addAShoppingCart(ShoppingCart cart) {
		ShoppingCart tempCart = shoppingCartRepository.findByCustomerId(cart.getCustomerId());
		
		if(tempCart != null) {
			LOG.info("ShoppingCart with CustomerId {} already exists. Updating cart", cart.getCustomerId());
			
			//quick fix delete current user cart and add updated one to prevent duplicate in db
			shoppingCartRepository.deleteByCustomerId(cart.getCustomerId());
			return shoppingCartRepository.save(tempCart);
			} 
		else 
			{
			ShoppingCart newCart = new ShoppingCart();
			newCart.setCustomerId(cart.getCustomerId());
			List<Product> listToSave = cart.getCustomerCartProductList();
			newCart.setCustomerCartProductList(listToSave);
			
			return shoppingCartRepository.save(newCart);
			}
	}
	
	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) throws IOException {
		ShoppingCart tempCart = findCartByCustomerId(shoppingCart.getCustomerId());
		
		if(tempCart != null) {
			tempCart.setCustomerId(shoppingCart.getCustomerId());
			tempCart.setCustomerCartProductList(shoppingCart.getCustomerCartProductList());
		} else {
			throw new IOException ("Shopping Cart was not found in database");
		}
		
		return (ShoppingCart) shoppingCartRepository.save(tempCart);
	}


	@Override
	public ShoppingCart deleteItemByProductIdAndCustomerId(Long customerId, Long productId) throws IOException {
		//get customer cart
		ShoppingCart tempCart = findCartByCustomerId(customerId);
		if(tempCart != null) {
			List<Product> tempList = tempCart.getCustomerCartProductList();
			int tempListSize = tempList.size();
			
			//delete product from list
			for(int i=0; i< tempListSize; i++){
				Long currentProductId = tempList.get(i).getProductId();
				if(currentProductId == productId) {
					tempList.remove(i);
				}
			}
			//save updated list to Cart
			tempCart.setCustomerCartProductList(tempList);
			
			//save updated cart to database
			try {
				updateShoppingCart(tempCart);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return tempCart;
			
		} else {
			LOG.error("ShoppingCart for customerId {} not found when in deleteItemByProductIdAndCustomerId()",customerId);
			throw new IOException ("Shopping Cart was not found in database") ;
		}
		
	}
	
	@Override
	public Long deleteCartByCustomerId(Long customerId) {
		shoppingCartRepository.deleteByCustomerId(customerId);
		return customerId;
	}

}
