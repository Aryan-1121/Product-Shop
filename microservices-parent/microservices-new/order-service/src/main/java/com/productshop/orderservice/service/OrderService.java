package com.productshop.orderservice.service;


import com.productshop.orderservice.dto.InventoryResponse;
//import com.productshop.inventoryservice.dto.InventoryResponse;
import com.productshop.orderservice.dto.OrderLineItemsDto;
import com.productshop.orderservice.dto.OrderRequest;
import com.productshop.orderservice.model.Order;
import com.productshop.orderservice.model.OrderLineItems;
import com.productshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    private final WebClient.Builder webClientBuilder;
    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems=  orderRequest.getOrderLineItemsDtoList()
                .stream()
//                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes= order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

//        //call inventory  service  to check if order is present in stock
        InventoryResponse[] inventoryResponseArray= webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())    // this will make our uri look like this -> http://localhost:8082/api/inventory?skucode=iPhone_13&skuCode=iPhone_14
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();               //this block will allow it for synchronous call  otherwise byvdefault it(webClientBuilder) was supposed to be Asynchronous call
        assert inventoryResponseArray != null;
        System.out.println("\n---------------------------\n"+Arrays.stream(inventoryResponseArray).toList());
        boolean allProductsInStock= Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

        if(inventoryResponseArray.length < skuCodes.size())
            throw new IllegalArgumentException("Such Item is not present");
        else if(allProductsInStock){
            orderRepository.save(order);
            return "Order placed Successfully";
        }
        else
            throw new IllegalArgumentException("out of stock");

//
//        orderRepository.save(order);
//        return "Order placed Successfully";
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
