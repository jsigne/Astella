package com.astella.api.service;

import com.astella.api.model.ServiceDelivery;
import com.astella.api.repository.ServiceDeliveryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Data
@Service
public class ServiceDeliveryService {

    @Autowired
    private ServiceDeliveryRepository serviceDeliveryRepository;

    public ServiceDelivery saveService(ServiceDelivery service){
        return serviceDeliveryRepository.save(service);
    }

    public Optional<ServiceDelivery> getService(final Long id){
        return serviceDeliveryRepository.findById(id);
    }

    public Iterable<ServiceDelivery> getServices(){
        return serviceDeliveryRepository.findAll();
    }

    public void deleteService(final Long id){
        serviceDeliveryRepository.deleteById(id);
    }

    public void deleteServices(){
        serviceDeliveryRepository.deleteAll();
    }

}
