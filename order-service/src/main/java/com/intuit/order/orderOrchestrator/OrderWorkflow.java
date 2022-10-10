package com.intuit.order.orderOrchestrator;

import com.intuit.order.orderOrchestrator.steps.InventoryStep;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OrderWorkflow implements Workflow {

    private final List<WorkflowStep> steps;

    public OrderWorkflow(List<WorkflowStep> steps) {
        this.steps = steps;
    }

    @Override
    public List<WorkflowStep> getSteps() {
        return this.steps;
    }

}
