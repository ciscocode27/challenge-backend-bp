package com.pichincha.prueba.rest.service.impl;

import com.pichincha.prueba.rest.Entity.Account;
import com.pichincha.prueba.rest.dao.AccountDao;
import com.pichincha.prueba.rest.service.AccountService;
import com.pichincha.prueba.rest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    ClientService clientService;

    /*public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }*/

    @Override
    public List<Account> getAll() {
        return accountDao.findAll();
    }

    @Override
    public Account getById(int id) {
        return accountDao.findById(id);
    }


    @Override
    public Account save(Account account) throws Exception {
        clientService.verifyIfClientExists( account.getClient().getId() );
        verifyFields(account);
        verifyIfNumberIsValid(account.getNumber());
        return accountDao.save(account);
    }

    @Override
    public Account update(Account account) throws Exception {
        verifyIfExists( account.getId() );
        Account actualAccount = getById(account.getId());
        if(!actualAccount.getNumber().equals( account.getNumber() )) {
            verifyIfNumberIsValid(account.getNumber());
        }
        verifyFields(account);
        if(actualAccount.getTransactions().size() != 0 && account.getBalance().compareTo(actualAccount.getBalance()) != 0) {
             throw new Exception("NO SE PUEDE EDITAR EL SALDO, LA CUENTA TIENE TRANSACCIONES");
        }
        return accountDao.save(account);
    }

    @Override
    public void deleteById(int id) throws Exception {
        verifyIfExists(id);
        //verifyDontHaveTransactions(id);
        accountDao.deleteById(id);
    }

    @Override
    public void verifyIfExists(int id) throws Exception {

        if( getById(id) == null ) throw new Exception("La cuenta "+ id + " no existe");

    }

    public void verifyFields(Account account) throws Exception{
        if(account.getNumber() < 0) throw new Exception("El número de cuenta es requerido o su valor no es valido");
        if(account.getBalance().compareTo(BigDecimal.ZERO) < 0) throw new Exception("El saldo inicial de cuenta es requerido o su valor no es valido");

    }

    /*@Override
    public void verifyDontHaveTransactions(int id) throws Exception {
        if(transactionDao.getByAccountId(id).size() != 0) {
            throw new Exception("ACCOUNT " + id + " HAS TRANSACTIONS");
        }

    }*/

    @Override
    public void verifyIfNumberIsValid(Long number) throws Exception {

        if(accountDao.findByNumber(number) != null)
            throw new Exception("ACCOUNT NUMBER ALREADY EXISTS");

    }
}
