package com.example.demo.services;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> get() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isEmpty()) {
            throw new ObjectNotFoundException("Object not found " + category);
        }

        return category.get();
    }

    public Category insert(Category category) {
        category.setId(null); // garantindo que será uma inserção
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        Integer id = category.getId();
        Optional<Category> categoryExists = categoryRepository.findById(id);

        if(categoryExists.isEmpty()) {
            throw new ObjectNotFoundException("Object not found " + category);
        }

        return categoryRepository.save(category);
    }

}
