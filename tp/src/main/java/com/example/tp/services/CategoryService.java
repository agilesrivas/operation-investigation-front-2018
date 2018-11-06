package com.example.tp.services;


import com.example.tp.interfaces.Crud;
import com.example.tp.models.Category;
import com.example.tp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements Crud<Category> {

    @Autowired
    protected CategoryRepository categoryRepository;
    @Override
    public List<Category> all() throws Exception {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category update(Category value) throws Exception {
        this.categoryRepository.save(value);
        return value;
    }

    @Override
    public Optional<Category> getById(Long id) throws Exception {
         Optional<Category> obj=this.categoryRepository.findById(id);
         return obj;
    }

    @Override
    public Category create(Category value) throws Exception {
        this.categoryRepository.save(value);
        return value;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        this.categoryRepository.deleteById(id);
        return true;
    }
}
