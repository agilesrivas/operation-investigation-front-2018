package com.example.tp.controllers;

import com.example.tp.interfaces.CrudController;
import com.example.tp.interfaces.Normalization;
import com.example.tp.models.DetailSale;
import com.example.tp.models.P;
import com.example.tp.models.Product;
import com.example.tp.models.Q;
import com.example.tp.repositories.P_Repository;
import com.example.tp.repositories.Q_Repository;
import com.example.tp.services.DetailSaleService;
import com.example.tp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/detailSale")
public class DetailSaleController implements CrudController<DetailSale> {
    @Autowired
    protected DetailSaleService detailSaleService;
    @Autowired
    protected ProductService productService;
    @PostMapping(value = "/create")
    @Override
    public ResponseEntity create(@RequestBody DetailSale value) {
        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            if (value != null && value instanceof DetailSale) {

                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    @PutMapping(value = "/edit")
    @Override
    public ResponseEntity update(@RequestBody DetailSale value) {
        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            if (value != null && value instanceof DetailSale) {
                this.detailSaleService.update(value);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    @DeleteMapping
    @Override
    public ResponseEntity delete(@RequestParam("id") Long value) {
        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            if (value != null) {
                this.detailSaleService.delete(value);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @GetMapping(value = "/")
    @Override
    public ResponseEntity getById(@RequestParam("id") Long value) {
        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            if (value != null) {
                this.detailSaleService.getById(value);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    @GetMapping
    @Override
    public ResponseEntity<List<DetailSale>> getAll() {
        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            List<DetailSale> obj = this.detailSaleService.all();
            status = new ResponseEntity<List<DetailSale>>(obj, HttpStatus.OK);
        }
        catch(Exception e)
    {
        e.printStackTrace();
    }
        return status;
}
}
