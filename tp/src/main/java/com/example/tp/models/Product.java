package com.example.tp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @JoinColumn(name = "id_category", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Category category;
    @JoinColumn(name = "id_provideer", nullable = true)
    @OneToOne(fetch = FetchType.EAGER)
    private Provideer provideer;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="code",nullable = false)
    private String code;
    @Column(name="amount",nullable = false)
    private double amount;
    @Column(name="cost",nullable = false)
    private double cost;
    @Column(name="current_amount",nullable = false)
    private int currentAmount;
    @Column(name="reorder_point",nullable = false)
    private int reorder_point;

    public Product (long id,Category category,Provideer provideer,String name,String code,double amount,double cost,int current,int reorder){
        this.id=id;
        this.category=category;
        this.provideer=provideer;
        this.name=name;
        this.code=code;
        this.amount=amount;
        this.cost=cost;
        this.currentAmount=current;
        this.reorder_point=reorder;
    }
    public Product (Category category,Provideer provideer,String name,String code,double amount,double cost,int current,int reorder){
        this.category=category;
        this.provideer=provideer;
        this.name=name;
        this.code=code;
        this.amount=amount;
        this.cost=cost;
        this.currentAmount=current;
        this.reorder_point=reorder;
    }
    public Product (Category category,String name,String code,double amount,double cost,int current,int reorder){
        this.category=category;
        this.name=name;
        this.code=code;
        this.amount=amount;
        this.cost=cost;
        this.currentAmount=current;
        this.reorder_point=reorder;
    }
}
