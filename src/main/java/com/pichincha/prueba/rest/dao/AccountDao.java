package com.pichincha.prueba.rest.dao;

import com.pichincha.prueba.rest.Entity.Account;
import com.pichincha.prueba.rest.Entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends CrudRepository<Account, Long> {

    public List<Account>  findAll();
    public Account  findById(int id);
    public Account  findByNumber(Long number);
    public List<Account>  findByClient(Client client);
    public Account  save(Account account);
    public void deleteById(int id);

    @Query(value = "SELECT * FROM accounts\r\n"
            + "where client_id = :clientId ; ", nativeQuery = true)
    public List<Account> getByClientId(@Param("clientId") int id);
}
