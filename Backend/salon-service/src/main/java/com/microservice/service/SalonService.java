package com.microservice.service;

import com.microservice.modal.Salon;
import com.microservice.payload.dto.SalonDTO;
import com.microservice.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {

    Salon createSalon(SalonDTO salon, UserDTO user);

    Salon updateSalon(Long salonId, Salon salon, UserDTO user) throws Exception;

    List<Salon> getAllSalons();

    Salon getSalonById(Long salonId);

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCity(String city);
}
