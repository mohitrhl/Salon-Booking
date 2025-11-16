package com.microservice.controller;

import com.microservice.domain.PaymentMethod;
import com.microservice.modal.PaymentOrder;
import com.microservice.payload.dto.BookingDTO;
import com.microservice.payload.dto.UserDTO;
import com.microservice.payload.response.PaymentLinkResponse;
import com.microservice.service.PaymentService;
import com.razorpay.RazorpayException;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDTO booking,
            @RequestParam PaymentMethod paymentMethod) throws
            RazorpayException, ExecutionControl.UserException {

        System.out.println("------"+booking);

        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setFullName("Mohit kumar");
        user.setEmail("mohitrhl001@gmail.com");

        PaymentLinkResponse paymentLinkResponse = paymentService
                .createOrder(user, booking, paymentMethod);

        return ResponseEntity.ok(paymentLinkResponse);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(
            @PathVariable Long paymentOrderId) {
        try {
            PaymentOrder paymentOrder = paymentService.getPaymentOrderById(paymentOrderId);
            return ResponseEntity.ok(paymentOrder);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId) throws Exception {

        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);
        Boolean success = paymentService.ProceedPaymentOrder(
                paymentOrder,
                paymentId,
                paymentLinkId);
        return ResponseEntity.ok(success);

    }


}
