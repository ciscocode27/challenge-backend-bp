package com.pichincha.prueba.rest.service;


import com.pichincha.prueba.rest.Entity.Account;
import com.pichincha.prueba.rest.Entity.Client;
import com.pichincha.prueba.rest.Utils.Classes.Enums.AccountType;
import com.pichincha.prueba.rest.dao.AccountDao;
import com.pichincha.prueba.rest.dao.ClientDao;
import com.pichincha.prueba.rest.dao.TransactionDao;
import com.pichincha.prueba.rest.service.impl.AccountServiceImpl;
import com.pichincha.prueba.rest.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.pichincha.prueba.rest.mocks.MockData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountDao accountDao;
    @Mock
    ClientServiceImpl clientService;
    @Mock
    ClientDao clientDao;
    @Mock
    TransactionDao transactionDao;

    @Test
    void wouldReturnMoreThanOneAccount() {
        List<Account> account = getAllAccounts();
        when( accountDao.findAll()).thenReturn(account);
        assertTrue(accountService.getAll().size() > 0 );
    }

    @Test
    void wouldReturnAccountById(){
        when( accountDao.findById(1)).thenReturn(getAccount());
        Account account = accountService.getById(1);
        assertSame( account.getType() , AccountType.AHORROS  );
    }

    @Test
    void wouldCreateAccount() throws Exception {
        Account account = getAccount();
        when(accountDao.save( any() )).thenReturn(account);

        Account createAccount = accountService.save(account);
        assertEquals(account, createAccount);
        verify(accountDao, times(1)).save(account);
    }

    @Test
    void wouldUpdateAccount() throws Exception {
        Account account = getAccount();
        when(accountDao.save(any() )).thenReturn(account);
        when( accountDao.findById(1)).thenReturn(getAccount());
        Account updateAccount = accountService.update(account);
        assertEquals( account, updateAccount);
        verify(accountDao, times(1)).save(account);
    }

    @Test
    void wouldDeleteAccount() throws Exception {
        doNothing().when(accountDao).deleteById(1);
        when( accountDao.findById(1)).thenReturn(getAccount());
        accountService.deleteById(1);
        verify(accountDao, times(1)).deleteById(1);
    }

}
