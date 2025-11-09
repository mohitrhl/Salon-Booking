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
    public Salon updateSalon(Long salonId, Salon salon, UserDTO user) throws Exception {
        Salon existingSalon = getSalonById(salonId);
        if (existingSalon!=null && salon.getOwnerId().equals(user.getId())) {

            existingSalon.setName(salon.getName());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setPhoneNumber(salon.getPhoneNumber());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setCity(salon.getCity());
            existingSalon.setOpen(salon.isOpen());
            existingSalon.setHomeService(salon.isHomeService());
            existingSalon.setActive(salon.isActive());
            existingSalon.setOpenTime(salon.getOpenTime());
            existingSalon.setCloseTime(salon.getCloseTime());

            return salonRepo.save(existingSalon);
        }
        throw new Exception("salon not exist");
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
