package org.project.shoppingcartservice.service.impl;

import java.util.List;

import org.project.shoppingcartservice.client.PriceServiceFeignClient;
import org.project.shoppingcartservice.client.ProductFeignClient;
import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.service.PriceService;
import org.project.shoppingcartservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService{


	@Autowired
	PriceServiceFeignClient priceServiceFeignClient;

	@Override
	public List<Product> getPricesForCustomerCartList(List<Product> customerCartProductList) {
		return priceServiceFeignClient.getPriceForProductList(customerCartProductList);

	}
}




