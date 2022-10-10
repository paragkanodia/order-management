package com.intuit.commons.restClients.inventoryService.service.api;

import com.intuit.commons.restClients.inventoryService.request.InventoryRequestDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InventoryApi {

    @POST("/orderManagement/inventory/add")
    Call<Boolean> addInventory(@Body InventoryRequestDTO inventoryRequestDTO);

    @POST("/orderManagement/inventory/deduct")
    Call<Boolean> deductInventory(@Body InventoryRequestDTO inventoryRequestDTO);
}
