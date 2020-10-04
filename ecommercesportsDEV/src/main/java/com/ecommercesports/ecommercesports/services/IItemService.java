package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.models.ItemModel;

public interface IItemService {


	public List<Item> getAll();
	
	public ItemModel insertOrUpdate(ItemModel itemModel);
	
	public ItemModel findByIdItem(long idItem);
	
	public boolean remove(long idItem);

}//Fin interface
