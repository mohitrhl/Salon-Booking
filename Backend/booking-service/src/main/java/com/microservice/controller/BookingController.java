package com.microservice.controller;

import com.microservice.domain.BookingStatus;
import com.microservice.mapper.BookingMapper;
import com.microservice.modal.Booking;
import com.microservice.modal.SalonReport;
import com.microservice.payload.dto.*;
import com.microservice.payload.request.BookingRequest;
import com.microservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    public ResponseEntity<Booking> createBooking(
            @RequestParam Long salonId,
            @RequestBody BookingRequest bookingRequest
            ) throws Exception {

        UserDTO user = new UserDTO();
        user.setId(1L);

        SalonDTO salon = new SalonDTO();
        salon.setId(salonId);

        if(salon.getId()==null){
            throw new Exception("Salon not found");
        }

        Set<ServiceOfferingDTO> serviceDtoSet = new HashSet<>();

        ServiceOfferingDTO serviceDto = new ServiceOfferingDTO();
        serviceDto.setId(1L);
        serviceDto.setPrice(399);
        serviceDto.setDuration(45);
        serviceDto.setName("Hair cut for men");

        serviceDtoSet.add(serviceDto);

        Booking booking = bookingService.createBooking(bookingRequest,
                user,
                salon,
                serviceDtoSet);

        return ResponseEntity.ok(booking);

    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(){

        List<Booking> bookings = bookingService.getBookingsByCustomer(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingsBySalon(){

        List<Booking> bookings = bookingService.getBookingsBySalon(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    /**
     * Get a booking by its ID
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));

    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status) throws Exception {

        Booking updatedBooking = bookingService.updateBookingStatus(bookingId, status);

        return ResponseEntity.ok(BookingMapper.toDTO(updatedBooking));

    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookedSlotsDTO>> getBookedSlots(
            @PathVariable Long salonId,
            @PathVariable LocalDate date) throws Exception {

        List<Booking> bookings = bookingService.getBookingsByDate(date,salonId);

        List<BookedSlotsDTO> slotsDTOS = bookings.stream()
                .map(booking -> {
                    BookedSlotsDTO slotsDTO = new BookedSlotsDTO();
                    slotsDTO.setStartTime(booking.getStartTime());
                    slotsDTO.setEndTime(booking.getEndTime());
                    return slotsDTO;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(slotsDTOS);

    }

    /**
     * Get all bookings for a salon
     */
    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(
    ) throws Exception {

        SalonReport report = bookingService.getSalonReport(1L);

        return ResponseEntity.ok(report);

    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings) {
        return bookings.stream()
                .map(booking -> {
                    return BookingMapper.toDTO(booking);
                }).collect(Collectors.toSet());
    }



}
