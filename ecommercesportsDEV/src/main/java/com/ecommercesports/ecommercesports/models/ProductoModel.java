package com.ecommercesports.ecommercesports.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Comentario;

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
    private Categoria categoria;
    private List<Comentario> listaComentarios;
    private double totalPuntaje;
    private int cantidadValoraciones;
    
    public ProductoModel() {
    	
    }

    public ProductoModel(long idProducto, double precio, String color, String descripcionCorta, String descripcionLarga,
			String sku, String talle, double totalPuntaje, int cantidadValoraciones) {
		super();
		this.idProducto = idProducto;
		this.precio = precio;
		this.color = color;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.sku = sku;
		this.talle = talle;
		this.totalPuntaje = totalPuntaje;
		this.cantidadValoraciones = cantidadValoraciones;
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
	
	
	

	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}
	
	
	
	
	public double getTotalPuntaje() {
		return totalPuntaje;
	}

	public void setTotalPuntaje(double totalPuntaje) {
		this.totalPuntaje = totalPuntaje;
	}
	
	
	
	
	public int getCantidadValoraciones() {
		return cantidadValoraciones;
	}

	public void setCantidadValoraciones(int cantidadValoraciones) {
		this.cantidadValoraciones = cantidadValoraciones;
	}

//	public void asignarPuntaje(double puntaje) {
//		this.puntaje.add(puntaje);
//		calcularPuntajeTotal();
//	}

//	public void calcularPuntajeTotal() {
//		double parcial = 0;
//		for(int i = 0; i < this.puntaje.size(); i++) {
//			parcial = parcial + this.puntaje.get(i);
//		}
		
//		parcial = parcial / this.puntaje.size();
//		this.totalPuntaje = parcial;
		
//	}
	
	
	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "ProductoModel [idProducto=" + idProducto + ", precio=" + precio + ", color=" + color
				+ ", descripcionCorta=" + descripcionCorta + ", descripcionLarga=" + descripcionLarga + ", marca="
				+ marca + ", sku=" + sku + ", talle=" + talle + ", listaCarritos=" + listaCarritos + ", categoria="
				+ categoria + "]";
	}
    
    
    
}