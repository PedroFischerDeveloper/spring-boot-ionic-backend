package com.example.demo.resources;

import com.example.demo.domain.Category;
import com.example.demo.dtos.CategoryDto;
import com.example.demo.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="categories")
public class CategoriaResources {

    @Autowired
    private CategoryServices categoryServices;


    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok().body(categoryServices.findById(categoryId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDto categoryDto) {
        Category categoryResponse = categoryServices.insert(categoryServices.fromDTO(categoryDto));

        // cria respost no formato de uri do objeto criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categoryResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        categoryDto.setId(categoryId);
        categoryServices.update(categoryServices.fromDTO(categoryDto));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer categoryId) {
        categoryServices.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<Category> list = categoryServices.findAll();

        // percorre a lista de Category e transforma em CategoryDto
        List<CategoryDto> listDto = list.stream()
                .map(category -> new CategoryDto(category))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Category> list = categoryServices.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDto> listDto = list.map(category -> new CategoryDto(category));

        return ResponseEntity.ok().body(listDto);
    }
}