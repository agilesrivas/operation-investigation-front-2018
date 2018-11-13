package com.example.tp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "Config")
@NoArgsConstructor
public class Config {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "diasLaborales", nullable = false)
    private double diasLaborales;
    @Column(name = "costoMantenimiento", nullable = false)
    private double costoMantenimiento;
    @Column(name = "CostoVenta", nullable = false)
    private double costoVenta;
    @Column(name = "porcentajeServicio", nullable = false)
    private double porcentajeServicio;
    @Column(name = "diasCuandoCompra", nullable = false)
    private double diasDeCompras;



    public Config(long id, double dias,double costo,double costoVenta,double porcentaje,double diasDe) {
        this.id = id;
        this.diasLaborales=dias;
        this.costoMantenimiento=costo;
        this.costoVenta=costoVenta;
        this.porcentajeServicio=porcentaje;
        this.diasDeCompras=diasDe;

    }
    public Config( double dias,double costo,double costoVenta,double porcentaje,double diasDe) {

        this.diasLaborales=dias;
        this.costoMantenimiento=costo;
        this.costoVenta=costoVenta;
        this.porcentajeServicio=porcentaje;
        this.diasDeCompras=diasDe;

    }
}
