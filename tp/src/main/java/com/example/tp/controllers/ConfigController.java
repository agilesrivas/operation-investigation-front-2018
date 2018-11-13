package com.example.tp.controllers;


import com.example.tp.interfaces.CrudController;
import com.example.tp.models.Config;


import com.example.tp.models.Stock;
import com.example.tp.repositories.ConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/config")
public class ConfigController implements CrudController<Config> {

    @Autowired
    protected ConfigRepository config_repository;




    @PutMapping(value = "/edit")
    @Override
    public ResponseEntity update(@RequestBody Config valu) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(valu!=null ){
                Config newS=this.config_repository.save(valu);
                status=new ResponseEntity(newS,HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ResponseEntity create(Config value) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long value) {
        return null;
    }

    @GetMapping(value = "/")
    @Override
    public ResponseEntity getById(@RequestParam("id") Long value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null ){
                Optional<Config> ob=this.config_repository.findById(value);
                status=new ResponseEntity(ob,HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @GetMapping
    @Override
    public ResponseEntity<List<Config>> getAll() {

        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{

            List<Config> obj=this.config_repository.findAll();
            status=new ResponseEntity(obj,HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;


    }

}
