package org.project.priceservice.repository;

import org.project.priceservice.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PriceRepository extends CrudRepository<Product, Long>{
	Product findProductByProductId(Long productId);
	//float findPricebyProductId(Long productId);
}
