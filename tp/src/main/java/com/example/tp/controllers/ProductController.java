package com.example.tp.controllers;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.tp.interfaces.CrudController;
import com.example.tp.interfaces.Normalization;
import com.example.tp.models.*;
import com.example.tp.repositories.ConfigRepository;
import com.example.tp.repositories.P_Repository;
import com.example.tp.repositories.Q_Repository;
import com.example.tp.services.ProductService;
import com.example.tp.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    protected ConfigRepository config;

    @GetMapping(value="/all/p")
    public ResponseEntity All_P(){
        ResponseEntity status=new ResponseEntity(HttpStatus.ACCEPTED);
        try{
            status=new ResponseEntity(this.p_repository.findAll(),HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
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
                Config conf=this.config.getOne((long) 1);
                Product product=this.productService.create(value);
                Q ob2=this.q_repository.getByProduct(value.getId());
                Q model_q=null;
                P model_p=null;

                if(product.getProvideer()!=null && product.getProvideer().getLeadtime()>0 )
                {
                   model_p=new P(product);
                   model_p.setD(product.getCurrentAmount()*conf.getDiasLaborales());
                    model_p.setC(product.getCost());
                    model_p.setDr(product.getCurrentAmount());
                    model_p.setH((product.getAmount()*conf.getCostoMantenimiento())/100);
                    model_p.setL(conf.getDiasDeCompras());
                    model_p.setS((product.getAmount()*conf.getCostoVenta())/100);
                    model_p.setR(model_p.getDr()*model_p.getL());
                    model_p.setT(product.getProvideer().getLeadtime());
                    model_p.setI(product.getCurrentAmount());
                    model_p.setP(conf.getPorcentajeServicio());
                    model_p.setDes_d(1);
                    model_p.setDes_t_l(Math.sqrt(model_p.getT()+model_p.getL())*model_p.getDes_d());

                    model_p.setE_z(((model_p.getDr()*model_p.getT())*(1-model_p.getP()))/model_p.getDes_t_l());
                    model_p.setQ(model_p.getDr()*(model_p.getT()+model_p.getL()) + (model_p.getZ()*model_p.getDes_t_l()) - model_p.getI());
                    System.out.println(model_p.getE_z());
                    Normaliza normModel=this.normalization.getByZ(model_p.getE_z());
                    if(normModel !=null){
                        model_p.setZ(normModel.getZ());
                        model_p.setZ_des_t_l(model_p.getZ()*model_p.getDes_t_l());
                    }else{
                        List<Normaliza> normalizations=this.normalization.findAll();
                        int i=0;
                        int j=1;
                        int b=i;

                        while((i<normalizations.size()) && (b<normalizations.size()))
                        {

                            if((normalizations.get(i).getE_z()>normalizations.get(j).getE_z()) && (normalizations.get(i).getE_z()<normalizations.get(b).getE_z())){
                                normModel=normalizations.get(i);
                                model_p.setZ(normModel.getZ());
                            }
                            j=i;
                            b=i+1;
                            i++;

                        }

                    }



                    this.p_repository.save(model_p);
                    
                }else{
                    model_q=new Q(product);
                    //demanda anul
                    model_q.setD(product.getCurrentAmount()*conf.getDiasLaborales());
                    model_q.setC(product.getCost());
                    model_q.setDr(product.getCurrentAmount());
                    model_q.setH((product.getAmount()*conf.getCostoMantenimiento())/100);
                    model_q.setL(conf.getDiasDeCompras());

                    model_q.setS((product.getAmount()*conf.getCostoVenta())/100);
                    model_q.setR(model_q.getDr()*model_q.getL());
                    model_q.setQ(Math.sqrt((2*model_q.getD()*model_q.getS())/model_q.getH()));
                    model_q.setTC((model_q.getD()*model_q.getC())+((model_q.getD()/model_q.getQ())*model_q.getS())+((model_q.getQ()/2)*model_q.getH()));
                    model_q.setDes_l(Math.sqrt(model_q.getL()));
                    model_q.setDes_d(2);
                    model_q.setE_z(((1-model_q.getP())*model_q.getQ())/model_q.getDes_l());
                    model_q.setZ_des_l(2);
                    Normaliza normModel=this.normalization.getByZ(model_q.getE_z());
                    if(normModel !=null){
                       model_q.setZ(normModel.getZ()); 
                    }else{
                        List<Normaliza> normalizations=this.normalization.findAll();
                        int i=0;
                        int j=1;
                        int b=i;

                        while((i<normalizations.size()) && (b<normalizations.size()))
                        {

                        if((normalizations.get(i).getE_z()>normalizations.get(j).getE_z()) && (normalizations.get(i).getE_z()<normalizations.get(b).getE_z())){
                                normModel=normalizations.get(i);
                                model_q.setZ(normModel.getZ());
                            }
                            j=i;
                            b=i+1;
                            i++;

                        }

                    }
                    
                    this.q_repository.save(model_q);
                }



                status=new ResponseEntity(product,HttpStatus.OK);
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
                Config conf=this.config.getOne((long) 1);
                Q ob2=this.q_repository.getByProduct(value.getId());
                P p_ob2=this.p_repository.getByProduct(value.getId());

                Product product=this.productService.update(value);
                
                     if(p_ob2!=null){
                        if(product.getProvideer().getLeadtime()!=0 && product.getProvideer()!=null)
                        {
                        p_ob2.setD((p_ob2.getStock().getCurrentAmount()+product.getCurrentAmount())*conf.getDiasLaborales());
                        p_ob2.setC(product.getCost());
                        p_ob2.setDr((p_ob2.getStock().getCurrentAmount()+product.getCurrentAmount())/conf.getDiasLaborales());
                        p_ob2.setH((product.getAmount()*conf.getCostoMantenimiento())/100);
                        p_ob2.setL(1);
                        p_ob2.setS((product.getAmount()*conf.getCostoVenta())/100);
                        p_ob2.setR(p_ob2.getDr()*p_ob2.getL());
                        p_ob2.setZ(p_ob2.getZ());
                        p_ob2.setT(product.getProvideer().getLeadtime());
                        p_ob2.setI(product.getCurrentAmount());
                        p_ob2.setP(p_ob2.getP());
                        p_ob2.setQ(p_ob2.getDr()*(p_ob2.getT()+p_ob2.getL()) + (p_ob2.getZ()*p_ob2.getDes_t_l()) - p_ob2.getI());
                        p_ob2.setDes_t_l(Math.sqrt(p_ob2.getL()));
                        p_ob2.setDes_d(1);
                        p_ob2.setDes_t_l(Math.sqrt(p_ob2.getT()+p_ob2.getL())*p_ob2.getDes_d());
                        p_ob2.setE_z(((p_ob2.getDr()*p_ob2.getT())*(1-p_ob2.getP())/p_ob2.getDes_t_l()));
                        this.p_repository.save(p_ob2);
                        }
                     }
                if(ob2!=null){
                    //demanda anul
                    ob2.setD((product.getCurrentAmount()+ob2.getProduct().getCurrentAmount())*conf.getDiasLaborales());
                    ob2.setC(product.getCost());
                    ob2.setDr((product.getCurrentAmount()+ob2.getProduct().getCurrentAmount())/conf.getDiasLaborales());
                    ob2.setH((product.getAmount()*conf.getCostoMantenimiento())/100);
                    ob2.setL(ob2.getL());
                    ob2.setP(ob2.getP());
                    ob2.setS((product.getAmount()*conf.getCostoVenta())/100);
                    ob2.setR(ob2.getDr()*ob2.getL());
                    ob2.setQ(Math.sqrt((2*ob2.getD()*ob2.getS())/ob2.getH()));
                    ob2.setTC((ob2.getD()*ob2.getC())+((ob2.getD()/ob2.getQ())*ob2.getS())+((ob2.getQ()/2)*ob2.getH()));
                    ob2.setDes_l(Math.sqrt(ob2.getL()));
                    ob2.setDes_d(1);
                    ob2.setZ(ob2.getZ());
                    ob2.setE_z(((1-ob2.getP())*ob2.getQ())/ob2.getDes_l());
                    ob2.setZ_des_l(ob2.getZ_des_l());
                    this.q_repository.save(ob2);
                }
               
                status=new ResponseEntity(product,HttpStatus.OK);
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
