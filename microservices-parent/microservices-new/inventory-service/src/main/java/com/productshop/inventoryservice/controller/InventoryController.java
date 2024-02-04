package com.productshop.inventoryservice.controller;


import com.productshop.inventoryservice.dto.InventoryResponse;
import com.productshop.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //http://localhost:8082/api/inventory/iPhone_13,iPhone_14
    //http://localhost:8082/api/inventory?skucode=iPhone_13&skuCode=iPhone_14

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes){
        return inventoryService.isInStock(skuCodes);
    }

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public String check(){
        return "InventoryController check method";
    }

}
