package com.intuit.inventory.service;

import com.intuit.appUtility.dto.request.AddInventoryRequestDTO;
import com.intuit.appUtility.dto.request.DeductInventoryRequestDTO;
import com.intuit.appUtility.dto.request.RevertInventoryDeductionRequestDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class InventoryServiceImpl implements InventoryService{

    private Map<String, Integer> productInventoryMap;
    private Map<Long, Integer> orderQuantityMap;

    @PostConstruct
    private void init(){
        this.productInventoryMap = new HashMap<>();
        this.orderQuantityMap=new HashMap<>();
    }

    @Override
    public CompletionStage<Boolean> revertInventoryDeduction(RevertInventoryDeductionRequestDTO requestDTO) {
        if(orderQuantityMap.containsKey(requestDTO.getOrderId()))
        {
            int quantity = this.productInventoryMap.getOrDefault(requestDTO.getProductCode(), 0);
            this.productInventoryMap.put(requestDTO.getProductCode(), quantity + orderQuantityMap.get(requestDTO.getOrderId()));
            this.orderQuantityMap.remove(requestDTO.getOrderId());
        }
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletionStage<Boolean> addInventory(AddInventoryRequestDTO inventoryRequestDTO) {
        int quantity = this.productInventoryMap.getOrDefault(inventoryRequestDTO.getProductCode(), 0);
        this.productInventoryMap.put(inventoryRequestDTO.getProductCode(), quantity + inventoryRequestDTO.getQuantity());
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletionStage<Boolean> deductInventory(DeductInventoryRequestDTO requestDTO) {
        if(!orderQuantityMap.containsKey(requestDTO.getOrderId()))
        {
            int quantity = this.productInventoryMap.getOrDefault(requestDTO.getProductCode(), 0);
            if(quantity >=requestDTO.getQuantity()){
                this.productInventoryMap.put(requestDTO.getProductCode(), quantity - requestDTO.getQuantity());
                this.orderQuantityMap.put(requestDTO.getOrderId(), requestDTO.getQuantity());
            }
            else
                return CompletableFuture.completedFuture(false);

            return CompletableFuture.completedFuture(true);
        }
        else
            return CompletableFuture.completedFuture(false);
    }

    @Override
    public CompletionStage<Integer> getInventory(String productCode) {
        return CompletableFuture.completedFuture(this.productInventoryMap.getOrDefault(productCode, 0));
    }
}
