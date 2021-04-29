package com.example.demo.resources;

import com.example.demo.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="orders")
public class OrderResources {

    @Autowired
    private OrderServices orderServices;

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer orderId) {
        return ResponseEntity.ok().body(orderServices.findById(orderId));
    }


}
