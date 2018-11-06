package com.example.tp.controllers;


import com.example.tp.interfaces.CrudController;
import com.example.tp.models.P;
import com.example.tp.models.Q;
import com.example.tp.models.Stock;
import com.example.tp.repositories.P_Repository;
import com.example.tp.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/stock")
public class StockController implements CrudController<Stock> {
    @Autowired
    protected StockService stockService;
    @Autowired
    protected P_Repository p_repository;

    @PostMapping(value = "/create")
    @Override
    public ResponseEntity create(@RequestBody Stock value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof Stock){
                this.stockService.create(value);
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
    public ResponseEntity update(@RequestBody Stock value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof Stock){
                Stock newS=this.stockService.update(value);
                status=new ResponseEntity(newS,HttpStatus.OK);
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
                this.stockService.delete(value);
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
                Optional<Stock> ob=this.stockService.getById(value);
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
    public ResponseEntity<List<Stock>> getAll() {

        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{

            List<Stock> obj=this.stockService.all();
            status=new ResponseEntity(obj,HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;


    }
    @GetMapping(value = "/P/all")
    public ResponseEntity<List<P>>getAllModelP(){
        ResponseEntity status=new ResponseEntity(HttpStatus.ACCEPTED);

        try{
            List<P> objs=this.p_repository.findAll();
            status=new ResponseEntity(objs,HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @GetMapping(value = "/Q/all")
    public ResponseEntity<List<Q>>getAllModelQ(){
        ResponseEntity status=new ResponseEntity(HttpStatus.ACCEPTED);

        try{
            List<P> objs=this.p_repository.findAll();
            status=new ResponseEntity(objs,HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
}
