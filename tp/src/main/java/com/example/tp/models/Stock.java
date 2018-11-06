package com.example.tp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Stock")
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @JoinColumn(name = "id_product")
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
    @Column(name="current_amount",nullable = false)
    private int currentAmount;
    @Column(name="reorder_point",nullable = false)
    private int reorder_point;

    public Stock (long id,Product product,int current,int reorder){
        this.id=id;
        this.product=product;
        this.currentAmount=current;
        this.reorder_point=reorder;
    }
    public Stock (Product product,int current,int reorder){
        this.product=product;
        this.currentAmount=current;
        this.reorder_point=reorder;
    }
}
