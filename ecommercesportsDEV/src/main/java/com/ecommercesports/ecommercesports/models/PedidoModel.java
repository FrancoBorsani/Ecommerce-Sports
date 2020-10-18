package com.ecommercesports.ecommercesports.models;

import org.springframework.lang.Nullable;

import com.ecommercesports.ecommercesports.entities.User;

public class PedidoModel {
	
	private long idPedido;
	
	@Nullable
	private String domicilio;
	
	private User user;
	
	private CarritoModel carritoModel;
    
	@Nullable
	private int cantidad;
    
	@Nullable
	private double importeAPagar;
    
	@Nullable
	private String metodoPago;
    
	@Nullable
	private String comentario;
    
	@Nullable
	private boolean pagado;
	
	@Nullable
	private double costoEnvio;
	
	public PedidoModel() { }

	public PedidoModel(long idPedido, User user, CarritoModel carritoModel) {
		super();
		this.idPedido = idPedido;
		this.user = user;
		this.carritoModel = carritoModel;
	}


	public PedidoModel(long idPedido, String domicilio, User user, CarritoModel carritoModel, int cantidad,
			double importeAPagar, String metodoPago, String comentario, boolean pagado) {
		super();
		this.idPedido = idPedido;
		this.domicilio = domicilio;
		this.user = user;
		this.carritoModel = carritoModel;
		this.cantidad = cantidad;
		this.importeAPagar = importeAPagar;
		this.metodoPago = metodoPago;
		this.comentario = comentario;
		this.pagado = pagado;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CarritoModel getCarritoModel() {
		return carritoModel;
	}

	public void setCarritoModel(CarritoModel carritoModel) {
		this.carritoModel = carritoModel;
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

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	

	public double getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	@Override
	public String toString() {
		return "PedidoModel [idPedido=" + idPedido + ", domicilio=" + domicilio + ", user=" + user + ", carritoModel="
				+ carritoModel + ", cantidad=" + cantidad + ", importeAPagar=" + importeAPagar + ", metodoPago="
				+ metodoPago + ", comentario=" + comentario + ", pagado=" + pagado + ", costoEnvio=" + costoEnvio + "]";
	}

	

	
}//Fin class