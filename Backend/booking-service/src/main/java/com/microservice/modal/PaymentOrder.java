package com.microservice.modal;


import com.microservice.domain.PaymentMethod;
import com.microservice.domain.PaymentOrderStatus;
import lombok.Data;

@Data
public class PaymentOrder {
    private Long id;

    private Long amount;

    private PaymentOrderStatus status;

    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    private Long userId;

    private Long bookingId;
}
