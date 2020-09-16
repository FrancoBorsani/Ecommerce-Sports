package com.ecommercesports.ecommercesports.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;

    @Column(name = "marca")
    private String marca;

    @Column(name = "precio")
    private double precio;

    @Column(name = "color")
    private String color;

    @Column(name = "descripcioncorta")
    private String descripcionCorta;

    @Column(name = "descipcionlarga")
    private String descripcionLarga;

    @Column(name = "sku")
    private String sku;

    @Column(name = "talle")
    private String talle;

    //private Set<Carrito> listaCarritos = new HashSet<Carrito>();
    
    @ManyToMany(mappedBy = "listaProductos")
    private List<Carrito> listaCarritos;
    
  
    public Producto() {
    }

    public Producto(double precio, String color, String descripcionCorta, String descripcionLarga, String marca, String sku, String talle) {
        this.precio = precio;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

	public List<Carrito> getListaCarritos() {
		return listaCarritos;
	}

	public void setListaCarritos(List<Carrito> listaCarritos) {
		this.listaCarritos = listaCarritos;
	}
    
    
    
    
}