package com.microservice.modal;

import com.microservice.domain.PaymentMethod;
import com.microservice.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    private Long userId;

    private Long bookingId;

    @Column(nullable = false)
    private Long salonId;
}
