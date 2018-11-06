package com.example.tp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "DetailSales")
@NoArgsConstructor
public class DetailSale {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @JoinColumn(name = "id_sale", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Sale sale;

    @Column(name = "subtotal",nullable = false)
    private double subtotal;

    public DetailSale(long id,Sale sale,Product product,double subtotal){
        this.id=id;
        this.sale=sale;

        this.subtotal=subtotal;
    }
    public DetailSale(Sale sale,Product product,double subtotal){
        this.sale=sale;

        this.subtotal=subtotal;
    }
}
