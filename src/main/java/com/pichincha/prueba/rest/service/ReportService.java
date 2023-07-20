package com.pichincha.prueba.rest.service;


import com.pichincha.prueba.rest.Controller.ReportController;
import com.pichincha.prueba.rest.Entity.Transaction;
import com.pichincha.prueba.rest.dao.ReportDao;
import com.pichincha.prueba.rest.dao.TransactionDao;
import com.pichincha.prueba.rest.dto.ReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {


    Logger logger = LoggerFactory.getLogger(ReportService.class);
    @Autowired
    ReportDao reportDao;

    public List<ReportDto> getReporByClientBtwnDates(String startDate, String endDate,int clientId ) throws Exception{
        logger.info("clientId " + clientId);
        return reportDao.getReport(startDate, endDate, clientId);
    }

}
