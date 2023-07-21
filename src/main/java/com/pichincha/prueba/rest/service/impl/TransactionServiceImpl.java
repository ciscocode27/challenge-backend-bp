package com.pichincha.prueba.rest.service.impl;


import com.pichincha.prueba.rest.Entity.Account;
import com.pichincha.prueba.rest.Entity.Client;
import com.pichincha.prueba.rest.Entity.Transaction;
import com.pichincha.prueba.rest.Utils.Classes.Enums.TransactionType;
import com.pichincha.prueba.rest.dao.TransactionDao;
import com.pichincha.prueba.rest.service.AccountService;
import com.pichincha.prueba.rest.service.ClientService;
import com.pichincha.prueba.rest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;

    @Override
    public List<Transaction> getAll() {
        return transactionDao.findAll();
    }

    @Override
    public Transaction  getById(int id)  {
        return transactionDao.findById(id);
    }

    @Override
    public Transaction save( Transaction transaction ) throws Exception {
        verifyValidAmount(transaction);
        BigDecimal lastBalance = getLastBalance( transaction.getAccount().getId() );
        BigDecimal actualBalance = sumAmountToBalance(transaction, lastBalance);
        transaction.setBalance(actualBalance);

        if(transaction.getType() == TransactionType.DEBITO)
            transaction.setAmount( transaction.getAmount().multiply(new BigDecimal(-1)) );


        return getCompleteObject(transactionDao.save(transaction));
    }

    @Override
    public void verifyValidAmount(Transaction transaction) throws Exception{
        if(transaction.getAmount().compareTo(BigDecimal.ZERO) < 0) throw new Exception("Saldo invalido");
    }

    @Override
    public Transaction update( Transaction transaction ) throws Exception {
        verifyIfExists(transaction.getId());
        verifyValidAmount(transaction);
        verifyCanEdit(transaction);
        BigDecimal nuevoBalance = recalculateBalance(transaction);
        transaction.setBalance( nuevoBalance );

        return getCompleteObject( transactionDao.save(transaction) );
    }

    @Override
    public void verifyIfExists( int id ) throws Exception {
        if( getById(id) == null ) {
            throw new Exception("Transacción " + id + " no existe");
        }
    }

    @Override
    public void verifyCanEdit( Transaction transaction) throws Exception {

        Transaction lastTransaction = transactionDao.getLastTransaction( transaction.getAccount().getId() );
        if( lastTransaction.getId() !=  transaction.getId()) {
            throw new Exception("Solo puede editar la última transacción");
        }

    }

    @Override
    public void deleteById(int id) throws Exception {
        verifyIfExists(id);
        transactionDao.deleteById(id);
    }

    @Override
    public BigDecimal getLastBalance( int accountId ) throws Exception {
        accountService.verifyIfExists(accountId);
        BigDecimal lastBalance = transactionDao.getLastBalance(accountId);
        if(lastBalance != null) return lastBalance;
        Account account = accountService.getById(accountId);
        return account.getBalance();
    }

    @Override
    public BigDecimal sumAmountToBalance( Transaction transaction, BigDecimal lastBalance ) throws Exception {

        BigDecimal actualBalance = BigDecimal.ZERO;
        if(transaction.getType() == TransactionType.CREDITO ) return lastBalance.add( transaction.getAmount() );
        else if( transaction.getType() ==  TransactionType.DEBITO ) {
            actualBalance = lastBalance.subtract( transaction.getAmount() );
            if( actualBalance.compareTo(BigDecimal.ZERO) < 0 )
                throw new Exception("SALDO NO DISPONIBLE");
            return actualBalance;
        }
        throw new Exception("El tipo de transacción es invalido");
    }

    @Override
    public Transaction getCompleteObject(Transaction transaction) {

        Account account = accountService.getById( transaction.getAccount().getId() );
        Client client = clientService.getById( account.getClient().getId() );

        account.setClient(client);
        transaction.setAccount(account);
        return transaction;
    }

    @Override
    public BigDecimal recalculateBalance(Transaction transaction) throws Exception {

        Transaction actualTransaction = getById( transaction.getId() );

        if( actualTransaction.getAmount().compareTo( transaction.getAmount() ) == 0 ) return transaction.getBalance();

        BigDecimal balance = actualTransaction.getBalance();
        if(transaction.getType() == TransactionType.CREDITO  && actualTransaction.getType() ==  TransactionType.CREDITO ) {
            return balance = balance.add(transaction.getAmount().subtract(actualTransaction.getAmount()));
        }
        else if( transaction.getType() == TransactionType.CREDITO  && actualTransaction.getType() == TransactionType.DEBITO ) {
            return balance = balance.add( transaction.getAmount().add(actualTransaction.getAmount()) );
        }
        else if( transaction.getType() == TransactionType.DEBITO &&  actualTransaction.getType() ==  TransactionType.DEBITO ) {
            balance = balance.subtract(transaction.getAmount().subtract(actualTransaction.getAmount().abs()));
            if( balance.compareTo(BigDecimal.ZERO) < 0 ) {
                throw new Exception("Saldo no disponible");
            }
            return balance;
        }
        else if( transaction.getType() ==  TransactionType.DEBITO &&  actualTransaction.getType() == TransactionType.CREDITO ) {
            balance = balance.subtract( transaction.getAmount().add(actualTransaction.getAmount()) );
            if(balance.compareTo(BigDecimal.ZERO) < 0) {
                throw new Exception("Saldo no disponible");
            }
            return balance;
        }
        throw new Exception("Tipo de transacción invalido");
    }


}
