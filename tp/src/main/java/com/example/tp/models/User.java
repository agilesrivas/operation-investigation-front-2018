package com.example.tp.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name="name",nullable = false,unique = true)
    private String name;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="phone;",nullable = false)
    private String phone;

    public User(long id,String name,String email,String phone){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;

    }
    public User(String name,String email,String phone){
        this.name=name;
        this.email=email;
        this.phone=phone;

    }
}
