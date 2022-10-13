package com.intuit.payment.service;

import com.intuit.appUtility.dto.request.AddBalanceRequestDTO;
import com.intuit.appUtility.dto.request.DeductPaymentRequestDTO;
import com.intuit.appUtility.dto.request.RevertPaymentRequestDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class PaymentServiceImpl implements PaymentService{
    private Map<String, Double> userBalanceMap;
    private Map<Long, Double> orderAmountMap;


    @PostConstruct
    private void init(){
        this.userBalanceMap = new HashMap<>();
        this.orderAmountMap=new HashMap<>();
    }

    @Override
    public CompletionStage<Boolean> addBalance(AddBalanceRequestDTO requestDTO) {
        double balance = this.userBalanceMap.getOrDefault(requestDTO.getUserId(), 0d);
        this.userBalanceMap.put(requestDTO.getUserId(), balance + requestDTO.getAmount());
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletionStage<Boolean> revertPaymentDeduction(RevertPaymentRequestDTO requestDTO) {
        if(orderAmountMap.containsKey(requestDTO.getOrderId()))
        {
            double balance = this.userBalanceMap.getOrDefault(requestDTO.getUserId(), 0d);
            this.userBalanceMap.put(requestDTO.getUserId(), balance + orderAmountMap.get(requestDTO.getOrderId()));
            this.orderAmountMap.remove(requestDTO.getOrderId());
        }
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletionStage<Boolean> deductPayment(DeductPaymentRequestDTO requestDTO) {
        if(!orderAmountMap.containsKey(requestDTO.getOrderId()))
        {
            double balance = this.userBalanceMap.getOrDefault(requestDTO.getUserId(), 0d);
            if(balance >= requestDTO.getAmount()){
                this.userBalanceMap.put(requestDTO.getUserId(), balance - requestDTO.getAmount());
                this.orderAmountMap.put(requestDTO.getOrderId(),requestDTO.getAmount());
                return CompletableFuture.completedFuture(true);
            }
            return CompletableFuture.completedFuture(false);
        }
        else
            return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletionStage<Double> getBalance(String userId) {
        return CompletableFuture.completedFuture(this.userBalanceMap.getOrDefault(userId, 0d));
    }
}
