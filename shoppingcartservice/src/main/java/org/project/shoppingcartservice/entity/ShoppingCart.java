package org.project.shoppingcartservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long cartId;
	
	Long customerId;
	
	@Column(name="product_list")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	List<Product> customerCartProductList;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Product> getCustomerCartProductList() {
		return customerCartProductList;
	}

	public void setCustomerCartProductList(List<Product> customerCartProductList) {
		this.customerCartProductList = customerCartProductList;
	}

	public ShoppingCart(Long cartId, Long customerId, List<Product> customerCartProductList) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.customerCartProductList = customerCartProductList;
	}
	
	public ShoppingCart() {};
}
