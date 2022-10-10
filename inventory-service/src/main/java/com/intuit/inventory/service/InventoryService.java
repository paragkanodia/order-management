package com.intuit.inventory.service;

import com.intuit.appUtility.dto.request.InventoryRequestDTO;

import java.util.concurrent.CompletionStage;

public interface InventoryService {
    CompletionStage<Boolean> addInventory(InventoryRequestDTO inventoryRequestDTO);

    CompletionStage<Boolean> deductInventory(InventoryRequestDTO inventoryRequestDTO);

    CompletionStage<Integer> getInventory(String productCode);
}
