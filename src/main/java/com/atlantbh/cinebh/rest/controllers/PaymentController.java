package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.StripeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/")
    public ResponseEntity<?> processPayment(@RequestBody Map<String, Object> paymentDetails) {
        String paymentMethodId = (String) paymentDetails.get("paymentMethodId");
        long amount = Long.parseLong(paymentDetails.get("amount").toString());

        return stripeService.makePaymentUsingCard(paymentMethodId, amount);
    }
}