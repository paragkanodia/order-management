package com.intuit.order.orderOrchestrator;

import com.intuit.commons.restClients.inventoryService.request.InventoryRequestDTO;
import com.intuit.commons.restClients.inventoryService.service.InventoryService;
import com.intuit.commons.restClients.paymentService.request.PaymentRequestDTO;
import com.intuit.commons.restClients.paymentService.service.PaymentService;
import com.intuit.order.model.OrderConfirmedEvent;
import com.intuit.order.model.OrderCreatedEvent;
import com.intuit.order.model.OrderFailedEvent;
import com.intuit.order.orderOrchestrator.steps.InventoryStep;
import com.intuit.order.orderOrchestrator.steps.PaymentStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderOrchestrator {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private KafkaTemplate<String, Object> template;

    private String orderConfirmedTopic = "ORDER_CONFIRMED";
    private String orderFailedTopic = "ORDER_FAILED";


    @KafkaListener(topics = "ORDER_CREATED", groupId = "mygroup")
    public void consumeOrderMessage(OrderCreatedEvent message)
    {
        Workflow orderWorkflow = this.getOrderWorkflow(message);

        Optional<Boolean> first = orderWorkflow.getSteps().stream()
                .map(workflowStep -> workflowStep.process())
                .filter(result -> result.equals(false))
                .findFirst();

        if(first.isPresent())
        {
            this.revertOrderWorkFlow(orderWorkflow, message);
            return;
        }
        sendOrderConfirmedEvent(message);
    }

    private void sendOrderFailedEvent(OrderCreatedEvent message){
        OrderFailedEvent orderFailedEvent=OrderFailedEvent.builder()
                .orderNo(message.getOrderNo())
                .build();

        template.send(orderFailedTopic,orderFailedEvent);
    }

    private void sendOrderConfirmedEvent(OrderCreatedEvent message){
        OrderConfirmedEvent orderConfirmedEvent=OrderConfirmedEvent.builder()
                .orderNo(message.getOrderNo())
                .inventoryNo("23")
                .paymentNo("23")
                .build();

        template.send(orderConfirmedTopic,orderConfirmedEvent);
    }

    private Workflow getOrderWorkflow(OrderCreatedEvent requestDTO){
        WorkflowStep paymentStep = new PaymentStep(this.paymentService, this.getPaymentRequestDTO(requestDTO));
        WorkflowStep inventoryStep = new InventoryStep(this.inventoryService, this.getInventoryRequestDTO(requestDTO));
        return new OrderWorkflow(Arrays.asList(paymentStep, inventoryStep));
    }

    private Void revertOrderWorkFlow(Workflow orderWorkflow, OrderCreatedEvent message){
        List<Boolean> collect = orderWorkflow.getSteps().stream()
                .filter(workflowStep -> workflowStep.getStatus().equals(WorkflowStepStatus.COMPLETE))
                .map(workflowStep -> workflowStep.revert())
                .collect(Collectors.toList());

        sendOrderFailedEvent(message);
//        CompletableFuture.allOf((CompletableFuture<?>) cfList)
//                .exceptionally(ex->revertOrderWorkFlow(orderWorkflow));
        return null;
    }

    private PaymentRequestDTO getPaymentRequestDTO(OrderCreatedEvent requestDTO){
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setOrderId(requestDTO.getOrderNo());
        paymentRequestDTO.setUserId(requestDTO.getUserId());
        paymentRequestDTO.setAmount(requestDTO.getPrice()*requestDTO.getQuantity());
        return paymentRequestDTO;
    }

    private InventoryRequestDTO getInventoryRequestDTO(OrderCreatedEvent requestDTO){
        InventoryRequestDTO inventoryRequestDTO = new InventoryRequestDTO();
        inventoryRequestDTO.setOrderId(requestDTO.getOrderNo());
        inventoryRequestDTO.setProductCode(requestDTO.getProductCode());
        inventoryRequestDTO.setQuantity(requestDTO.getQuantity());
        return inventoryRequestDTO;
    }
}
