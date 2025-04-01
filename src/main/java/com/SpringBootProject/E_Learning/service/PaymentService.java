package com.SpringBootProject.E_Learning.service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${stripe.api.secret}")
    private String stripeApiKey;

    public String checkoutSession(String courseId,double price) throws StripeException {
        Stripe.apiKey=stripeApiKey;
        SessionCreateParams params=SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/student/payment/success?courseId=" + courseId)
                .setCancelUrl("http://localhost:8080/student/payment/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("INR")
                                                .setUnitAmount((long) price*100)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Course: "+courseId)
                                                                .build()

                                                ).build()

                                ).build()
                ).build();

        Session session=Session.create(params);
        return session.getUrl();
    }

}
