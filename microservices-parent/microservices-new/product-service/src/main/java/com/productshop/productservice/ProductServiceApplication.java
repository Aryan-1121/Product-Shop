package com.productshop.productservice;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient					// for some reason this annotation is not getting picked up from dependency, hence using eureka client via Autowired

public class ProductServiceApplication {

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
