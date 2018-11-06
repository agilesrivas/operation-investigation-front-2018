package com.example.tp.controllers;

import com.example.tp.interfaces.CrudController;
import com.example.tp.models.Category;
import com.example.tp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
@CrossOrigin
@RestController
@RequestMapping(value = "/category")
public class CategoryController implements CrudController<Category> {

    @Autowired
    protected CategoryService category;

    @PostMapping(value ="/create")
    @Override
    public ResponseEntity create(@RequestBody Category value)
    {
        ResponseEntity status = new ResponseEntity(INTERNAL_SERVER_ERROR);
        try{

            if(value!=null && value instanceof Category){
                Category obj=this.category.create(value);
                if(obj!=null)
                {

                    status = new ResponseEntity<Category>(obj,HttpStatus.OK);
                }
            }
        }
        catch (Exception e){
            e.getMessage();
        }

        return status;
    }
    @PutMapping(value = "/edit")
    @Override
    public ResponseEntity update(@RequestBody Category value) {
        ResponseEntity status = new ResponseEntity(INTERNAL_SERVER_ERROR);
        try{

            if(value!=null && value instanceof Category){
                Category obj=this.category.create(value);
                if(obj!=null)
                {
                    status = new ResponseEntity<Category>(value,HttpStatus.OK);
                }
            }
        }
        catch (Exception e){
            e.getMessage();
        }

        return status;
    }
    @DeleteMapping
    @Override
    public ResponseEntity delete(@RequestParam("id") Long value) {
        System.out.println(value);
        ResponseEntity status = new ResponseEntity(INTERNAL_SERVER_ERROR);
        try {
            if(value!=null) {
            this.category.delete(value);
                status = new ResponseEntity(value,HttpStatus.OK);
        }
        }
        catch(Exception e){
            e.getMessage();
            }
        return status;
    }
    @GetMapping(value = "/")
    @Override
    public ResponseEntity getById(@RequestParam("id") Long value) {
        System.out.println(value);
        ResponseEntity status = new ResponseEntity(INTERNAL_SERVER_ERROR);
        try{

            if(value!=null){
                Optional<Category> obj=this.category.getById(value);
                if(obj!=null)
                {
                    status = new ResponseEntity(obj,HttpStatus.OK);
                }
            }
        }
        catch (Exception e){
            e.getMessage();
        }

        return status;
    }
    @GetMapping
    @Override
    public ResponseEntity<List<Category>> getAll() {
        ResponseEntity status = new ResponseEntity(INTERNAL_SERVER_ERROR);
        try{
                List<Category> obj=this.category.all();
                if(obj!=null)
                {
                    status = new ResponseEntity<List<Category>>(obj,HttpStatus.OK);
                }
            } catch (Exception e1) {
            e1.printStackTrace();
        }

        return status;
    }
}
