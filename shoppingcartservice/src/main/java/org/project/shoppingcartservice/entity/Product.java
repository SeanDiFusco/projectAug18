package org.project.shoppingcartservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long productId;

	private String name;
	
	@Column(columnDefinition = "text")
	private String description;
	
	//customerId associated with the product
	private Long inShoppingCartId;
	
	private float price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getInShoppingCartId() {
		return inShoppingCartId;
	}

	public void setInShoppingCartId(Long inShoppingCartId) {
		this.inShoppingCartId = inShoppingCartId;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	
	public Product() {}
	
	public Product(Long productId, String name, String description) {
		this.productId = productId;
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productId=" + productId + ", name=" + name + ", description=" + description
				+ ", inShoppingCartId=" + inShoppingCartId + "]";
	}
}
