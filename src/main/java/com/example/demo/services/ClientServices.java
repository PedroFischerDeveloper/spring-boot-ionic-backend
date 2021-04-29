package com.example.demo.services;

import com.example.demo.domain.Client;
import com.example.demo.domain.Client;
import com.example.demo.dtos.ClientDto;
import com.example.demo.exception.DataIntegrityException;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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


    public Client update(Client client) {
        Integer id = client.getId();
        Optional<Client> clientExists = clientRepository.findById(id);

        if(clientExists.isEmpty()) {
            throw new ObjectNotFoundException("Object not found " + client);
        }

        Client updatedCliente = clientExists.get();
        updatedCliente.setName(client.getName());
        updatedCliente.setEmail(client.getEmail());

        return clientRepository.save(updatedCliente);
    }


    public void delete(Integer ClientId) {
        Optional<Client> ClientExists = clientRepository.findById(ClientId);

        if(ClientExists.isEmpty()) {
            throw new ObjectNotFoundException("Object not found ");
        }

        try {
            clientRepository.deleteById(ClientId);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("It is not possible to exclude a Client containing orders");
        }

    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Page findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        return clientRepository.findAll(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }

    public Client fromDTO(ClientDto clientDto) {
        return new Client(clientDto.getId(), clientDto.getName(), clientDto.getEmail(), null, null);
    }
}
