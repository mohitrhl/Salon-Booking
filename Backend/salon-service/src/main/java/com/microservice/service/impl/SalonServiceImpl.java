package com.microservice.service.impl;

import com.microservice.modal.Salon;
import com.microservice.payload.dto.SalonDTO;
import com.microservice.payload.dto.UserDTO;
import com.microservice.repository.SalonRepo;
import com.microservice.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepo salonRepo;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon=new Salon();
        salon.setName(req.getName());
        salon.setImages(req.getImages());
        salon.setCity(req.getCity());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setPhoneNumber(req.getPhoneNumber());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        salon.setHomeService(true);
        salon.setOpen(true);
        salon.setOwnerId(user.getId());
        salon.setActive(true);

        return salonRepo.save(salon);
    }

    @Override
    public Salon updateSalon(Long salonId, Salon salonRequest, UserDTO user) throws Exception {
        // 1️⃣ Fetch existing salon
        Salon existingSalon = getSalonById(salonId);
        if (existingSalon == null) {
            throw new Exception("Salon not found with ID: " + salonId);
        }

        // 2️⃣ Check ownership
        if (existingSalon.getOwnerId() == null || !existingSalon.getOwnerId().equals(user.getId())) {
            throw new Exception("Unauthorized to update this salon");
        }

        // 3️⃣ Update only non-null fields
        if (salonRequest.getName() != null)
            existingSalon.setName(salonRequest.getName());
        if (salonRequest.getAddress() != null)
            existingSalon.setAddress(salonRequest.getAddress());
        if (salonRequest.getPhoneNumber() != null)
            existingSalon.setPhoneNumber(salonRequest.getPhoneNumber());
        if (salonRequest.getEmail() != null)
            existingSalon.setEmail(salonRequest.getEmail());
        if (salonRequest.getCity() != null)
            existingSalon.setCity(salonRequest.getCity());
        if (salonRequest.getOpenTime() != null)
            existingSalon.setOpenTime(salonRequest.getOpenTime());
        if (salonRequest.getCloseTime() != null)
            existingSalon.setCloseTime(salonRequest.getCloseTime());

        // Boolean fields: if present in request, apply update
        existingSalon.setOpen(salonRequest.isOpen());
        existingSalon.setHomeService(salonRequest.isHomeService());
        existingSalon.setActive(salonRequest.isActive());

        // 4️⃣ Save
        return salonRepo.save(existingSalon);
    }


    @Override
    public List<Salon> getAllSalons() {
        return salonRepo.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) {
        return salonRepo.findById(salonId).orElse(null);
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepo.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepo.searchSalons(city);
    }
}
