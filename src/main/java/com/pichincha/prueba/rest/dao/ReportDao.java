package com.pichincha.prueba.rest.dao;

import com.pichincha.prueba.rest.dto.ReportDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDao extends CrudRepository<ReportDto, Long>{

    @Query(value = "SELECT trx.id,trx.date fecha , clt.name cliente, ac.number numero_cuenta , ac.type tipo,ac.state estado,  (trx.balance - trx.amount ) as saldo_inicial ,trx.amount movimiento , trx.balance as saldo_disponible \r\n"
            + " FROM transactions trx, accounts ac,clients clt  \r\n"
            + "where clt.id = ac.client_id and ac.id = trx.account_id  and clt.id = :clientId and trx.date BETWEEN :startDate and :endDate \r\n"
            + "order by fecha asc;", nativeQuery = true)
    public List<ReportDto> getReport( @Param("startDate") String startDate,
                                      @Param("endDate") String endDate, @Param("clientId") int clientId);

}
