package com.astella.api.controller;

import com.astella.api.model.ServiceDelivery;
import com.astella.api.service.ServiceDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
public class ServiceDeliveryController {

    @Autowired
    private ServiceDeliveryService serviceDeliveryService;

    /**
     * Create - Add a new ServiceDelivery
     * @param serviceDelivery An object ServiceDelivery
     * @return The service object saved
     */
    @PostMapping("/service")
    public ServiceDelivery createService(@RequestBody ServiceDelivery serviceDelivery){
        return serviceDeliveryService.saveService(serviceDelivery);
    }


    /**
     * Read - Get all services
     * @return - An Iterable object of ServiceDelivery full filled
     */
    @GetMapping("/services")
    public Iterable<ServiceDelivery> getServices(){
        return serviceDeliveryService.getServices();
    }


    /**
     * Read - Get one service
     * @param id The id of the service
     * @return An ServiceDelivery object full filled
     */
    @GetMapping("/service/{id}")
    public ServiceDelivery getService(@PathVariable("id") final Long id){
        Optional<ServiceDelivery> serviceDelivery = serviceDeliveryService.getService(id);
        return serviceDelivery.orElse(null);
    }

    @PutMapping("/updateService/{id}")
    public ServiceDelivery updateService(@PathVariable("id") final Long id, @RequestBody ServiceDelivery serviceDeliveryUpdate){
        Optional<ServiceDelivery> currentService = serviceDeliveryService.getService(id);

        if(currentService.isPresent()){
            ServiceDelivery service = currentService.get();
            if (serviceDeliveryUpdate.getName() != null){
                serviceDeliveryUpdate.setName(service.getName());
            }

            if (serviceDeliveryUpdate.getTime() != null){
                serviceDeliveryUpdate.setTime(service.getTime());
            }

            if (serviceDeliveryUpdate.getDescription() != null) {
                serviceDeliveryUpdate.setDescription(service.getDescription());
            }

            return serviceDeliveryService.saveService(service);

        }
        else {
            return null;
        }
    }

    /**
     * Delete - Delete a service
     * @param id The id of the service to delete
     */
    @DeleteMapping("/deleteService/{id}")
    public void deleteService(@PathVariable("id") final Long id){
        serviceDeliveryService.deleteService(id);
    }


}
