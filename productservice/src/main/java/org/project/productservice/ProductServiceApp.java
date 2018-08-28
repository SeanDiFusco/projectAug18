package org.project.productservice;

import org.project.productservice.entity.Product;
import org.project.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ProductServiceApp implements CommandLineRunner
{
	@Autowired
	ProductService productService;
	
    public static void main( String[] args )
    {
        SpringApplication.run(ProductServiceApp.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		/*
		Product testProduct1 = new Product(1L, "test1", "test1 description");
		System.out.println("Testproduct1 has productId "+ testProduct1.getProductId());
		Product testProduct2 = new Product(2L, "test2", "test2 description");

	

		productService.addProduct(testProduct1);
		productService.addProduct(testProduct2);
		*/
	}
}
