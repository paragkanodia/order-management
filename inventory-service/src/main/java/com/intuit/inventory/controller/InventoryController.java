package com.intuit.inventory.controller;

import com.intuit.appUtility.dto.request.AddInventoryRequestDTO;
import com.intuit.appUtility.dto.request.DeductInventoryRequestDTO;
import com.intuit.appUtility.dto.request.RevertInventoryDeductionRequestDTO;
import com.intuit.inventory.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping("/deduct")
    @Operation(summary = "deducts inventory against an Order", description = "This API deducts inventory against an Order")
    public CompletionStage<Boolean> deduct(@RequestBody final DeductInventoryRequestDTO requestDTO){
        return this.service.deductInventory(requestDTO);
    }

    @PostMapping("/revert")
    @Operation(summary = "reverts deduction of inventory against an Order", description = "This API reverts deduction of inventory against an Order")
    public CompletionStage<Boolean> revert(@RequestBody final RevertInventoryDeductionRequestDTO requestDTO){
        return this.service.revertInventoryDeduction(requestDTO);
    }

    @PostMapping("/add")
    @Operation(summary = "reverts deduction of inventory against an Order", description = "This API reverts deduction of inventory against an Order")
    public CompletionStage<Boolean> add(@RequestBody final AddInventoryRequestDTO requestDTO){
        return this.service.addInventory(requestDTO);
    }

    @GetMapping("/{productCode}")
    @Operation(summary = "Returns existing inventory of product", description = "Returns existing inventory of product")
    public CompletionStage<Integer> getInventory(@PathVariable("productCode") final String productCode){
        return this.service.getInventory(productCode);
    }
}
