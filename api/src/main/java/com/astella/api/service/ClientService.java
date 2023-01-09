package com.astella.api.service;

import com.astella.api.model.Client;
import com.astella.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Optional<Client> getId(Long id){
        return clientRepository.findById(id);
    }
}
