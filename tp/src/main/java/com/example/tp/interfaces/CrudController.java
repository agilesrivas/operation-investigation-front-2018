package com.example.tp.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CrudController<T> {

    public ResponseEntity create(@RequestBody T value );
    public ResponseEntity update(@RequestBody T value );
    public ResponseEntity delete(@RequestParam Long value );
    public ResponseEntity getById(@RequestParam Long value );
    public  ResponseEntity <List<T>> getAll();
}
