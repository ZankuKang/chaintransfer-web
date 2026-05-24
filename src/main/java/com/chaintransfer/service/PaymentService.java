package com.chaintransfer.service;

import com.chaintransfer.grpc.MutinyPaymentGrpc;
import com.chaintransfer.grpc.PaymentRequest;
import com.chaintransfer.grpc.PaymentResponse;
import io.quarkus.grpc.GrpcClient;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentService {

    @GrpcClient("chaintransfer-pay")
    MutinyPaymentGrpc.MutinyPaymentStub paymentClient;

    public PaymentResponse processPayment(
            String userId,
            long amount,
            String currency,
            String nonce
    ) {
        PaymentRequest request = PaymentRequest.newBuilder()
                .setUserId(userId)
                .setAmount(amount)
                .setCurrency(currency)
                .setNonce(nonce)
                .build();

        return paymentClient.processPayment(request)
                .await().indefinitely();
    }
}