package org.project.shoppingcartservice.repository;

import java.util.List;

import org.project.shoppingcartservice.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{
	ShoppingCart findByCustomerId(Long customerId);
	Long deleteByCustomerId(Long customerId);
	List<ShoppingCart> findAll();
}
