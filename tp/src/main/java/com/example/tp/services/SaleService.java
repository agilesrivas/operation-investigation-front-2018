package com.example.tp.services;

import com.example.tp.interfaces.Crud;

import com.example.tp.models.Sale;
import com.example.tp.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements Crud<Sale> {
    @Autowired
    protected SaleRepository saleRepository;
    @Override
    public List<Sale> all() throws Exception {
        return this.saleRepository.findAll();
    }

    @Override
    public Sale update(Sale value) throws Exception {
        this.saleRepository.save(value);
        return value;
    }

    @Override
    public Optional<Sale> getById(Long id) throws Exception {
        Optional<Sale> obj=this.saleRepository.findById(id);
        return obj;
    }

    @Override
    public Sale create(Sale value) throws Exception {
        Sale date= this.saleRepository.save(value);
         return date;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        this.saleRepository.deleteById(id);
        return true;
    }
}
