package com.ecommercesports.ecommercesports.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;

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
    
    @OneToOne(cascade = CascadeType.MERGE)
	private Categoria categoria;
    
    @OneToOne(cascade = CascadeType.MERGE)
	private Marca marca;

    //private Set<Carrito> listaCarritos = new HashSet<Carrito>();
    
    @ManyToMany(mappedBy = "listaProductos")
    private List<Carrito> listaCarritos;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> listaComentarios;
    
  
    public Producto() {
    }


    public Producto(long idProducto,double precio, String color, String descripcionCorta,
			String descripcionLarga, String sku, String talle) {
		super();
		this.idProducto = idProducto;
		this.precio = precio;
		this.color = color;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.sku = sku;
		this.talle = talle;
	}


	public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	
	
	
	
	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}


	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}


	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", marca=" + marca + ", precio=" + precio + ", color=" + color
				+ ", descripcionCorta=" + descripcionCorta + ", descripcionLarga=" + descripcionLarga + ", sku=" + sku
				+ ", talle=" + talle + ", categoria=" + categoria + ", listaCarritos=" + listaCarritos + "]";
	}
	
	

}