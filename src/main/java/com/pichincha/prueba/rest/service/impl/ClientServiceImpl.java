package com.pichincha.prueba.rest.service.impl;

import com.pichincha.prueba.rest.Entity.Client;
import com.pichincha.prueba.rest.dao.ClientDao;
import com.pichincha.prueba.rest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientDao clientDao;

    @Override
    public List<Client> getAll() {
        return clientDao.findAll();
    }

    @Override
    public Client getById(int id) {
        return clientDao.findById(id);
    }

    @Override
    public Client save(Client client) throws Exception {
        validateFields(client);
        client.setState(true);
        return clientDao.save(client);
    }

    @Override
    public Client update(Client client) throws Exception {
        validateFields(client);
        verifyIfClientExists(client.getId());
        return clientDao.save(client);
    }

    @Override
    public void deleteById(int id) throws Exception {
        verifyIfClientExists(id);
        clientDao.deleteById(id);
    }

    private void validateFields(Client client) throws Exception{
        if( client.getPassword().isBlank()  )  throw new Exception("El campo Password es requerido o su valor no es valido");
        if( client.getName().length() == 0 ) throw new Exception("El campo Nombre es requerido o su valor no es valido");
        if( client.getAge() < 0 ) throw new Exception("El campo Edad es requerido o su valor no es valido");
        if( client.getDni().length() == 0 ) throw new Exception("El campo Dni es requerido o su valor no es valido");
        if( client.getAddress().length() == 0 ) throw new Exception("El campo Dirección es requerido o su valor no es valido");
        if( client.getPhone().length() == 0 ) throw new Exception("El campo Télefono es requerido o su valor no es valido");

        if( client.getPassword().length() != 6 )  throw new Exception("El campo Password requiere seis caracteres");

    }

    private void verifyIfClientExists(int id) throws Exception {

        if(getById(id) == null)
            throw new Exception("CLIENT " + id + " does not exist");
    }


}
