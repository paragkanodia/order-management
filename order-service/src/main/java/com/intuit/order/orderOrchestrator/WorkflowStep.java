package com.intuit.order.orderOrchestrator;

public interface WorkflowStep {

    WorkflowStepStatus getStatus();
    Boolean process();
    Boolean revert();
}
