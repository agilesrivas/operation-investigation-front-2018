package com.example.tp.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "provideer")
@NoArgsConstructor
public class Provideer {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name="name",nullable = false,unique = true)
    private String name;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="phone",nullable = false)
    private String phone;
    @Column(name="leadtime",nullable = false)
    private int leadtime;


    public Provideer(long id,String name,String email,String phone,int leadtime){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.leadtime=leadtime;
    }
    public Provideer(String name,String email,String phone,int leadtime){
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.leadtime=leadtime;
    }
}
