package com.example.tp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "model_q")
@NoArgsConstructor
public class Q {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @JoinColumn(name = "id_product", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
    @Column(name = "tc",nullable = true)
    private double TC;  //Costo anual total
    @Column(name = "tc_service",nullable = true)
    private double TC_service;  //Costo anual total
    @Column(name = "tc_production",nullable = true)
    private double TC_production;  //Costo anual total
    @Column(name = "D",nullable = true)
    private double D ; //Demanda anual
    @Column(name = "dr",nullable = true)
    private double dr;  //Demanda promedio diaria
    @Column(name = "d_di",nullable = true)
    private double d_t; //tasa de demanda diaria
    @Column(name = "p",nullable = true)
    private double p_t; //tasa de producción diaria
    @Column(name = "C",nullable = true)
    private double C;  //Costo por unidad
    @Column(name = "Q",nullable = true)
    private double Q;
    @Column(name = "Q_production",nullable = true)
    private double Q_production;
    //Cantidad que debe ordenarse (el monto óptimo se denomina cantidad económica del pedido – Qóptima)
    @Column(name = "S",nullable = true)
    private double  S; //Costo de preparación o costo de colocación de un pedido
    @Column(name = "R",nullable = true)
    private double R; //Punto de un nuevo pedid
    @Column(name = "L",nullable = true)
    private double L ;// Plazo de reposición
    @Column(name = "H",nullable = true)
    private double H;    //Costo  anual  de  mantenimiento  y  de  almacenamiento  por  unidad  del  inventario promedio
    @Column(name = "P_level",nullable = true)
    private double P;
    @Column(name = "des_l",nullable = true)
    private double des_l;
    @Column(name = "z",nullable = true)
    private double z;
    @Column(name = "z_des_l",nullable = true)
    private double z_des_l;
    @Column(name = "des_d",nullable = true)
    private double des_d;
    @Column(name = "E_z",nullable = true)
    private double e_z;

    public Q(Product st){
        this.product=st;

    }



}
