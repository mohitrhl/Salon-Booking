package com.microservice.service.clients;


import com.microservice.domain.PaymentMethod;
import com.microservice.modal.Booking;
import com.microservice.payload.response.PaymentLinkResponse;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PAYMENT-SERVICE")
public interface PaymentFeignClient {

    @PostMapping("/api/payments/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Booking booking,
            @RequestParam PaymentMethod paymentMethod) throws ExecutionControl.UserException;
}
