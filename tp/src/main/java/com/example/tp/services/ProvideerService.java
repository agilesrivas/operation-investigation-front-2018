package com.example.tp.services;

import com.example.tp.interfaces.Crud;

import com.example.tp.models.Provideer;
import com.example.tp.repositories.ProvideerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvideerService implements Crud<Provideer> {
    @Autowired
    protected ProvideerRepository provideerRepository;
    @Override
    public List<Provideer> all() throws Exception {
        return this.provideerRepository.findAll();
    }

    @Override
    public Provideer update(Provideer value) throws Exception {
        this.provideerRepository.save(value);
        return value;
    }

    @Override
    public Optional<Provideer> getById(Long id) throws Exception {

        Optional<Provideer> obj=this.provideerRepository.findById(id);
        return obj;
    }

    @Override
    public Provideer create(Provideer value) throws Exception {
        this.provideerRepository.save(value);
        return value;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        this.provideerRepository.deleteById(id);
        return true;
    }
}
