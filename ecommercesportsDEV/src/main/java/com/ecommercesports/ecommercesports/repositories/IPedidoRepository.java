package com.ecommercesports.ecommercesports.repositories;

import com.ecommercesports.ecommercesports.entities.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable> {
	public abstract Pedido findByIdPedido(long idPedido);
	
    @Query(nativeQuery=true,value="select * from pedido where pagado = false and user_id = (:idUser)")
	public Pedido traerPedidoPorIdUser_y_NoPagado(int idUser);

    @Query(nativeQuery=true,value="select * from pedido where carrito_id_carrito = (:idCarrito)")
    public Pedido traerPedidoDelCarrito(long idCarrito);
    
    @Query(nativeQuery=true,value="select * from pedido where user_id = (:user_id)")
    public List<Pedido> traerPedidosDelUser(long user_id);
    
    //@Query("SELECT u FROM Carrito u WHERE u.user.email = (:email)")
  	//public abstract Carrito findByUser(@Param("email") String email);
  		
    @Query(nativeQuery=true,value="select * from pedido where user_id = (:user_id)")
    public Pedido traerPedidoPorUsuario(long user_id);
    
 //   @Query(nativeQuery=true,value="select obtenerValor_x_PesoEmpresa((:empresa),(:nroColumna))")
  //  public abstract double getCostoEnvio(String empresa, int nroColumna);
    
 //   @Modifying
 //   @Transactional 
 //   @Query(value="UPDATE Pedido p SET p.costoEnvio = ?1 WHERE p.idPedido = ?2")
 //   public int updateCostoEnvio(double costoEnvio,long idCarrito);
    
}
