package com.ecommercesports.ecommercesports.models;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Categoria;
import com.ecommercesports.ecommercesports.entities.Comentario;
import com.ecommercesports.ecommercesports.entities.Marca;
import com.ecommercesports.ecommercesports.entities.Tag;
import com.ecommercesports.ecommercesports.entities.Valoracion;

public class ProductoModel {

    private long idProducto;
    private double precio;
    private double precioEnOferta;
    private String color;
    private String descripcionCorta;
    private String descripcionLarga;
    private Marca marca;
    private String sku;
    private String talle;
    private Categoria categoria;
    private List<Comentario> listaComentarios;
    private double totalPuntaje;
    private int cantidadValoraciones;
    private List<Tag> tags;
    private double peso;
    private boolean visible;
    private String imagen;
    private List<Valoracion> listaValoraciones;
	
    public ProductoModel() {
    }

    public ProductoModel(long idProducto, double precio,double precioEnOferta, String color, String descripcionCorta, String descripcionLarga,
			String sku, String talle, double totalPuntaje, int cantidadValoraciones, double peso, List<Tag> tags,boolean visible, String imagen, List<Valoracion> listaValoraciones, Categoria categoria, Marca marca) {
		super();
		this.idProducto = idProducto;
		this.precio = precio;
		this.color = color;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.talle = talle;
		this.totalPuntaje = totalPuntaje;
		this.cantidadValoraciones = cantidadValoraciones;
		this.tags = tags;
		this.peso = peso;
		this.precioEnOferta = precioEnOferta;
		this.visible = visible;
		this.imagen = imagen;
		this.listaValoraciones = listaValoraciones;
		this.categoria = categoria;
		this.marca = marca;
		setSku();
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

	//Pone en minusculas todas las cadenas y elimina los espacios si hay.
	public void setSku() {
		this.sku =  getDescripcionCorta().toLowerCase().replaceAll("\\s","") + "_" +
				getColor().toLowerCase().replaceAll("\\s","") + "_" +
				getTalle().toLowerCase().replaceAll("\\s","") + "_" +
				getIdProducto();
	}

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public double getPrecioEnOferta() {
		return precioEnOferta;
	}

	public void setPrecioEnOferta(double precioEnOferta) {
		this.precioEnOferta = precioEnOferta;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public List<Valoracion> getListaValoraciones() {
		return listaValoraciones;
	}

	public void setListaValoraciones(List<Valoracion> listaValoraciones) {
		this.listaValoraciones = listaValoraciones;
	}

	@Override
	public String toString() {
		return "ProductoModel [idProducto=" + idProducto + ", precio=" + precio + ", precioEnOferta=" + precioEnOferta
				+ ", color=" + color + ", descripcionCorta=" + descripcionCorta + ", descripcionLarga="
				+ descripcionLarga + ", marca=" + marca + ", sku=" + sku + ", talle=" + talle + ", categoria="
				+ categoria + ", listaComentarios=" + listaComentarios + ", totalPuntaje=" + totalPuntaje
				+ ", cantidadValoraciones=" + cantidadValoraciones + ", tags=" + tags + ", peso=" + peso + ", visible="
				+ visible + ", imagen=" + imagen + "]";
	}
	

}