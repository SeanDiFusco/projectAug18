package org.project.shoppingcartservice.repository;

import org.project.shoppingcartservice.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{
	ShoppingCart findByCustomerId(Long customerId);
	ShoppingCart findByCartId(Long cartId);
}
