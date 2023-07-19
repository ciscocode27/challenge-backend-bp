package com.pichincha.prueba.rest.dao;

import com.pichincha.prueba.rest.Entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends CrudRepository<Client, Long> {

    public List<Client> findAll();
    public Client findById(int id);
    public Client save(Client client);
    public void deleteById(int id);

}
