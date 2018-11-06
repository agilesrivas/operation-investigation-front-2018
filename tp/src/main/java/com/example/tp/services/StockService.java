package com.example.tp.services;


import com.example.tp.interfaces.Crud;
import com.example.tp.models.Category;
import com.example.tp.models.Stock;
import com.example.tp.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService implements Crud<Stock> {

    @Autowired
    protected StockRepository stockRepository;
    @Override
    public List<Stock> all() throws Exception {
        return this.stockRepository.findAll();
    }

    @Override
    public Stock update(Stock value) throws Exception {
        Stock nn=this.stockRepository.save(value);
        return nn;
    }

    @Override
    public Optional<Stock> getById(Long id) throws Exception {
        Optional<Stock> obj=this.stockRepository.findById(id);
        return obj;
    }

    @Override
    public Stock create(Stock value) throws Exception {
        Stock nn=this.stockRepository.save(value);
        return nn;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        this.stockRepository.deleteById(id);
        return true;
    }
}
