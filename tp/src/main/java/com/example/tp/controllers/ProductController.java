package com.example.tp.controllers;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.tp.interfaces.CrudController;
import com.example.tp.interfaces.Normalization;
import com.example.tp.models.P;
import com.example.tp.models.Product;
import com.example.tp.models.Q;
import com.example.tp.repositories.P_Repository;
import com.example.tp.repositories.Q_Repository;
import com.example.tp.services.ProductService;
import com.example.tp.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController implements CrudController<Product> {
    @Autowired
    protected ProductService productService;
    @Autowired
    protected P_Repository p_repository;
    @Autowired
    protected Q_Repository q_repository;
    @Autowired
    protected Normalization normalization;

    @GetMapping(value="/all/q")
    public ResponseEntity All(){
        ResponseEntity status=new ResponseEntity(HttpStatus.ACCEPTED);
        try{
            status=new ResponseEntity(this.q_repository.findAll(),HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @PostMapping(value = "/create")
    @Override
    public ResponseEntity create(@RequestBody Product value) {

        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof Product){
                Q ob2=this.q_repository.getByProduct(value.getId());
                System.out.println(ob2);
                Product id=this.productService.create(value);
                Q model_q=null;

                    model_q=new Q(id);
                    //demanda anul
                    model_q.setD(value.getCurrentAmount()*365);
                    model_q.setC(value.getCost());
                    model_q.setDr(value.getCurrentAmount()/365);
                    model_q.setH((value.getAmount()*0.10)/100);
                    model_q.setL(value.getProvideer().getLeadtime());
                    model_q.setS((value.getAmount()*0.10)/100);
                    model_q.setR(model_q.getDr()*model_q.getL());
                    model_q.setQ(Math.sqrt((2*model_q.getD()*model_q.getS())/model_q.getH()));
                    model_q.setTC((model_q.getD()*model_q.getC())+((model_q.getD()/model_q.getQ())*model_q.getS())+((model_q.getQ()/2)*model_q.getH()));
                    model_q.setDes_l(Math.sqrt(model_q.getL()));
                    //model_q.setDes_d();
                    //model_q.setZ();
                    model_q.setE_z(((1-model_q.getP())*model_q.getQ())/model_q.getDes_l());
                    //model_q.setZ_des_l();
                    this.q_repository.save(model_q);


                status=new ResponseEntity(id,HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @PutMapping(value = "/edit")
    @Override
    public ResponseEntity update(@RequestBody Product value)
    {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            if(value!=null && value instanceof Product){
                Q ob2=this.q_repository.getByProduct(value.getId());
                System.out.println(ob2);
                Product id=this.productService.update(value);
                Q model_q=null;
                if(ob2!=null){

                    ob2.setD((ob2.getProduct().getCurrentAmount()+value.getCurrentAmount())*365);
                    ob2.setC(value.getCost());
                    ob2.setDr(ob2.getD()/365);

                    ob2.setH((value.getAmount()*0.10)/100);
                    ob2.setL(value.getProvideer().getLeadtime());
                    ob2.setS(((value.getAmount()+ob2.getProduct().getAmount())*0.10)/100);
                    ob2.setR(ob2.getDr()*ob2.getL());

                    ob2.setQ(Math.sqrt((2*ob2.getD()*ob2.getS())/ob2.getH()));
                    ob2.setTC((ob2.getD()*ob2.getC())+((ob2.getD()/ob2.getQ())*ob2.getS())+((ob2.getQ()/2)*ob2.getH()));
                    ob2.setDes_l(Math.sqrt(ob2.getL()));
                    //model_q.setDes_d();
                    //model_q.setZ();
                    ob2.setE_z(((1-ob2.getP())*ob2.getQ())/ob2.getDes_l());
                    //model_q.setZ_des_l();
                    this.q_repository.save(ob2);
                }else {

                    model_q=new Q(id);
                    //demanda anul
                    model_q.setD(value.getCurrentAmount()*365);
                    model_q.setC(value.getCost());
                    model_q.setDr(value.getCurrentAmount()/365);
                    model_q.setH((value.getAmount()*0.10)/100);
                    model_q.setL(value.getProvideer().getLeadtime());
                    model_q.setS((value.getAmount()*0.10)/100);
                    model_q.setR(model_q.getDr()*model_q.getL());
                    model_q.setQ(Math.sqrt((2*model_q.getD()*model_q.getS())/model_q.getH()));
                    model_q.setTC((model_q.getD()*model_q.getC())+((model_q.getD()/model_q.getQ())*model_q.getS())+((model_q.getQ()/2)*model_q.getH()));
                    model_q.setDes_l(Math.sqrt(model_q.getL()));
                    //model_q.setDes_d();
                    //model_q.setZ();
                    model_q.setE_z(((1-model_q.getP())*model_q.getQ())/model_q.getDes_l());
                    //model_q.setZ_des_l();
                    this.q_repository.save(model_q);
                }

                status=new ResponseEntity(id,HttpStatus.OK);
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
            if(value!=null){
                this.productService.delete(value);
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
            if(value!=null){
                Optional<Product> obj=this.productService.getById(value);
                status=new ResponseEntity(obj,HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    @GetMapping
    @Override
    public ResponseEntity<List<Product>> getAll()
    {
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{

                List<Product> obj=this.productService.all();
                status=new ResponseEntity<List<Product>>(obj,HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
}
