package com.example.tp.controllers;

import com.example.tp.interfaces.CrudController;
import com.example.tp.models.Product;
import com.example.tp.models.Q;
import com.example.tp.models.Sale;
import com.example.tp.repositories.P_Repository;
import com.example.tp.repositories.Q_Repository;
import com.example.tp.services.ProductService;
import com.example.tp.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/sale")
public class SaleController implements CrudController<Sale> {
    @Autowired
    protected SaleService saleService;
    @Autowired
    protected ProductService productService;

    @PostMapping(value = "/create")
    @Override
    public ResponseEntity create(@RequestBody Sale value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof Sale){

                Optional<Product> obj=this.productService.getById(value.getProduct().getId());
                obj.get().setCurrentAmount(obj.get().getCurrentAmount()-value.getProduct().getCurrentAmount());
                this.productService.update(obj.get());
                Sale sale=this.saleService.create(value);
                status=new ResponseEntity(sale,HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @PutMapping(value = "/edit")
    @Override
    public ResponseEntity update(@RequestBody Sale value) {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof Sale){
                this.saleService.update(value);
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
                this.saleService.delete(value);
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
                this.saleService.getById(value);
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
    public ResponseEntity<List<Sale>> getAll() {

        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{

            List<Sale> obj=this.saleService.all();
            status=new ResponseEntity(obj,HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;


    }
}
