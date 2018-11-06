package com.example.tp.services;


import com.example.tp.interfaces.Crud;
import com.example.tp.models.Product;
import com.example.tp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements Crud<Product> {

    @Autowired
    protected ProductRepository productRepository;
    @Override
    public List<Product> all() throws Exception {
        return this.productRepository.findAll();
    }

    @Override
    public Product update(Product value) throws Exception {
       Product obj= this.productRepository.save(value);
        return obj;
    }

    @Override
    public Optional<Product> getById(Long id) throws Exception {
        Optional<Product> obj=this.productRepository.findById(id);
        return obj;
    }

    @Override
    public Product create(Product value) throws Exception {
        Product use=this.productRepository.save(value);
        return use;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        this.productRepository.deleteById(id);
        return true;
    }
}
