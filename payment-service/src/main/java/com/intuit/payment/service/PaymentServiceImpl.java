package com.intuit.payment.service;

import com.intuit.appUtility.dto.request.PaymentRequestDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class PaymentServiceImpl implements PaymentService{
    private Map<String, Double> userBalanceMap;

    @PostConstruct
    private void init(){
        this.userBalanceMap = new HashMap<>();
        this.userBalanceMap.put("1", 1000d);
        this.userBalanceMap.put("2", 1000d);
        this.userBalanceMap.put("3", 1000d);
    }

    @Override
    public CompletionStage<Boolean> addPayment(PaymentRequestDTO requestDTO) {
        double balance = this.userBalanceMap.getOrDefault(requestDTO.getUserId(), 0d);
        this.userBalanceMap.put(requestDTO.getUserId(), balance + requestDTO.getAmount());
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletionStage<Boolean> deductPayment(PaymentRequestDTO requestDTO) {
        double balance = this.userBalanceMap.getOrDefault(requestDTO.getUserId(), 0d);
        if(balance >= requestDTO.getAmount()){
            this.userBalanceMap.put(requestDTO.getUserId(), balance - requestDTO.getAmount());
            return CompletableFuture.completedFuture(true);
        }
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public CompletionStage<Double> getBalance(String userId) {
        return CompletableFuture.completedFuture(this.userBalanceMap.getOrDefault(userId, 0d));
    }
}
