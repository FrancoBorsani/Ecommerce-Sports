package com.ecommercesports.ecommercesports.models;


import com.poiji.annotation.ExcelCellName;

public class RegistroExcelModel {

    @ExcelCellName("DescripcionCorta")//(lo del parentesis debe ser igual al nombre de la columna del excel)   sin el "@ExcelCellName" solo guarda números
    private String descripcionCorta;

    @ExcelCellName("DescripcionLarga")
    private String descripcionLarga;
	
    @ExcelCellName("Precio")
    private double precio;
    
    @ExcelCellName("PrecioEnOferta")
    private double precioEnOferta;

    @ExcelCellName("Color")
    private String color;

    @ExcelCellName("Sku")
    private String sku;

    @ExcelCellName("Talle")
    private String talle;
    
    @ExcelCellName("Categoria")
	private String categoria;
    
    @ExcelCellName("Marca")
	private String marca;
    
    @ExcelCellName("TotalPuntaje")
    private double totalPuntaje;
    
    @ExcelCellName("CantidadValoraciones")
    private int cantidadValoraciones;
    
    @ExcelCellName("Peso")
    private double peso;
    
    @ExcelCellName("Visible")
    private boolean visible;
    
    @ExcelCellName("Imagen")
    private String imagen;

    
	public RegistroExcelModel() { }

	
	public RegistroExcelModel(String descripcionCorta, String descripcionLarga, double precio, double precioEnOferta,
			String color, String sku, String talle, String categoria, String marca, double totalPuntaje,
			int cantidadValoraciones, double peso, boolean visible, String imagen) {
		super();
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.precio = precio;
		this.precioEnOferta = precioEnOferta;
		this.color = color;
		this.sku = sku;
		this.talle = talle;
		this.categoria = categoria;
		this.marca = marca;
		this.totalPuntaje = totalPuntaje;
		this.cantidadValoraciones = cantidadValoraciones;
		this.peso = peso;
		this.visible = visible;
		this.imagen = imagen;
	}




	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecioEnOferta() {
		return precioEnOferta;
	}

	public void setPrecioEnOferta(double precioEnOferta) {
		this.precioEnOferta = precioEnOferta;
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


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
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
	
	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	@Override
	public String toString() {
		return "RegistroExcelModel [descripcionCorta=" + descripcionCorta + ", descripcionLarga=" + descripcionLarga
				+ ", precio=" + precio + ", precioEnOferta=" + precioEnOferta + ", color=" + color + ", sku=" + sku
				+ ", talle=" + talle + ", categoria=" + categoria + ", marca=" + marca + ", totalPuntaje="
				+ totalPuntaje + ", cantidadValoraciones=" + cantidadValoraciones + ", peso=" + peso + ", visible="
				+ visible + ", imagen=" + imagen + "]";
	}

}//Fin class