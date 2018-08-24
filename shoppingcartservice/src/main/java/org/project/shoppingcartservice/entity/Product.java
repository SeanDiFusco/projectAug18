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
	Long Id;
	
	Long productId;

	String name;
	
	@Column(columnDefinition = "text")
	String description;
	
	//customerId associated with the product
	Long inShoppingCartId;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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
	
	
	public Product() {}
	
	public Product(Long productId, String name, String description) {
		this.productId = productId;
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [Id=" + Id + ", productId=" + productId + ", name=" + name + ", description=" + description
				+ ", inShoppingCartId=" + inShoppingCartId + "]";
	}
}
