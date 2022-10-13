package com.intuit.commons.restClients.inventoryService.service;

import com.intuit.commons.rest.RestClientUtil;
import com.intuit.commons.restClients.inventoryService.request.DeductInventoryRequestDTO;
import com.intuit.commons.restClients.inventoryService.request.RevertInventoryDeductionRequestDTO;
import com.intuit.commons.restClients.inventoryService.service.api.InventoryApi;
import retrofit2.Retrofit;

import java.util.concurrent.CompletionStage;

public class InventoryServiceImpl implements InventoryService{

    private RestClientUtil restClientUtil;
    private InventoryApi inventoryApi;

    public InventoryServiceImpl(Retrofit retrofit, RestClientUtil restClientUtil)
    {
        inventoryApi=retrofit.create(InventoryApi.class);
        this.restClientUtil=restClientUtil;
    }

    public CompletionStage<Boolean> revertInventoryDeduction(RevertInventoryDeductionRequestDTO inventoryRequestDTO) {
        return restClientUtil.toCompletableFuture(inventoryApi.revertInventoryDeduction(inventoryRequestDTO));
    }

    @Override
    public CompletionStage<Boolean> deductInventory(DeductInventoryRequestDTO inventoryRequestDTO) {
        return restClientUtil.toCompletableFuture(inventoryApi.deductInventory(inventoryRequestDTO));
    }
}
