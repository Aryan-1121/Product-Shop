package com.productshop.inventoryservice.controller;


import com.productshop.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping("/sku-code")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping("check")
    @ResponseStatus(HttpStatus.OK)
    public String check(){
        return "InventoryController check method";
    }

}
