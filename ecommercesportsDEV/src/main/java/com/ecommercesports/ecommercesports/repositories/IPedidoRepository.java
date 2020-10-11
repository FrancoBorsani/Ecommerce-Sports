package com.ecommercesports.ecommercesports.repositories;

import com.ecommercesports.ecommercesports.entities.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.io.Serializable;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable> {
	public abstract Pedido findByIdPedido(long idPedido);
	
    @Query(nativeQuery=true,value="select * from pedido where user_id = (:idUser) and carrito_id_carrito = (select id_carrito from carrito where fecha = CURDATE())")
	public Pedido traerPedidoByIdUserAndDateNow(int idUser);

    @Query(nativeQuery=true,value="select * from pedido where carrito_id_carrito = (:idCarrito)")
    public Pedido traerPedidoDelCarrito(long idCarrito);
    
    @Query(nativeQuery=true,value="select * from pedido where user_id = (:user_id)")
    public Pedido traerPedidoDelUser(long user_id);
    
  //@Query("SELECT u FROM Carrito u WHERE u.user.email = (:email)")
  	//public abstract Carrito findByUser(@Param("email") String email);
  		
  	
    
    
    
}
