package com.example.demo.services;

import com.example.demo.domain.Order;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServices {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> get() {
        return orderRepository.findAll();
    }

    public Order findById(Integer orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()) {
            throw new ObjectNotFoundException("Object not found " + order);
        }

        return order.get();
    }

}
