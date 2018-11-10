package com.example.tp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "model_p")
@NoArgsConstructor
public class P {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @JoinColumn(name = "id_product", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Product stock;


    @Column(name = "D",nullable = true)
    private double D ; //Demanda anual
    @Column(name = "dr",nullable = true)
    private double dr;  //Demanda promedio diaria

    @Column(name = "C",nullable = true)
    private double C;  //Costo por unidad
    @Column(name = "Q",nullable = true)
    private double Q;  //Cantidad que debe ordenarse (el monto óptimo se denomina cantidad económica del pedido – Qóptima)
    @Column(name = "S",nullable = true)
    private double  S; //Costo de preparación o costo de colocación de un pedido
    @Column(name = "R",nullable = true)
    private double R; //Punto de un nuevo pedid
    @Column(name = "L",nullable = true)
    private double L ;// Plazo de reposición
    @Column(name = "plazo_reposicion",nullable = true)
    private double plazo_reposicion ;// Plazo de reposición
    @Column(name = "H",nullable = true)
    private double H;    //Costo  anual  de  mantenimiento  y  de  almacenamiento  por  unidad  del  inventario promedio
    @Column(name = "T",nullable = true)
    private double T;
    @Column(name = "I",nullable = true)
    private double I;
    @Column(name = "P_level",nullable = true)
    private double P;
    @Column(name = "des_t_l",nullable = true)
    private double des_t_l;
    @Column(name = "z",nullable = true)
    private double z;
    @Column(name = "z_des_l",nullable = true)
    private double z_des_t_l;
    @Column(name = "des_d",nullable = true)
    private double des_d;
    @Column(name = "E_z",nullable = true)
    private double e_z;

    public P(Product st){
        this.stock=st;
    }
}
