package com.productshop.apigateway;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ApiGatewayApplication {
    @Autowired
    private EurekaClient eurekaClient;
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class,args);
    }
}
