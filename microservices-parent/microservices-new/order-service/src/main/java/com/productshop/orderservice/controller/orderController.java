package com.productshop.orderservice.controller;

import com.productshop.orderservice.dto.OrderRequest;
import com.productshop.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class orderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed Successfully";
    }


    // using same returntype as original method -placeOrder (String) + same Method signature
    public String fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return "OOPS !! something went wrong, pls try after some time ";
    }

}
