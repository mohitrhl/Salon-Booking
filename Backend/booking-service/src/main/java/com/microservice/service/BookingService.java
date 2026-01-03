package com.microservice.service;

import com.microservice.domain.BookingStatus;
import com.microservice.modal.Booking;
import com.microservice.modal.PaymentOrder;
import com.microservice.modal.SalonReport;
import com.microservice.payload.dto.SalonDTO;
import com.microservice.payload.dto.ServiceOfferingDTO;
import com.microservice.payload.dto.UserDTO;
import com.microservice.payload.request.BookingRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {


    Booking createBooking(
            BookingRequest booking,
            UserDTO user,
            SalonDTO salon,
            Set<ServiceOfferingDTO> serviceOfferingSet) throws Exception;


    List<Booking> getBookingsByCustomer(Long customerId);


    List<Booking> getBookingsBySalon(Long salonId);


    Booking getBookingById(Long bookingId);

    Booking bookingSuccess(PaymentOrder order);


    Booking updateBookingStatus(Long bookingId, BookingStatus status) throws Exception;

    SalonReport getSalonReport(Long salonId);

    List<Booking> getBookingsByDate(LocalDate date,Long salonId);
}
