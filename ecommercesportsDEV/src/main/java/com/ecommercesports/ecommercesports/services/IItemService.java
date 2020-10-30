package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.ItemModel;

public interface IItemService {


	public List<Item> getAll();
	
	public ItemModel insertOrUpdate(ItemModel itemModel);
	
	public ItemModel findByIdItem(long idItem);
	
	public boolean remove(long idItem);
	
	public Item itemsByProducto(Producto producto);
	
	 //cuando busco el item de un producto tengo que tambien pasar el ID DEL CARRITO para que logre diferenciar los items de cada cliente
	public Item itemsByProductoAndCarrito(Producto producto , Carrito carrito);

	public Item insertarItemConProducto_y_Traer(Producto producto,  Carrito carrito);
	
	public Item agregarUnidadAlItemYTraer(Item item);
	
	public Item restarAlItemYTraer_EliminarSiEsCero(Item item);

}//Fin interface
