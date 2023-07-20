package com.pichincha.prueba.rest.dao;

import com.pichincha.prueba.rest.Entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Long> {


    public List<Transaction> findAll();
    public Transaction findById(int id);
    public Transaction  save(Transaction transaction);
    public void  deleteById(int id);

    @Query(value = "SELECT balance FROM transactions\r\n"
            + "where account_id = :accountId \r\n"
            + "order by date desc\r\n"
            + "limit 1;", nativeQuery = true)
    public BigDecimal getLastBalance( @Param("accountId") int accountId );

    @Query(value = "SELECT * FROM transactions\r\n"
            + "where account_id = :accountId ; ", nativeQuery = true)
    public List<Transaction> getByAccountId(@Param("accountId") int accountId);

    @Query(value = "SELECT * FROM transactions \r\n"
            + "where account_id = :accountId \n "
            + "order by date desc limit 1", nativeQuery = true)
    public Transaction getLastTransaction(@Param("accountId") int accountId);


}
