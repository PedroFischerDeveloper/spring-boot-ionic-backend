package com.example.demo.resources;

import com.example.demo.domain.Category;
import com.example.demo.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="categories")
public class CategoriaResources {

    @Autowired
    private CategoryServices categoryServices;


    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok().body(categoryServices.findById(categoryId));
    }


}
