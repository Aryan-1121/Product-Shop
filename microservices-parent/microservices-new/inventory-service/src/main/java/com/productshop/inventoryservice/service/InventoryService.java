package com.productshop.inventoryservice.service;

import com.productshop.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        System.out.println("in inventory SErvice ");
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

}
