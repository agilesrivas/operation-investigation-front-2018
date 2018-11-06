package com.example.tp.controllers;


import com.example.tp.interfaces.CrudController;
import com.example.tp.models.User;
import com.example.tp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController implements CrudController<User> {
    @Autowired
    protected UserService userService;

    @PostMapping(value = "/create")
    @Override
    public ResponseEntity create(@RequestBody User value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof User){
                this.userService.create(value);
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
    public ResponseEntity update(@RequestBody User value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof User){
                this.userService.update(value);
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
                this.userService.delete(value);
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
                this.userService.getById(value);
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
    public ResponseEntity<List<User>> getAll() {

        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{

            List<User> obj=this.userService.all();
            status=new ResponseEntity(HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;


    }
}
