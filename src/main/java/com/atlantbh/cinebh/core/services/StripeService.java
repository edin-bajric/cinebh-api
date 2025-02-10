package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    private Stripe stripe;

    @Value("${STRIPE_API_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        stripe.apiKey = secretKey;
    }

    public ResponseEntity<?> makePaymentUsingCard(String paymentMethodId, long amount) {
        Stripe.apiKey = secretKey;

        try {
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setAmount(amount)
                    .setCurrency("bam")
                    .setPaymentMethod(paymentMethodId)
                    .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.AUTOMATIC)
                    .setConfirm(true)
                    .setReturnUrl("http://localhost:5173/buy-ticket-payment")
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(createParams);

            // Return a JSON response
            Map<String, String> response = new HashMap<>();
            response.put("message", "Payment successful");
            response.put("paymentIntentId", paymentIntent.getId());
            return ResponseEntity.ok().body(response);
        } catch (StripeException e) {
            // Return a JSON error response
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    public ResponseEntity<?> charge(PaymentRequest paymentRequest) {
        Stripe.apiKey = secretKey;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentRequest.getAmount());
        chargeParams.put("currency", paymentRequest.getCurrency());
        chargeParams.put("description", paymentRequest.getDescription());
        chargeParams.put("source", paymentRequest.getSource());

        try {
            Charge charge = Charge.create(chargeParams);

            return ResponseEntity.ok().body("Payment successful with charge ID: " + charge.getId());
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
