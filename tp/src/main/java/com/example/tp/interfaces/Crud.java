package com.example.tp.interfaces;

import com.example.tp.models.Category;

import java.util.List;
import java.util.Optional;

public interface Crud<T> {

    List<T> all() throws Exception;
    T update(T value) throws Exception;
    Optional<T> getById(Long id) throws Exception;
    T create(T value) throws Exception;
    boolean delete(Long id) throws Exception;
}
