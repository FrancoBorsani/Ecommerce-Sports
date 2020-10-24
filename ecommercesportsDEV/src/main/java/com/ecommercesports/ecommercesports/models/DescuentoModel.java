package com.ecommercesports.ecommercesports.models;

public class DescuentoModel {
    private long idDescuento;
    private String codigo;
    private double porcentaje;

    public DescuentoModel() {

    }

    public DescuentoModel(long idDescuento, String codigo, double porcentaje) {
        this.idDescuento = idDescuento;
        this.codigo = codigo.toLowerCase();
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
        this.codigo = codigo.toLowerCase();
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "DescuentoModel{" +
                "idDescuento=" + idDescuento +
                ", codigo='" + codigo + '\'' +
                ", porcentaje=" + porcentaje +
                '}';
    }
}