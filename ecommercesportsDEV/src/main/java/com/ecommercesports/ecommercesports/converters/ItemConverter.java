package com.ecommercesports.ecommercesports.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.models.ItemModel;

@Component("itemConverter")
public class ItemConverter {
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Autowired
	@Qualifier("carritoConverter")
	private CarritoConverter carritoConverter;

	public ItemModel entityToModel (Item item) {
		return new ItemModel(item.getIdItem(),productoConverter.entityToModel(item.getProducto()),item.getCantidad(),carritoConverter.entityToModel(item.getCarrito()));
	}
	
	public Item modelToEntity (ItemModel itemModel) {
		return new Item(itemModel.getIdItem(),productoConverter.modelToEntity(itemModel.getProducto()),itemModel.getCantidad(),carritoConverter.modelToEntity(itemModel.getCarritoModel()));
	}
	
	
	
}//Fin class
