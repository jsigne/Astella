package com.astella.api.repository;

import com.astella.api.model.ServiceDelivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDeliveryRepository extends CrudRepository<ServiceDelivery, Long>{
}
