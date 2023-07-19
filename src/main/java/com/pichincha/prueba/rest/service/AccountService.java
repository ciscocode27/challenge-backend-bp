package com.pichincha.prueba.rest.service;

import com.pichincha.prueba.rest.Entity.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAll();
    public Account getById(int id);
    public Account  save(Account account) throws Exception;
    public Account update(Account account) throws Exception;
    public void  deleteById(int id) throws Exception;

    public void  verifyIfExists(int id) throws Exception;
    public void  verifyFields(Account account) throws Exception;
    public void  verifyIfNumberIsValid(Long number) throws Exception;
    //public void verifyDontHaveTransactions(int id) throws Exception;
}
