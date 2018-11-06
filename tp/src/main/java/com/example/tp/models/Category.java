package com.example.tp.models;





import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "Categories")
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="name",nullable = false,unique = true)
    private String name;

    @Column(name="description",nullable = false)
    private String description;
    public Category(long id,String name,String description){
        this.id=id;
        this.name=name;
        this.description=description;
    }
    public Category(String name,String description){
        this.name=name;
        this.description=description;
    }
}
