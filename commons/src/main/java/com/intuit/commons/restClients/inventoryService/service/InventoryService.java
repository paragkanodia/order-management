package com.intuit.commons.restClients.inventoryService.service;


import com.intuit.commons.restClients.inventoryService.request.InventoryRequestDTO;

import java.util.concurrent.CompletionStage;

public interface InventoryService {

    CompletionStage<Boolean> addInventory(InventoryRequestDTO inventoryRequestDTO);

    CompletionStage<Boolean> deductInventory(InventoryRequestDTO inventoryRequestDTO);
}
