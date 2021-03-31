package com.example.demo.resources;

import com.example.demo.domain.Category;
import com.example.demo.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="categories")
public class CategoriaResources {

    @Autowired
    private CategoryServices categoryServices;

    @RequestMapping(method = RequestMethod.GET, value = "all")
    public ResponseEntity<?> get(@PathVariable Integer categoryId) {
        return ResponseEntity.ok().body(categoryServices.get());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok().body(categoryServices.search(categoryId));
    }
}
