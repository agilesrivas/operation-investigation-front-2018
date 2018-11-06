package com.example.tp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Sales")
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
      @Column(name="total",nullable = false)
    private double total;
    @JoinColumn(name = "id_product", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    public Sale (long id,double cost,Product pro){
        this.id=id;

        this.total=cost;
        this.product=pro;
    }
    public Sale (double cost,Product pro){

        this.total=cost;
        this.product=pro;
    }
}
