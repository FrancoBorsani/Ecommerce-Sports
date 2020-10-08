package com.ecommercesports.ecommercesports.models;

public class ItemModel {
    private long idItem;
    private ProductoModel producto;
    private int cantidad;
    private CarritoModel carritoModel;
	
    
    public ItemModel() {}


	public ItemModel(long idItem, ProductoModel producto, int cantidad, CarritoModel carritoModel) {
		super();
		this.idItem = idItem;
		this.producto = producto;
		this.cantidad = cantidad;
		this.carritoModel = carritoModel;
	}


	public long getIdItem() {
		return idItem;
	}


	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}


	public ProductoModel getProducto() {
		return producto;
	}


	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public CarritoModel getCarritoModel() {
		return carritoModel;
	}


	public void setCarritoModel(CarritoModel carritoModel) {
		this.carritoModel = carritoModel;
	}


	@Override
	public String toString() {
		return "ItemModel [idItem=" + idItem + ", producto=" + producto + ", cantidad=" + cantidad + ", carritoModel="
				+ carritoModel + "]" + "\n";
	}
    
}//Fin class
