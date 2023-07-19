package com.pichincha.prueba.rest.Controller;

import com.pichincha.prueba.rest.Entity.Account;
import com.pichincha.prueba.rest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("")
    public ResponseEntity getAll() {

        try {
            return ResponseEntity.status(200).body( accountService.getAll() );
        } catch(Exception e) {
            return ResponseEntity.status(400).body( e.getMessage() );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id ) {

        try {
            return ResponseEntity.status(200).body( accountService.getById(id) );
        } catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @PostMapping("")
    public ResponseEntity post(@RequestBody Account account ) {

        try {
            return ResponseEntity.status(200).body( accountService.save(account) );
        }catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity put( @RequestBody Account account ) {
        try {
            return ResponseEntity.status(202).body( accountService.update(account) );
        } catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id ) {
        try {
            accountService.deleteById(id);
            return ResponseEntity.status(200).body("Cuenta eliminada exitosamente");
        } catch(Exception e) {
            return ResponseEntity.status(400).body("Bad Request "+ e.getMessage());
        }
    }
}
