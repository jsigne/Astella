package com.astella.api.controller;


import com.astella.api.model.Client;
import com.astella.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/client")
    public Client createClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @GetMapping("/client/{id}")
    public Client getById(@PathVariable("id") final Long id)
    {
        return clientService.getClient(id).orElse(null);
    }

    @GetMapping("/clients")
    public Iterable<Client> getAll(){
        return clientService.getClients();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Long id){
        clientService.deleteClient(id);
    }

    @DeleteMapping("/delete")
    public void deleteAll(){
        clientService.deleteClients();
    }

    @PutMapping("/client/{id}")
    public Client updateClient(@PathVariable("id") final Long id, @RequestBody Client clientUpdate){
        Optional<Client> client = clientService.getClient(id);

        if (client.isPresent()){
            Client currentClient = client.get();
            if (clientUpdate.getEmail() != null) {
                currentClient.setEmail(clientUpdate.getEmail());
            }
            if (clientUpdate.getName() != null) {
                currentClient.setName(clientUpdate.getName());
            }
            if (clientUpdate.getPhonenumber() != null) {
                currentClient.setPhonenumber(clientUpdate.getPhonenumber());
            }

            return clientService.saveClient(currentClient);
        }

        else return null;

    }

}
