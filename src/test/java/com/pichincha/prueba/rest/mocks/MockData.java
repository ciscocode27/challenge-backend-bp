package com.pichincha.prueba.rest.mocks;

import com.pichincha.prueba.rest.Entity.Client;
import com.pichincha.prueba.rest.Utils.Classes.Enums.Gender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static Client getClientCreateResponse(){
        return new Client(1, "SofíaZapata", Gender.FEMENINO,30,"095648726",
                "Rosales","286922","123456", true, new ArrayList());
    }

    public  static List<Client> getAllClients(){
        Client client1 = new Client(1, "Sofía Zapata", Gender.FEMENINO,30,"095648726",
                "Rosales","286922","123456", true, new ArrayList());

        Client client2 = new Client(2, "Juan Lopez", Gender.MASCULINO,80,"095648726",
                "Rosales","286922","123456", true, new ArrayList());
        List<Client> clients = Arrays.asList(client1, client2);
        return clients;
    }
}
