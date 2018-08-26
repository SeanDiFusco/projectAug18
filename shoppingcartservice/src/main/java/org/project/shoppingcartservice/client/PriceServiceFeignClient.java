package org.project.shoppingcartservice.client;
import java.util.ArrayList;
import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("priceservice")
public interface PriceServiceFeignClient {

	@RequestMapping(value="/prices/cart", method = RequestMethod.POST, consumes = "application/json")
	List<Product> getPriceForProductList(@RequestBody List<Product> userShoppingCartProductList);
	
	@RequestMapping(value="/prices/product/{productId}", method = RequestMethod.GET, consumes = "application/json")
	Float getPriceForSingleProduct(@PathVariable("productId") Long productId);
}

