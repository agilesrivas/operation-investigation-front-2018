package com.example.tp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "normalizaciones")
@Entity
@NoArgsConstructor
public class Normaliza {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    protected long id;
    @Column(name="e_z",nullable = true)
    protected double e_z;
    @Column(name = "z",nullable = true)
    protected double z;

    public Normaliza(double e,double z){
        this.e_z=e;
        this.z=z;
    }
    public Normaliza(long id,double e,double z){
        this.id=id;
        this.e_z=e;
        this.z=z;
    }
}
