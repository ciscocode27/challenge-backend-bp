package com.pichincha.prueba.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Account extends CrudRepository<Account, Long> {

}
