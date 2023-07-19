package com.pichincha.prueba.rest.service;

import com.pichincha.prueba.rest.Entity.Client;

import java.util.List;

public interface ClientService {

    public List<Client> getAll();
    public Client getById(int id);
    public Client save(Client client) throws Exception;
    public Client update(Client client) throws Exception;
    public void deleteById(int id) throws Exception;
}
