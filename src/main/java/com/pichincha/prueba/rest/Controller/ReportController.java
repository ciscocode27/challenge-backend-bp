package com.pichincha.prueba.rest.Controller;


import com.pichincha.prueba.rest.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/reports")
public class ReportController {

    Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;

    @GetMapping("")
    public ResponseEntity getReport( @RequestParam String startDate, @RequestParam String endDate, @RequestParam int clientId) {
        try {
            logger.info("clientId " + clientId);
            return ResponseEntity.status(200).body( reportService.getReporByClientBtwnDates(startDate, endDate ,clientId) );
        } catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
