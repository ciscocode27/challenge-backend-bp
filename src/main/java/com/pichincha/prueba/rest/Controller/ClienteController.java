package com.pichincha.prueba.rest.Controller;


import com.pichincha.prueba.rest.Entity.Client;
import com.pichincha.prueba.rest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clients")
public class ClienteController {


    @Autowired
    ClientService clientService;

    @GetMapping("")
    public ResponseEntity getAll() {

        try {
            return ResponseEntity.status(200).body( clientService.getAll() );
        } catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity post(@RequestBody Client client) {

        try {
            return ResponseEntity.status(200).body( clientService.save(client) );
        }catch(Exception e) {
            return ResponseEntity.status(400).body( e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity put(@RequestBody Client client) {
        try {
            return ResponseEntity.status(202).body( clientService.update(client) );
        } catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.status(200).body("Cliente eliminado exitosamente");
        } catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
