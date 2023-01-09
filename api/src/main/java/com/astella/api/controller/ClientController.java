package com.astella.api.controller;


import com.astella.api.model.Client;
import com.astella.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.Cleaner;


@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/client")
    public Client createClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @GetMapping("/client/{id}")
    public Client getById(@PathVariable("id") final long id)
    {
        return clientService.getClient(id).orElse(null);
    }

    @GetMapping("/clients")
    public Iterable<Client> getAll(){
        return clientService.getClients();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final long id){
        clientService.deleteClient(id);
    }

    @DeleteMapping("/delete")
    public void deleteAll(){
        clientService.deleteClients();
    }



}
