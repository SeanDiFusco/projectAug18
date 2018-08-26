package org.project.productservice.repository;

import org.project.productservice.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Long>{
	Product findProductByProductId(Long productId);
	@SuppressWarnings("unchecked")
	Product save(Product product);
}
