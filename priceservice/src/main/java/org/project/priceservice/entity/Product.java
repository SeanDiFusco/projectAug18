package org.project.priceservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long productId;

	private String name;
	
	@Transient
	private String description;
	
	//customerId associated with the product
	@Transient
	private Long inCartOfCustomerId;
	
	private Float price;
	
	@Transient
	private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Long getInCartOfCustomerId() {
		return inCartOfCustomerId;
	}

	public void setInCartOfCustomerId(Long inCartOfCustomerId) {
		this.inCartOfCustomerId = inCartOfCustomerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
				+ ",quantity " + quantity + ", inCartOfCustomerId=" + inCartOfCustomerId + "]";
	}
}
