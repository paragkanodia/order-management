package com.intuit.inventory.service;

import com.intuit.appUtility.dto.request.InventoryRequestDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class InventoryServiceImpl implements InventoryService{

    private Map<String, Integer> productInventoryMap;
    @PostConstruct
    private void init(){
        this.productInventoryMap = new HashMap<>();
        this.productInventoryMap.put("1", 5);
        this.productInventoryMap.put("2", 5);
        this.productInventoryMap.put("3", 5);
    }

    @Override
    public CompletionStage<Boolean> addInventory(InventoryRequestDTO requestDTO) {
        int quantity = this.productInventoryMap.getOrDefault(requestDTO.getProductCode(), 0);
        this.productInventoryMap.put(requestDTO.getProductCode(), quantity + requestDTO.getQuantity());
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletionStage<Boolean> deductInventory(InventoryRequestDTO requestDTO) {
        int quantity = this.productInventoryMap.getOrDefault(requestDTO.getProductCode(), 0);
        if(quantity > requestDTO.getQuantity()){
            this.productInventoryMap.put(requestDTO.getProductCode(), quantity - requestDTO.getQuantity());
        }
        else
            return CompletableFuture.completedFuture(false);

        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletionStage<Integer> getInventory(String productCode) {
        return CompletableFuture.completedFuture(this.productInventoryMap.getOrDefault(productCode, 0));
    }
}
