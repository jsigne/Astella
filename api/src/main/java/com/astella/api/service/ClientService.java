package com.astella.api.service;

import com.astella.api.model.Client;
import com.astella.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public Optional<Client> getClient(Long id){
        return clientRepository.findById(id);
    }

    public Iterable<Client> getClients(){
        return clientRepository.findAll();
    }

    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }

    public void deleteClients(){
        clientRepository. deleteAll();
    }

}
