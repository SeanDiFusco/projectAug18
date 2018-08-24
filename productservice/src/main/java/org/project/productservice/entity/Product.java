package org.project.productservice.entity;

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


	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Id
	Long productId;

	String name;
	
	@Column(columnDefinition = "text")
	String description;
	
	private Long quantity;


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
	
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quanitity) {
		this.quantity = quantity;
	}
	
	public Product() {}
	
	public Product(Long productId, String name, String description, Long quantity) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}
}
