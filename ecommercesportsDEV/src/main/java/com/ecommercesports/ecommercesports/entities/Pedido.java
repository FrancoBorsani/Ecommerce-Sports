package com.ecommercesports.ecommercesports.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long idPedido;

	 /*
	    @Column(name = "cliente")
	    private String cliente;   (que haya un metodo en cliente que te devuelva el nombre completo del cliente y lo guardes en este campo)
	 
	    @Column(name = "carrito")
	    private Carrito carrito;
	 */  
	 
	    @Column(name = "domicilio")
	    private String domicilio;
	    
	    @Column(name = "cantidad")
	    private int cantidad;

	    @Column(name = "importeAPagar")
	    private double importeAPagar;

	    @Column(name = "metodoPago")
	    private String metodoPago;

	    @Column(name = "comentario")
	    private String comentario;

	    @Column(name = "estado")
	    private String estado;


	    public Pedido() {
	    }

		public Pedido(long idPedido,/*String cliente*/ String domicilio, int cantidad, /*Carrito carrito*/ double importeAPagar, String metodoPago, String comentario,
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


		public String getDomicilio() {
			return domicilio;
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

		@Override
		public String toString() {
			return "Pedido [idPedido=" + idPedido + ", domicilio=" + domicilio + ", cantidad=" + cantidad
					+ ", importeAPagar=" + importeAPagar + ", metodoPago=" + metodoPago + ", comentario=" + comentario
					+ ", estado=" + estado + "]";
		}

	    
}
