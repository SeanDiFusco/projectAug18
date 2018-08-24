package org.project.shoppingcartservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.project.shoppingcartservice.entity.Product;
import org.project.shoppingcartservice.entity.ShoppingCart;
import org.project.shoppingcartservice.service.ProductService;
import org.project.shoppingcartservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
//@EnableCircuitBreaker
public class ShoppingcartServiceApp implements CommandLineRunner
{
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ProductService productService;
	
	
	// spring.sleuth.sampler.percentage. 
	// 10% sent by default.
	// This ensures 100% are sent
	/*@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}
	*/
	
    public static void main( String[] args )
    {
       SpringApplication.run(ShoppingcartServiceApp.class, args);
    }


	@Override
	public void run(String... args) throws Exception {
		Product testProduct1 = new Product(1L, "test1", "test1 description");
		Product testProduct2 = new Product(2L, "test2", "test2 description");
		List<Product> testList = new ArrayList<Product>();
		testList.add(testProduct1);
		testList.add(testProduct2);
		
		ShoppingCart testCart = new ShoppingCart();
		testCart.setCartId(1L);
		testCart.setCustomerId(123L);
		testCart.setCustomerCartProductList(testList);

		shoppingCartService.addAShoppingCart(testCart);
	}
}
