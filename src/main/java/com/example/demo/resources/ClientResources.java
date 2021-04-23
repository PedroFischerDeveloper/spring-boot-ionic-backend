package com.example.demo.resources;

import com.example.demo.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="clients")
public class ClientResources {

    @Autowired
    private ClientServices clientServices;


    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer clientId) {
        return ResponseEntity.ok().body(clientServices.findById(clientId));
    }


}
