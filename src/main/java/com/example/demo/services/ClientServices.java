package com.example.demo.services;

import com.example.demo.domain.Category;
import com.example.demo.domain.Client;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServices {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> get() {
        return clientRepository.findAll();
    }

    public Client findById(Integer clienteId) {
        Optional<Client> client = clientRepository.findById(clienteId);

        if(client.isEmpty()) {
            throw new ObjectNotFoundException("Object not found " + client);
        }

        return client.get();
    }

}
