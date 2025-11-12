package com.microservice.service;

import com.microservice.modal.ServiceOffering;
import com.microservice.payload.dto.CategoryDTO;
import com.microservice.payload.dto.SalonDTO;
import com.microservice.payload.dto.ServiceDTO;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(ServiceDTO service, SalonDTO salon, CategoryDTO category);

    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;

    Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId);

    ServiceOffering getServiceById(Long serviceId);

    Set<ServiceOffering> getServicesByIds(Set<Long> ids);
}

