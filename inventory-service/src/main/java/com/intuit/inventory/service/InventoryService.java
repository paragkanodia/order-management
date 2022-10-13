package com.intuit.inventory.service;

import com.intuit.appUtility.dto.request.AddInventoryRequestDTO;
import com.intuit.appUtility.dto.request.DeductInventoryRequestDTO;
import com.intuit.appUtility.dto.request.RevertInventoryDeductionRequestDTO;

import java.util.concurrent.CompletionStage;

public interface InventoryService {
    CompletionStage<Boolean> revertInventoryDeduction(RevertInventoryDeductionRequestDTO deductInventoryRequestDTO);

    CompletionStage<Boolean> addInventory(AddInventoryRequestDTO inventoryRequestDTO);

    CompletionStage<Boolean> deductInventory(DeductInventoryRequestDTO deductInventoryRequestDTO);

    CompletionStage<Integer> getInventory(String productCode);
}
