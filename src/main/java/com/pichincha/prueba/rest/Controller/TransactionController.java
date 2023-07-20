package com.pichincha.prueba.rest.Controller;

import com.pichincha.prueba.rest.Entity.Transaction;
import com.pichincha.prueba.rest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity getAll() {

        try {
            return ResponseEntity.status(200).body( transactionService.getAll() );
        } catch(Exception e) {
            return ResponseEntity.status(400).body( e.getMessage() );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        try {
            return ResponseEntity.status(200).body( transactionService.getById(id) );
        } catch(Exception e) {
            return ResponseEntity.status(400).body( e.getMessage() );
        }
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody Transaction transaction) {
        try {
            return ResponseEntity.status(200).body( transactionService.save(transaction) );
        }catch(Exception e) {
            return ResponseEntity.status(400).body( e.getMessage() );
        }
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody Transaction transaction) {
        try {
            return ResponseEntity.status(202).body( transactionService.update(transaction) );
        } catch(Exception e) {
            return ResponseEntity.status(400).body( e.getMessage() );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        try {
            transactionService.deleteById(id);
            return ResponseEntity.status(200).body("Transacción eliminada exitosamente");
        } catch(Exception e) {
            return ResponseEntity.status(400).body( e.getMessage());
        }
    }

}
