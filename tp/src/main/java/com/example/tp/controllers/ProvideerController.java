package com.example.tp.controllers;


import com.example.tp.interfaces.CrudController;
import com.example.tp.models.Provideer;
import com.example.tp.services.ProvideerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/provideer")
public class ProvideerController implements CrudController<Provideer> {
    @Autowired
    protected ProvideerService provideerService;

    @PostMapping(value = "/create")
    @Override
    public ResponseEntity create(@RequestBody Provideer value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof Provideer){
                this.provideerService.create(value);
                status=new ResponseEntity(HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @PutMapping(value = "/edit")
    @Override
    public ResponseEntity update(@RequestBody Provideer value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof Provideer){
                this.provideerService.update(value);
                status=new ResponseEntity(HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @DeleteMapping
    @Override
    public ResponseEntity delete(@RequestParam("id") Long value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null ){
                this.provideerService.delete(value);
                status=new ResponseEntity(HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @GetMapping(value = "/")
    @Override
    public ResponseEntity getById(@RequestParam("id") Long value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null ){
                this.provideerService.getById(value);
                status=new ResponseEntity(HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @GetMapping
    @Override
    public ResponseEntity<List<Provideer>> getAll() {

        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{

                List<Provideer> obj=this.provideerService.all();
                status=new ResponseEntity(obj,HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;


    }
}
