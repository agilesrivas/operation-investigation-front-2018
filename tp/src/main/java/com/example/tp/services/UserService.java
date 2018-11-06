package com.example.tp.services;

import com.example.tp.interfaces.Crud;
import com.example.tp.models.Category;
import com.example.tp.models.User;
import com.example.tp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Crud<User> {
    @Autowired
    protected UserRepository userRepository;
    @Override
    public List<User> all() throws Exception {
        return this.userRepository.findAll();
    }

    @Override
    public User update(User value) throws Exception {
        this.userRepository.save(value);
        return value;
    }

    @Override
    public Optional<User> getById(Long id) throws Exception {
        Optional<User> obj=this.userRepository.findById(id);
        return obj;
    }

    @Override
    public User create(User value) throws Exception {
         this.userRepository.save(value);
         return value;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        this.userRepository.deleteById(id);
        return true;
    }
}
