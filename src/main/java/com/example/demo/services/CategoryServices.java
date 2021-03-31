package com.example.demo.services;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> get() {
        return categoryRepository.findAll();
    }

    public Category search(Integer categoriaId) {
        var category = categoryRepository.findById(categoriaId);
        return category.orElse(null);
    }

}
