package com.intuit.commons.restClients.inventoryService.service;


import com.intuit.commons.restClients.inventoryService.request.DeductInventoryRequestDTO;
import com.intuit.commons.restClients.inventoryService.request.RevertInventoryDeductionRequestDTO;

import java.util.concurrent.CompletionStage;

public interface InventoryService {

    CompletionStage<Boolean> revertInventoryDeduction(RevertInventoryDeductionRequestDTO inventoryRequestDTO);

    CompletionStage<Boolean> deductInventory(DeductInventoryRequestDTO inventoryRequestDTO);
}
