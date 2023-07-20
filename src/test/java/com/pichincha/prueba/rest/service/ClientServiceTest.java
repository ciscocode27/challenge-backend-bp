package com.pichincha.prueba.rest.service;


import com.pichincha.prueba.rest.Entity.Client;
import com.pichincha.prueba.rest.Utils.Classes.Enums.Gender;
import com.pichincha.prueba.rest.dao.AccountDao;
import com.pichincha.prueba.rest.dao.ClientDao;
import com.pichincha.prueba.rest.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static com.pichincha.prueba.rest.mocks.MockData.getAllClients;
import static com.pichincha.prueba.rest.mocks.MockData.getClientCreateResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    ClientServiceImpl clientService;
    @MockBean
    ClientDao clientDao;
    @MockBean
    AccountDao accountDao;


    @Test
    void wouldCreateClient() throws Exception {

        Client clientCreate = getClientCreateResponse();

        Client clientResponse = new Client(1, "SofíaZapata",Gender.FEMENINO,30,"095648726",
                "Rosales","286922","123456", true, new ArrayList());

        when( clientDao.save( any()) ).thenReturn(clientResponse);

        assertEquals(clientService.save(clientCreate) , clientResponse);

        verify(clientDao, times(1)).save(clientCreate);
    }

    @Test
    void wouldReturnMoreThanOneClient(){
        List<Client> clients = getAllClients();
        when( clientDao.findAll()).thenReturn(clients);
        assertTrue(clientService.getAll().size() > 0 );
    }

    @Test
    void wouldReturnClientById(){
        when( clientDao.findById(1)).thenReturn(getClientCreateResponse());
        Client client = clientService.getById(1);
        assertSame( client.getAge(), 30  );
    }


    @Test
    void wouldUpdateClient() throws Exception {
        Client client = getClientCreateResponse();
        when(clientDao.save( any()) ).thenReturn(client);
        when( clientDao.findById(1)).thenReturn(getClientCreateResponse());
        assertEquals(client, clientService.update(client));
        verify(clientDao, times(1)).save(client);
    }

    @Test
    void wouldUpdateErrorClient() throws Exception {
        Client client = getClientCreateResponse();
        client.setPassword("123");
        when(clientDao.save( any()) ).thenReturn(client);
        when( clientDao.findById(1)).thenReturn(getClientCreateResponse());
        Exception thrown = assertThrows(
                Exception.class,
                () -> clientService.update(client),
                "Debe retornar excepcion"
        );
        assertTrue(thrown.getMessage().contains("El campo Password requiere seis caracteres"));
        verify(clientDao, times(0)).save(client);
    }

    @Test
    void wouldDeleteClient() throws Exception {
        doNothing().when(clientDao).deleteById(1);
        when( clientDao.findById(1)).thenReturn(getClientCreateResponse());
        clientService.deleteById(1);
        verify(clientDao, times(1)).deleteById(1);
    }




}
