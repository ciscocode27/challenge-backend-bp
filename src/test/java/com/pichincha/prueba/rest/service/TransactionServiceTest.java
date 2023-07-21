package com.pichincha.prueba.rest.service;

import com.pichincha.prueba.rest.Entity.Account;
import com.pichincha.prueba.rest.Entity.Transaction;
import com.pichincha.prueba.rest.Utils.Classes.Enums.TransactionType;
import com.pichincha.prueba.rest.dao.AccountDao;
import com.pichincha.prueba.rest.dao.ClientDao;
import com.pichincha.prueba.rest.dao.TransactionDao;
import com.pichincha.prueba.rest.service.impl.TransactionServiceImpl;
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
public class TransactionServiceTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    TransactionDao transactionDao;

    @Mock
    ClientDao clientDao;

    @Mock
    AccountDao accountDao;

    @Mock
    AccountService accountService;

    @Mock
    ClientService clientService;


    @Test
    void wouldReturnMoreThanOneTransactions() {
        List<Transaction> transactions = getAllTransactions();
        when( transactionDao.findAll()).thenReturn(transactions);
        assertTrue(transactionService.getAll().size() > 0 );
    }

    @Test
    void wouldReturnTransactionById(){
        when( transactionDao.findById(1)).thenReturn(getTransaction());
        Transaction transaction = transactionService.getById(1);
        assertSame( transaction.getType() , TransactionType.CREDITO  );
    }

    @Test
    void wouldCreateTransactionCredito() throws Exception {
        Transaction transaction = getTransaction();
        Account account = getAccount();

        when(transactionDao.save( any() )).thenReturn(transaction);
        when(accountService.getById(1)).thenReturn(account);

        Transaction createTransaction = transactionService.save(transaction);
        assertEquals(transaction, createTransaction);
        verify(transactionDao, times(1)).save(transaction);
    }

    @Test
    void wouldCreateTransactionDebito() throws Exception {
        Transaction transaction = getTransactionDebito();
        Account account = getAccount();

        when(transactionDao.save( any() )).thenReturn(transaction);
        when(accountService.getById(1)).thenReturn(account);

        Transaction createTransaction = transactionService.save(transaction);
        assertEquals(transaction, createTransaction);
        verify(transactionDao, times(1)).save(transaction);
    }

    @Test
    void wouldUpdateTransaction() throws Exception {
        Transaction transaction = getTransaction();
        Account account = getAccount();

        when(transactionDao.save( any() )).thenReturn(transaction);
        when(accountService.getById(1)).thenReturn(account);
        when( transactionDao.findById(1)).thenReturn(getTransaction());
        when(transactionDao.getLastTransaction(1)).thenReturn(transaction);

        Transaction createTransaction = transactionService.update(transaction);
        assertEquals(transaction, createTransaction);
        verify(transactionDao, times(1)).save(transaction);
    }

    @Test
    void wouldDeleteTransaction() throws Exception {
        doNothing().when(transactionDao).deleteById(1);
        when( transactionDao.findById(1)).thenReturn(getTransaction());
        transactionService.deleteById(1);
        verify(transactionDao, times(1)).deleteById(1);
    }

}
