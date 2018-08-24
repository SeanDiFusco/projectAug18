package org.project.zuulserver;

import java.util.Collections;
import java.util.List;

import org.project.zuulserver.util.CorrelationIdContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableZuulProxy
public class ZuulserverApp {
	
	// spring.sleuth.sampler.percentage. 
	// 10% sent by default.
	// This ensures 100% are sent
	@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		
		if(interceptors == null) {
			restTemplate.setInterceptors(Collections.singletonList(new CorrelationIdContextInterceptor()));
		} else {
			interceptors.add(new CorrelationIdContextInterceptor());
			restTemplate.setInterceptors(interceptors);
		}
		
		return restTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ZuulserverApp.class, args);
	}
}