package com.astella.api.controller;


import com.astella.api.model.Client;
import com.astella.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/client/{id}")
    Client getById(@PathVariable("id") final long id)
    {
        return clientService.getId(id).orElse(null);
    }


}
