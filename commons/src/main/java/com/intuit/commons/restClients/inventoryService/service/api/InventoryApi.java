package com.intuit.commons.restClients.inventoryService.service.api;

import com.intuit.commons.restClients.inventoryService.request.DeductInventoryRequestDTO;
import com.intuit.commons.restClients.inventoryService.request.RevertInventoryDeductionRequestDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InventoryApi {

    @POST("/orderManagement/inventory/revert")
    Call<Boolean> revertInventoryDeduction(@Body RevertInventoryDeductionRequestDTO inventoryRequestDTO);

    @POST("/orderManagement/inventory/deduct")
    Call<Boolean> deductInventory(@Body DeductInventoryRequestDTO inventoryRequestDTO);
}
