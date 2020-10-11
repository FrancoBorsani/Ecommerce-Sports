package com.ecommercesports.ecommercesports.entities;

import javax.persistence.*;

@Entity
@Table(name="descuento")
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDescuento;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "porcentaje")
    private double porcentaje;

    public Descuento() {

    }

    public Descuento(long idDescuento, String codigo, double porcentaje) {
        this.idDescuento = idDescuento;
        this.codigo = codigo;
        this.porcentaje = porcentaje;
    }

    public long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(long idDescuento) {
        this.idDescuento = idDescuento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Descuento{" +
                "idDescuento=" + idDescuento +
                ", codigo='" + codigo + '\'' +
                ", porcentaje=" + porcentaje +
                '}';
    }
}