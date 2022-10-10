package com.intuit.order.orderOrchestrator.steps;

import com.intuit.commons.restClients.inventoryService.request.InventoryRequestDTO;
import com.intuit.commons.restClients.inventoryService.service.InventoryService;
import com.intuit.order.orderOrchestrator.WorkflowStep;
import com.intuit.order.orderOrchestrator.WorkflowStepStatus;

public class InventoryStep implements WorkflowStep {

    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;
    private InventoryService inventoryService;
    private InventoryRequestDTO requestDTO;

    public InventoryStep(InventoryService inventoryService, InventoryRequestDTO requestDTO) {
        this.inventoryService = inventoryService;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Boolean process() {
        Boolean result = inventoryService.addInventory(requestDTO).toCompletableFuture().join();
        if(result==false)
        {
            this.stepStatus=WorkflowStepStatus.FAILED;
            return false;
        }
        this.stepStatus=WorkflowStepStatus.COMPLETE;
        return true;
    }

    @Override
    public Boolean revert() {
        return inventoryService.deductInventory(requestDTO).toCompletableFuture().join();
    }
}
