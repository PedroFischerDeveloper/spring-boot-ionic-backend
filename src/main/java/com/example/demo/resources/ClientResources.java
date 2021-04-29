package com.example.demo.resources;

import com.example.demo.domain.Client;
import com.example.demo.domain.Client;
import com.example.demo.dtos.ClientDto;
import com.example.demo.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="clients")
public class ClientResources {

    @Autowired
    private ClientServices clientServices;


    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Client> findById(@PathVariable Integer clientId) {
        return ResponseEntity.ok().body(clientServices.findById(clientId));
    }
    
    @RequestMapping(value = "/{ClientId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDto ClientDto, @PathVariable Integer ClientId) {
        ClientDto.setId(ClientId);
        clientServices.update(clientServices.fromDTO(ClientDto));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{ClientId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer ClientId) {
        clientServices.delete(ClientId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> findAll() {
        List<Client> list = clientServices.findAll();

        // percorre a lista de Client e transforma em ClientDto
        List<ClientDto> listDto = list.stream()
                .map(Client -> new ClientDto(Client))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Client> list = clientServices.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDto> listDto = list.map(Client -> new ClientDto(Client)) ;

        return ResponseEntity.ok().body(listDto);
    }


}
