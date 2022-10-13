package com.intuit.commons.restClients.paymentService.service.api;

import com.intuit.commons.restClients.paymentService.request.DeductPaymentRequestDTO;
import com.intuit.commons.restClients.paymentService.request.RevertPaymentRequestDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PaymentApi {

    @POST("/orderManagement/payment/deduct")
    Call<Boolean> deductPayment(@Body DeductPaymentRequestDTO paymentRequestDTO);

    @POST("/orderManagement/payment/revert")
    Call<Boolean> revertPayment(@Body RevertPaymentRequestDTO paymentRequestDTO);
}
