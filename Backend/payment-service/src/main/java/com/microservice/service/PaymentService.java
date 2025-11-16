package com.microservice.service;

import com.microservice.domain.PaymentMethod;
import com.microservice.modal.PaymentOrder;
import com.microservice.payload.dto.BookingDTO;
import com.microservice.payload.dto.UserDTO;
import com.microservice.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import jdk.jshell.spi.ExecutionControl;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO user,
                                    BookingDTO booking, PaymentMethod paymentMethod) throws RazorpayException, ExecutionControl.UserException;

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception;

    Boolean ProceedPaymentOrder (PaymentOrder paymentOrder,
                                 String paymentId, String paymentLinkId) throws RazorpayException;

    PaymentLink createRazorpayPaymentLink(UserDTO user,
                                          Long Amount,
                                          Long orderId) throws RazorpayException;

}
