package com.pichincha.prueba.rest.service;

import com.pichincha.prueba.rest.Entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    public List<Transaction> getAll();
    public Transaction  getById(int id );
    public Transaction  save(Transaction transaction ) throws Exception;
    public Transaction  update(Transaction transaction ) throws Exception;
    public void deleteById(int id ) throws Exception;


    public void verifyValidAmount(Transaction transaction ) throws Exception;
    public void verifyIfExists(int id ) throws Exception;
    public BigDecimal getLastBalance(int accountId ) throws Exception;
    public BigDecimal sumAmountToBalance( Transaction transaction, BigDecimal lastBalance ) throws Exception;
    public Transaction getCompleteObject( Transaction transaction );
    public void verifyCanEdit( Transaction transaction ) throws Exception;
    public BigDecimal recalculateBalance( Transaction transaction ) throws Exception;

}
