package org.project.shoppingcartservice.client;

import org.project.shoppingcartservice.entity.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("productservice")
public interface ProductFeignClient {

	@RequestMapping(value="/product/{productId}", method = RequestMethod.GET, consumes="application/json")
	Product getProductById(@PathVariable("productId")Long productId);
}
