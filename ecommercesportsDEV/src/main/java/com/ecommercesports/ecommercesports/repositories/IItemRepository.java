package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Item;

@Repository("itemRepository")
public interface IItemRepository extends JpaRepository<Item, Serializable> {

	public abstract Item findByIdItem(long idItem);

	@Query(nativeQuery=true,value="	select * from item where producto_id_producto = (:idProducto)")
	public abstract Item itemsByIdProducto(long idProducto);
	
	@Query(nativeQuery=true,value="	select * from item where producto_id_producto = (:idProducto) and id_carrito= (:idCarrito")
	public abstract Item itemsByIdProductoAndIdCarrito(long idProducto , long idCarrito);
	
	@Query(nativeQuery=true,value="SELECT * FROM Item where id_carrito = (:idCarrito)")
	public abstract List<Item> itemsDelCarrito(long idCarrito);


	
}//Fin interface 
