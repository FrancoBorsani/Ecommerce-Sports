package com.ecommercesports.ecommercesports.models;

public class PedidoModel {
	private long idPedido;
   //private String cliente; (que haya un metodo en cliente que te devuelva el nombre completo del cliente y lo guardes en este campo)
	private String domicilio;
   //private Carrito carrito;
	private int cantidad;
	private double importeAPagar;
	private String metodoPago;
	private String comentario;
	private String estado;
	
	
	public PedidoModel(long idPedido,/*String cliente*/ String domicilio, int cantidad, /*Carrito carrito*/ double importeAPagar, String metodoPago, String comentario,
			String estado) {

		this.idPedido = idPedido;
		//this.cliente = cliente;
		this.domicilio = domicilio;
		this.cantidad = cantidad;
		//this.carrito = Carrito;
		this.importeAPagar = importeAPagar;
		this.metodoPago = metodoPago;
		this.comentario = comentario;
		this.estado = estado;
	}


	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

/*
	public String getCliente() {
		return cliente;
	}
	
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	
	public Carrito getCarrito() {
		return carrito;
	}
	
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
*/
	
	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getImporteAPagar() {
		return importeAPagar;
	}

	public void setImporteAPagar(double importeAPagar) {
		this.importeAPagar = importeAPagar;
	}


	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
