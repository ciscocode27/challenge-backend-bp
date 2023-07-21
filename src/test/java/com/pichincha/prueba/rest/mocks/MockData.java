package com.pichincha.prueba.rest.mocks;

import com.pichincha.prueba.rest.Entity.Account;
import com.pichincha.prueba.rest.Entity.Client;
import com.pichincha.prueba.rest.Entity.Transaction;
import com.pichincha.prueba.rest.Utils.Classes.Enums.AccountType;
import com.pichincha.prueba.rest.Utils.Classes.Enums.Gender;
import com.pichincha.prueba.rest.Utils.Classes.Enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public  static List<Account> getAllAccounts(){
        Account account1 = new Account(1, 957846L, AccountType.AHORROS,
                BigDecimal.ZERO, true, new Client(), new ArrayList<>());
        Account account2 = new Account(2, 105482L, AccountType.CORRIENTE,
                BigDecimal.ZERO, true, new Client(), new ArrayList<>());
        List<Account> accounts = Arrays.asList(account1, account2);
        return accounts;
    }

    public static Account getAccount(){
        return new Account(1, 957846L, AccountType.AHORROS,
                new BigDecimal(550), true, new Client(), new ArrayList<>());
    }


    public static List<Transaction> getAllTransactions(){
        Transaction transaction1 = new Transaction(1, LocalDateTime.now(), TransactionType.CREDITO,
                new BigDecimal(100), new BigDecimal(100), new Account());
        Transaction transaction2 = new Transaction(2, LocalDateTime.now(), TransactionType.DEBITO,
                new BigDecimal(100), new BigDecimal(0), new Account());
        return Arrays.asList(transaction1, transaction2);
    }

    public static Transaction getTransaction(){
        Account account = new Account(1, 957846L, AccountType.AHORROS,
                new BigDecimal(550), true, new Client(), new ArrayList<>());
        return new Transaction(1, LocalDateTime.now(), TransactionType.CREDITO,
                new BigDecimal(100), new BigDecimal(50),account);

    }

    public static Transaction getTransactionDebito(){
        Account account = new Account(1, 957846L, AccountType.AHORROS,
                new BigDecimal(550), true, new Client(), new ArrayList<>());
        return new Transaction(1, LocalDateTime.now(), TransactionType.DEBITO,
                new BigDecimal(100), new BigDecimal(50),account);

    }

}
