package com.ecommercesports.ecommercesports.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ecommercesports.ecommercesports.entities.Carrito;

public class ProductoModel {

    private long idProducto;
    private double precio;
    private String color;
    private String descripcionCorta;
    private String descripcionLarga;
    private String marca;
    private String sku;
    private String talle;
    private Set<Carrito> listaCarritos = new HashSet<Carrito>();
    
    
    public ProductoModel() {
    }

    public ProductoModel(double precio, String color, String descripcionCorta, String descripcionLarga, String marca, String sku, String talle) {
        this.color = color;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga;
        this.marca = marca;
        this.sku = sku;
        this.talle = talle;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

	public Set<Carrito> getListaCarritos() {
		return listaCarritos;
	}

	public void setListaCarritos(Set<Carrito> listaCarritos) {
		this.listaCarritos = listaCarritos;
	}
    
    
    
    
    
}