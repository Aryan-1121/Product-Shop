package com.productshop.orderservice.controller;

import com.productshop.orderservice.dto.OrderRequest;
import com.productshop.orderservice.service.OrderService;
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
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed Successfully";
    }
//
//
//    // using same returntype as original method -placeOrder (String) + same Method signature
//    public String fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
//        return "OOPS !! something went wrong, pls try after some time ";
//    }



//    timeout needs different method signature of type CompletableFuture<>


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
//    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
//        //this placeOrder method will now execute in different thread
//        // when time-limit is reached(3sec as in properties file) then timeout exception will be thrown
////        TODO -> catch timeout exception and throw proper error message
//        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
//
//    }
//
//
//    // using same returntype as original method -placeOrder (String) + same Method signature
//    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
//        return  CompletableFuture.supplyAsync(()->"OOPS !! something went wrong, pls try after some time ");
//    }
}
