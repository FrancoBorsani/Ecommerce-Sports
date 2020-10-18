package com.ecommercesports.ecommercesports.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name="pedido")
public class Pedido {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long idPedido; 
	 
	    @Nullable
	    @Column(name = "domicilio")
	    private String domicilio;

	    @OneToOne(fetch=FetchType.LAZY)
		private User user;

	    @OneToOne(fetch=FetchType.LAZY)
		private Carrito carrito;
	    
		@Nullable
	    @Column(name = "cantidad")
	    private int cantidad;

		@Nullable
	    @Column(name = "importeAPagar")
	    private double importeAPagar;

		@Nullable
	    @Column(name = "metodoPago")
	    private String metodoPago;

		@Nullable
	    @Column(name = "comentario")
	    private String comentario;
		
		@Nullable
	    @Column(name = "pagado")
	    private boolean pagado;

		@Nullable
	    @Column(name = "costoEnvio")
	    private double costoEnvio;

	    public Pedido() { }

		public Pedido(long idPedido, User user, Carrito carrito) {
			super();
			this.idPedido = idPedido;
			this.user = user;
			this.carrito = carrito;
		}


		public Pedido(long idPedido, String domicilio, User user, Carrito carrito, int cantidad, double importeAPagar,
				String metodoPago, String comentario, boolean pagado) {
			super();
			this.idPedido = idPedido;
			this.domicilio = domicilio;
			this.user = user;
			this.carrito = carrito;
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


		public Carrito getCarrito() {
			return carrito;
		}


		public void setCarrito(Carrito carrito) {
			this.carrito = carrito;
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
			return "Pedido [" + "carrito="
					+ carrito + "]";
		}

		
}//Fin class
