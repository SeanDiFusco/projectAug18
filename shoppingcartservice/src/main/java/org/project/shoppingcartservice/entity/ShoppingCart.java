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
	private Long id;
	
	@Column(unique=true)
	private Long customerId;
	
	@OneToMany(mappedBy = "id",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Product> customerCartProductList;

	private Float totalPrice;

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

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ShoppingCart(Long cartId, Long customerId, List<Product> customerCartProductList) {
		super();
		this.customerId = customerId;
		this.customerCartProductList = customerCartProductList;
	}
	
	public ShoppingCart() {}

	@Override
	public String toString() {
		return "ShoppingCart [customerId=" + customerId + ", customerCartProductList="
				+ customerCartProductList + "]";
	};
}
