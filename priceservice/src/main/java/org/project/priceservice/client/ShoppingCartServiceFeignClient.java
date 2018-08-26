package org.project.priceservice.client;

import java.util.ArrayList;
import java.util.List;

import org.project.priceservice.entity.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("shoppingcartservice")
public interface ShoppingCartServiceFeignClient  {

	@RequestMapping(value="/add-to-cart/prices", method = RequestMethod.POST, consumes="application/json")
	ArrayList<Product> sendPricesForProductList(@RequestBody List<Product> userShoppingCartProductList);
}


