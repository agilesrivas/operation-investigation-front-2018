package com.example.tp.services;


import com.example.tp.interfaces.Crud;
import com.example.tp.models.Category;
import com.example.tp.models.DetailSale;
import com.example.tp.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailSaleService implements Crud<DetailSale> {

    @Autowired
    protected DetailRepository detailRepository;
    @Override
    public List<DetailSale> all() throws Exception {
        return this.detailRepository.findAll();
    }

    @Override
    public DetailSale update(DetailSale value) throws Exception {
        this.detailRepository.save(value);
        return value;
    }

    @Override
    public Optional<DetailSale> getById(Long id) throws Exception {
        Optional<DetailSale> obj=this.detailRepository.findById(id);
        return obj;
    }

    @Override
    public DetailSale create(DetailSale value) throws Exception {
        this.detailRepository.save(value);
        return value;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        this.detailRepository.deleteById(id);
        return true;
    }
}
